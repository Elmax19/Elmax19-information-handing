package com.epam.jwd.app;

import com.epam.jwd.calculator.ExpressionCalculator;
import com.epam.jwd.calculator.ExpressionParser;
import com.epam.jwd.exception.WrongInputString;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexeme {
    private static final String EXPR_REGEX = "[0-9]+";
    private Lexeme lexeme;

    public Lexeme() {
    }

    public Lexeme(String data) {
        Pattern p = Pattern.compile(EXPR_REGEX);
        Matcher m = p.matcher(data);
        if (m.find()) {
            if (data.length() > 1) {
                try {
                    List<String> polishString = new ExpressionParser().parse(data);
                    ExpressionCalculator.Expr expr = ExpressionCalculator.parse(polishString);
                    data = ((Integer) expr.interpret(new HashMap<>())).toString();
                } catch (IllegalArgumentException | WrongInputString e) {
                    Information.LOGGER.error(e.getMessage());
                }
            }
            lexeme = new Expression(data);
        } else {
            lexeme = new Word(data);
        }
    }

    @Override
    public String toString() {
        return lexeme.toString();
    }
}
