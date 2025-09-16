package br.com.quick_travel.main.common.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.common.exceptions.EntityNotFoundException;

@RestControllerAdvice
public class EntityNotFoundHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponseDto<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiResponseDto<String> response = new ApiResponseDto<>(ex.getMessage(), 404, null);

        return ResponseEntity.status(404).body(response);
    }
}
