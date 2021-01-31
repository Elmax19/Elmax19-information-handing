package com.epam.jwd.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends Parser {
    private final static LexemeParser INSTANCE = new LexemeParser();
    private final static String LEXEME_REGEX = "[^ ]++";

    private LexemeParser() {
    }

    public static LexemeParser getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public List<String> parse(String data) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile(LEXEME_REGEX);
        Matcher m = p.matcher(data);
        boolean hasNext = m.find();
        while (hasNext) {
            list.add(m.group());
            hasNext = m.find();
        }
        return list;
    }
}
