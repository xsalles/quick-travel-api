package br.com.quick_travel.main.modules.CreditCard.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.quick_travel.main.common.dto.ApiResponseDto;
import br.com.quick_travel.main.common.exceptions.EntityAlreadyExistsException;
import br.com.quick_travel.main.modules.CreditCard.model.CreditCardModel;
import br.com.quick_travel.main.modules.CreditCard.repository.CreditCardRepository;
import br.com.quick_travel.main.modules.User.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ApiResponseDto<String>> addCreditCardForUser(HttpServletRequest request,
            CreditCardModel creditCardModel) {
        var userId = request.getAttribute("user_id");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDto<String>("Sorry, you can't add a credit card, make login again please.",
                            HttpStatus.UNAUTHORIZED.value(), null));
        }

        var user = userRepository.findById(UUID.fromString(userId.toString()));

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found.");
        }

        List<CreditCardModel> creditCards = user.get().getCreditCards();

        boolean alreadyExists = creditCards.stream()
                .anyMatch(card -> card.getNumberCard().equals(creditCardModel.getNumberCard()));

        if (alreadyExists) {
            throw new EntityAlreadyExistsException("This credit card already exists for this user.");
        }

        creditCardModel.setUser(user.get());

        creditCardRepository.save(creditCardModel);

        creditCards.add(creditCardModel);

        user.get().setCreditCards(creditCards);

        userRepository.save(user.get());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto<String>("Credit card added successfully.", HttpStatus.CREATED.value(), null));
    }
}
