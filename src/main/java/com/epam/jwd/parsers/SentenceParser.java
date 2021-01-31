package com.epam.jwd.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends Parser {
    private final static SentenceParser INSTANCE = new SentenceParser();
    private final static String SENTENCE_REGEX = "[A-Z0-9].*?[.!?]";

    private SentenceParser() {
    }

    public static SentenceParser getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public List<String> parse(String data) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile(SENTENCE_REGEX);
        Matcher m = p.matcher(data);
        boolean hasNext = m.find();
        while (hasNext) {
            list.add(m.group());
            hasNext = m.find();
        }
        return list;
    }
}
