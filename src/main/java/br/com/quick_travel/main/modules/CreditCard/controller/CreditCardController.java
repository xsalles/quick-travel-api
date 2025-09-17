package br.com.quick_travel.main.modules.CreditCard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.modules.CreditCard.model.CreditCardModel;
import br.com.quick_travel.main.modules.CreditCard.service.CreditCardService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/credit-cards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponseDto<String>> addCreditCard(@RequestBody CreditCardModel creditCardModel,
            HttpServletRequest request) {
        return creditCardService.addCreditCardForUser(request, creditCardModel);
    }

}
