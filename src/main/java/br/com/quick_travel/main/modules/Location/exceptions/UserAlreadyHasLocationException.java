package br.com.quick_travel.main.modules.Location.exceptions;

public class UserAlreadyHasLocationException extends RuntimeException {
    public UserAlreadyHasLocationException() {
        super("User already has a location.");
    }
}
