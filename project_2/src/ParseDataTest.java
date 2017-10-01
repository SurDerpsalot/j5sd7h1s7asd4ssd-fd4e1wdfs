/**
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-19
 * This is the unit test for the parser class
 */
public class ParseDataTest extends student.TestCase {

    /**
     * Test method for {@link ParseData#ParseData()}.
     */
    
    public void testParseData() {
        ParseData p = new ParseData("SyntaxTest.txt");
       // assertNotNull(p);
        p.close();
    }

    /**
     * Test method for 
     * {@link ParseData#beginParsing(java.lang.String, java.lang.String)}.
     */
    public void testBeginParsing() {
        ParseData p = new ParseData("SyntaxTest.txt");
        p.beginParsing("SyntaxTest.txt");
      //  assertNotNull(p.getData().getBST().getRoot());
        p.close();
    }
    
    /**
     * tests the individual switch cases
     */
    public void testAllCases() {
        ParseData p = new ParseData("SyntaxTest.txt");
  //      assertTrue(p.getBST().isEmpty());
        p.beginParsing("SyntaxTest.txt");
      //  assertFalse(p.getBST().isEmpty());
    //    p.readCommand("12");
        p.close();
    }

}
