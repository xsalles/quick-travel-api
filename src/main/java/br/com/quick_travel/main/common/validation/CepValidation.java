package br.com.quick_travel.main.common.validation;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CepValidation implements ConstraintValidator<ValidCep, String> {

    private static final String CEP_REGEX = "\\d{8}";

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || !cep.matches(CEP_REGEX)) {
            return false;
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getBody() != null && response.getBody().containsKey("erro")) {
                return false;
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
