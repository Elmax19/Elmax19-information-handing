package com.epam.jwd.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends Parser {
    private final static ParagraphParser INSTANCE = new ParagraphParser();
    private final static String PARAGRAPH_REGEX = ".+?\\n";

    private ParagraphParser() {
    }

    public static ParagraphParser getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public List<String> parse(String data) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile(PARAGRAPH_REGEX);
        Matcher m = p.matcher(data);
        boolean hasNext = m.find();
        while (hasNext) {
            list.add(m.group());
            hasNext = m.find();
        }
        return list;
    }
}
