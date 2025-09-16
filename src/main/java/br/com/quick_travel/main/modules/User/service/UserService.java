package br.com.quick_travel.main.modules.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.common.exceptions.EntityAlreadyExistsException;
import br.com.quick_travel.main.common.exceptions.EntityNotFoundException;
import br.com.quick_travel.main.common.provider.JWTProvider;
import br.com.quick_travel.main.modules.User.dto.AuthUserDto;
import br.com.quick_travel.main.modules.User.model.UserModel;
import br.com.quick_travel.main.modules.User.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider JWTProvider;

    public ResponseEntity<ApiResponseDto<String>> registerUser(UserModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new EntityAlreadyExistsException("This user already exists.");
        }

        String encodedPassword = passwordEncoder.encode(userModel.getPassword());

        userModel.setPassword(encodedPassword);

        userRepository.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto<String>("User registered successfully", HttpStatus.CREATED.value(), null));
    }

    public ResponseEntity<ApiResponseDto<String>> loginUser(AuthUserDto authUserDto) {
        var user = userRepository.findByEmail(authUserDto.getEmail());

        if (!user.isPresent()) {
            throw new EntityNotFoundException("This user does not exist");
        }

        var passwordMatches = passwordEncoder.matches(authUserDto.getPassword(),
                userRepository.findByEmail(authUserDto.getEmail()).get().getPassword());

        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDto<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED.value(), null));
        }

        var token = JWTProvider.generateToken(user.get().getId(), user.get().getEmail());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<String>("User logged in successfully", HttpStatus.OK.value(), token));
    }
}
