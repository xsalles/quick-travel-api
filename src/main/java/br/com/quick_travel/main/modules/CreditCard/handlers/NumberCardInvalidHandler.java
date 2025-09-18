package br.com.quick_travel.main.modules.CreditCard.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.quick_travel.main.common.dto.ApiResponseDto;

@RestControllerAdvice
public class NumberCardInvalidHandler {
        
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponseDto<String>> handleNumberFormatException (NumberFormatException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ApiResponseDto<>("Invalid card number", HttpStatus.UNPROCESSABLE_ENTITY.value(), null));
    }
}
