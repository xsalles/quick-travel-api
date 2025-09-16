package br.com.quick_travel.main.modules.User.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserDto {
    private String email;
    private String password;
}
