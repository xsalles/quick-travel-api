package br.com.quick_travel.main.modules.CreditCard.model;

import java.util.UUID;

import br.com.quick_travel.main.modules.CreditCard.validation.ValidCardNumber;
import br.com.quick_travel.main.modules.User.model.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "credit_cards")
public class CreditCardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "CVV cannot be blank")
    @Pattern(regexp = "\\d{3,4}", message = "CVV must be 3 or 4 digits")
    private String cvv;

    @ValidCardNumber
    @NotBlank(message = "Card number cannot be blank")
    @Column(name = "number_card", unique = true, nullable = false, length = 16)
    private String numberCard;

    @Pattern(regexp = "\\d+", message = "Number must be a positive integer")
    @NotBlank(message = "Holder name cannot be blank")
    private String holderName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}

