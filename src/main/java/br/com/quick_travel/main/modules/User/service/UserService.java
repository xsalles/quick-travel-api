package br.com.quick_travel.main.modules.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.common.exceptions.EntityAlreadyExistsException;
import br.com.quick_travel.main.modules.User.model.UserModel;
import br.com.quick_travel.main.modules.User.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

}
