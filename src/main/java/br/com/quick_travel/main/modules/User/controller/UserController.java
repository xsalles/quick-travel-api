package br.com.quick_travel.main.modules.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.modules.User.model.UserModel;
import br.com.quick_travel.main.modules.User.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponseDto<String>> registerUser(@Valid @RequestBody UserModel userModel) {
        return userService.registerUser(userModel);
    }
}
