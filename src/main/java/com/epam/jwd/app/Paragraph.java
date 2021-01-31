package com.epam.jwd.app;

import com.epam.jwd.parsers.Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Paragraph {
    private final List<Sentence> sentences = new ArrayList<>();

    public Paragraph(Parser parser, String data) {
        createParagraph(parser, data);
    }

    private void createParagraph(Parser parser, String data) {
        List<String> sentences = parser.parse(data);
        for (String sentence : sentences) {
            this.sentences.add(new Sentence(parser.getNext(), sentence));
        }
    }

    public void sortSentencesByCountOfLexemes() {
        Comparator<Sentence> comparator = Comparator.comparing(sentence -> sentence.getLexemes().size());
        sentences.sort(comparator);
    }

    public void sortLexemesBySymbol(char symbol) {
        for (Sentence sentence : sentences) {
            sentence.sortLexemesBySymbol(symbol);
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("    ");
        for (Sentence sentence : sentences) {
            stringBuilder.append(sentence.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}