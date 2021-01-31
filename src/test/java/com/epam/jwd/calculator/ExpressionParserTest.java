package com.epam.jwd.calculator;

import com.epam.jwd.exception.WrongInputString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ExpressionParserTest {
    ExpressionParser expressionParser;

    @Before
    public void init() {
        expressionParser = new ExpressionParser();
    }

    @Test
    public void parse_shouldParseTheString() {
        List<String> actual = expressionParser.parse("~6&9|(3>>1^4<<1)");
        List<String> expected = Arrays.asList("6", "~", "9", "&", "3", "1", ">", "4", "1", "<", "^", "|");
        Assert.assertEquals("", expected, actual);
    }

    @Test(expected = WrongInputString.class)
    public void parse_shouldThrowWrongInputString_whenMoreOpenBrackets() throws WrongInputString {
        expressionParser.parse("(~6&9|(3>>1^4<<1)");
    }

    @Test(expected = WrongInputString.class)
    public void parse_shouldThrowWrongInputString_whenWrongBracketsOrder() throws WrongInputString {
        expressionParser.parse("~6&9|)3>>1^4<<1(");
    }

    @Test(expected = WrongInputString.class)
    public void parse_shouldThrowWrongInputString_whenNotEnoughArgs() throws WrongInputString {
        List<String> actual = expressionParser.parse("~6&9|(3>>1^4<<1)|");
    }
}