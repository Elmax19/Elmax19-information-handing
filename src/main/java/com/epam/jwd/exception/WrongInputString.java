package com.epam.jwd.exception;

public class WrongInputString extends RuntimeException {
    public WrongInputString(String message) {
        super("Wrong input string (" + message + ")");
    }
}
