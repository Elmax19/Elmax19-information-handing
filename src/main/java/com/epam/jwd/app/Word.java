package com.epam.jwd.app;

public class Word extends Lexeme {
    private final String data;

    public Word(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
