package com.epam.jwd.calculator;

import com.epam.jwd.exception.WrongInputString;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class ExpressionParser {
    private static final List<String> operators = new ArrayList<>();
    private static final List<String> delimiters = new ArrayList<>();

    public ExpressionParser() {
        operators.add(">");
        operators.add("<");
        operators.add("^");
        operators.add("~");
        operators.add("&");
        operators.add("|");
        delimiters.add("(");
        delimiters.add(")");
        delimiters.addAll(operators);
    }

    private static boolean isDelimiter(String token) {
        for (String delimiter : delimiters) {
            if (token.equals(delimiter)) return true;
        }
        return false;
    }

    private static boolean isOperator(String token) {
        for (String operator : operators) {
            if (token.equals(operator)) return true;
        }
        return false;
    }

    private static int priority(String token) throws IllegalArgumentException {
        switch (token) {
            case "(":
                return 1;
            case "|":
                return 2;
            case "^":
                return 3;
            case "&":
                return 4;
            case "<":
            case ">":
                return 5;
            case "~":
                return 6;
            default:
                throw new IllegalArgumentException("Wrong operation (" + token + ")");
        }
    }

    public List<String> parse(String infix) throws IllegalArgumentException, WrongInputString {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, "()><!^&|~", true);
        String prev = "";
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                throw new WrongInputString("Needs more params after" + curr);
            }
            if (prev.equals(">") || prev.equals("<")) {
                prev = "";
                continue;
            }
            if (isDelimiter(curr)) {
                if (curr.equals("(")) stack.push(curr);
                else if (curr.equals(")")) {
                    while (!Objects.equals(stack.peek(), "(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            throw new WrongInputString("Problem with brackets");
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty()) {
                        postfix.add(stack.pop());
                    }
                } else {
                    while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                        postfix.add(stack.pop());
                    }
                    stack.push(curr);
                }

            } else {
                postfix.add(curr);
            }
            prev = curr;
        }
        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                throw new WrongInputString("Problem with brackets");
            }
        }
        return postfix;
    }
}
