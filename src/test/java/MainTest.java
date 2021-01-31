import com.epam.jwd.app.Information;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.epam.jwd.parsers.LexemeParser;
import com.epam.jwd.parsers.ParagraphParser;
import com.epam.jwd.parsers.Parser;
import com.epam.jwd.parsers.SentenceParser;

public class MainTest {
    Information information;

    @Before
    public void initTest() {
        Parser parser = ParagraphParser.getINSTANCE();
        parser.linkWith(SentenceParser.getINSTANCE())
                .linkWith(LexemeParser.getINSTANCE());
        information = new Information(parser);
    }

    @Test
    public void GeneralTest() {
    }

    @Test
    public void sortingByCountOfSentencesTest() {
        information.sortParagraphsByCountOfSentences();
    }

    @Test
    public void sortingByCountOfLexemesTest() {
        information.sortSentencesByCountOfLexemes();
    }

    @Test
    public void sortingByCountOfSymbolTest() {
        information.sortLexemesBySymbol('a');
    }

    @After
    public void writeResult() {
        information.writeInformation();
    }
}
