package br.com.quick_travel.main.modules.CreditCard.exceptions;

public class NumberCardInvalidException extends RuntimeException {
    public NumberCardInvalidException(String message) {
        super(message);
    }
}
