package br.com.quick_travel.main.common.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.common.exceptions.EntityAlreadyExistsException;

@ControllerAdvice
public class EntityAlreadyExistsHandler {
    
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<String>> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        ApiResponseDto<String> response = new ApiResponseDto<>(ex.getMessage(), 409, null);

        return ResponseEntity.status(409).body(response);
    }
}
