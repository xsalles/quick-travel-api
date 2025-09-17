package br.com.quick_travel.main.modules.Location.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.modules.Location.exceptions.UserAlreadyHasLocationException;

@RestControllerAdvice
public class UserAlreadyHasLocationHandler {

    @ExceptionHandler(UserAlreadyHasLocationException.class)
    public ResponseEntity<ApiResponseDto<String>> handleUserAlreadyHasLocationException(
            UserAlreadyHasLocationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponseDto<String>(ex.getMessage(), HttpStatus.CONFLICT.value(), null));
    }
}
