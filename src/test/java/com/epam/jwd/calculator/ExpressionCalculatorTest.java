package com.epam.jwd.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExpressionCalculatorTest {
    List<String> strings;

    @Before
    public void init() {
        strings = Arrays.asList("6", "~", "9", "&", "3", "1", ">", "4", "1", "<", "^", "|");
    }

    @Test
    public void parse_shouldReturnRightAnswer() {
        ExpressionCalculator.Expr expr = ExpressionCalculator.parse(strings);
        String actual = ((Integer) expr.interpret(new HashMap<>())).toString();
        Assert.assertEquals("With the string ~6&9|(3>>1^4<<1) should return 9", "9", actual);
    }
}