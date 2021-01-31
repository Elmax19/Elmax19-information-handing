package com.epam.jwd.app;

import com.epam.jwd.parsers.Parser;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private final List<Lexeme> lexemes = new ArrayList<>();

    public Sentence(Parser parser, String data) {
        createSentence(parser, data);
    }

    private void createSentence(Parser parser, String data) {
        List<String> lexemes = parser.parse(data);
        for (String lexeme : lexemes) {
            this.lexemes.add(new Lexeme(lexeme));
        }
    }

    public void sortLexemesBySymbol(char symbol) {
        lexemes.sort((l1, l2) -> {
            int count1 = 0;
            int count2 = 0;
            for (char chr : l1.toString().toCharArray()) {
                if (chr == symbol) {
                    count1++;
                }
            }
            for (char chr : l2.toString().toCharArray()) {
                if (chr == symbol) {
                    count2++;
                }
            }
            if (count1 != count2) {
                return count2 - count1;
            }
            return l1.toString().compareTo(l2.toString());
        });
    }

    public List<Lexeme> getLexemes() {
        return lexemes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lexeme lexeme : lexemes) {
            stringBuilder.append(lexeme.toString()).append(" ");
        }
        return stringBuilder.toString();
    }
}
