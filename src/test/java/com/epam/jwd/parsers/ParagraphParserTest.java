package com.epam.jwd.parsers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ParagraphParserTest {
    ParagraphParser parser = ParagraphParser.getINSTANCE();

    @Test
    public void parse() {
        List<String> actual = parser.parse("Hello! Hi, it's the 1>>2 test.\nHello again!\n");
        List<String> expended = Arrays.asList("Hello! Hi, it's the 1>>2 test.\n", "Hello again!\n");
        Assert.assertEquals("Should split paragraphs", expended, actual);
    }
}