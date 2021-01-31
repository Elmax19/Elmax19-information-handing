package com.epam.jwd.calculator;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;

public class ExpressionCalculator {

    @FunctionalInterface
    public interface Expr {
        int interpret(Map<String, Integer> context);

        static Expr number(int number) {
            return context -> number;
        }

        static Expr leftShift(Expr left, Expr right) {
            return context -> left.interpret(context) << right.interpret(context);
        }

        static Expr rightShift(Expr left, Expr right) {
            return context -> left.interpret(context) >> right.interpret(context);
        }

        static Expr bitAnd(Expr left, Expr right) {
            return context -> left.interpret(context) & right.interpret(context);
        }

        static Expr bitOr(Expr left, Expr right) {
            return context -> left.interpret(context) | right.interpret(context);
        }

        static Expr exclusiveOr(Expr left, Expr right) {
            return context -> left.interpret(context) ^ right.interpret(context);
        }

        static Expr bitComplement(Expr right) {
            return context -> ~right.interpret(context);
        }
    }

    private static Expr parseToken(String token, ArrayDeque<Expr> stack) {
        Expr left, right;
        switch (token) {
            case ">":
                right = stack.pop();
                left = stack.pop();
                return Expr.rightShift(left, right);
            case "<":
                right = stack.pop();
                left = stack.pop();
                return Expr.leftShift(left, right);
            case "|":
                right = stack.pop();
                left = stack.pop();
                return Expr.bitOr(left, right);
            case "^":
                right = stack.pop();
                left = stack.pop();
                return Expr.exclusiveOr(left, right);
            case "&":
                right = stack.pop();
                left = stack.pop();
                return Expr.bitAnd(left, right);
            case "~":
                right = stack.pop();
                return Expr.bitComplement(right);
            default:
                return Expr.number(Integer.parseInt(token));
        }
    }

    public static Expr parse(List<String> expression) {
        ArrayDeque<Expr> stack = new ArrayDeque<>();
        for (String token : expression) {
            stack.push(parseToken(token, stack));
        }
        return stack.pop();
    }
}
