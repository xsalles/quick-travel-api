package br.com.quick_travel.main.common.handlers;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotValidInformation {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseDto<String>> handleEntityNotValidInformation(ConstraintViolationException ex, HttpServletRequest request) {
        var path = request.getRequestURI();

        if (path.startsWith("/credit-cards/add")) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiResponseDto<String>("Invalid number card, please try again", HttpStatus.UNPROCESSABLE_ENTITY.value(), null));
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ApiResponseDto<String>("Invalid CEP, please try again", HttpStatus.UNPROCESSABLE_ENTITY.value(), null));
    }
}
