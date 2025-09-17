package br.com.quick_travel.main.common.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.quick_travel.main.common.dto.ErrorMessageDto;

@RestControllerAdvice
public class EntityPatternHandler {

      private MessageSource messageSource;

      public EntityPatternHandler(MessageSource message) {
            this.messageSource = message;
      }

      @ExceptionHandler(Exception.class)
      public ResponseEntity<List<ErrorMessageDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
            List<ErrorMessageDto> dto = new ArrayList<>();

            ex.getBindingResult().getFieldErrors().forEach(err -> {
                  String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

                  ErrorMessageDto error = new ErrorMessageDto(message, err.getField());

                  dto.add(error);
            });

            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
      }

}
