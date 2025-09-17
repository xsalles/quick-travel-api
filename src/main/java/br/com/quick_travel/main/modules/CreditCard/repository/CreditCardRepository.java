package br.com.quick_travel.main.modules.CreditCard.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quick_travel.main.modules.CreditCard.model.CreditCardModel;

public interface CreditCardRepository extends JpaRepository<CreditCardModel, UUID> {
    
}
