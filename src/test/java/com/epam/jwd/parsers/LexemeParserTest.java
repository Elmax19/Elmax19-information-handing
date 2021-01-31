package com.epam.jwd.parsers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LexemeParserTest {
    LexemeParser parser = LexemeParser.getINSTANCE();

    @Test
    public void parse() {
        List<String> actual = parser.parse("Hi, it's the 1>>2 test.");
        List<String> expended = Arrays.asList("Hi,", "it's", "the", "1>>2", "test.");
        Assert.assertEquals("Should split lexemes", expended, actual);
    }
}