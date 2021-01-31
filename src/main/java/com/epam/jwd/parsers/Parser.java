package com.epam.jwd.parsers;

import java.util.List;

public abstract class Parser {
    private Parser next;

    public Parser linkWith(Parser next) {
        this.next = next;
        return next;
    }

    public abstract List<String> parse(String data);

    public Parser getNext() {
        return next;
    }
}
