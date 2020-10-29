import junit.framework.TestCase;

import java.io.IOException;
import java.util.regex.Pattern;

public class ParserTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
    }
    public void testParseFile() throws IOException {
        Parser parser=new Parser();
        String expected=parser.parseFile("src/test/java/files/parserTest.txt").trim();
        String actual="parser\n" +
                "parser\n" +
                "parser\n" +
                "parser";
        assertEquals(expected,actual);
    }
    @Override
    protected void tearDown() throws Exception {
    }
}
