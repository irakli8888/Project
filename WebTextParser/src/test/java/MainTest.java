import junit.framework.TestCase;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class MainTest extends TestCase{
    @Override
    protected void setUp() throws Exception {
    }

    public void testReadAndSavePage() throws Exception {
        Main.readAndSavePage("https://ru.wikipedia.org/wiki/Java");
        Parser parser=new Parser();
        String actual=Jsoup.parse(parser.parseFile("data/page.html")).text();
        String expected=Jsoup.parse(parser.parseFile("src/test/java/files/page.html")).text();
        assertEquals(expected,actual);
    }

    public void testWordCount() throws IOException, SQLException {
        Parser parser = new Parser();
        Map<String, Long> map = Main.wordCount(Jsoup.parse(parser.parseFile("src/test/java/files/test.html")));
        long expected = 27;
        long actual = 0;
        for (Map.Entry<String, Long> item : map.entrySet()) actual += item.getValue();
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
