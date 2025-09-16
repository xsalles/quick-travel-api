package br.com.quick_travel.main.modules.Location.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.modules.Location.model.LocationModel;
import br.com.quick_travel.main.modules.Location.repository.LocationRepository;
import br.com.quick_travel.main.modules.User.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ApiResponseDto<String>> addLocationForUser(HttpServletRequest request, LocationModel locationModel) {
        var userId = request.getAttribute("user_id");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDto<String>("Sorry, you can't add a location, make login again please.",
                            HttpStatus.UNAUTHORIZED.value(), null));
        }
        
        var user = userRepository.findById(UUID.fromString(userId.toString()));

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto<String>("User not found.", HttpStatus.NOT_FOUND.value(), null));
        }

        locationModel.setUser(user.get());

        locationRepository.save(locationModel);

        user.get().setLocation(locationModel);

        userRepository.save(user.get());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto<String>("Location added successfully.", HttpStatus.CREATED.value(), null));
    }
}
