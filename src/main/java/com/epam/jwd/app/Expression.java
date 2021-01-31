package com.epam.jwd.app;

public class Expression extends Lexeme {
    private final String data;

    public Expression(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
