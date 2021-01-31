package com.epam.jwd.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.jwd.parsers.Parser;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Information {
    private final List<Paragraph> paragraphs = new ArrayList<>();
    public static final Logger LOGGER = LoggerFactory.getLogger(Information.class);
    private static final File INPUT_FILE = new File("./src/main/resources/input.txt");
    private static final File OUTPUT_FILE = new File("./src/main/resources/output.txt");

    public Information(Parser parser) {
        createInformation(parser);
    }

    public void createInformation(Parser parser) {
        List<String> paragraphs = parser.parse(readInformation());
        for (String paragraph : paragraphs) {
            this.paragraphs.add(new Paragraph(parser.getNext(), paragraph));
        }
    }

    private String readInformation() {
        String line;
        StringBuilder lines = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_FILE))) {
            while ((line = bufferedReader.readLine()) != null) {
                lines.append(line).append('\n');
            }
        } catch (IOException ioException) {
            LOGGER.error("IOException");
        }
        return lines.toString();
    }

    public void writeInformation() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            byte[] buffer = toString().getBytes();
            bufferedOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            LOGGER.error("IOException");
        }
    }

    public void sortParagraphsByCountOfSentences() {
        Comparator<Paragraph> comparator = Comparator.comparing(paragraph -> paragraph.getSentences().size());
        paragraphs.sort(comparator);
    }

    public void sortSentencesByCountOfLexemes() {
        for (Paragraph paragraph : paragraphs) {
            paragraph.sortSentencesByCountOfLexemes();
        }
    }

    public void sortLexemesBySymbol(char symbol) {
        for (Paragraph paragraph : paragraphs) {
            paragraph.sortLexemesBySymbol(symbol);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Paragraph paragraph : paragraphs) {
            stringBuilder.append(paragraph.toString());
        }
        return stringBuilder.toString();
    }
}
