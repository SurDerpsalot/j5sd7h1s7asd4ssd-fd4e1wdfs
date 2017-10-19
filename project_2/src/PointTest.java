/**
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-19
 * This is the unit test for the parser class
 */
public class PointTest extends student.TestCase {

    /**
     * Test method for {@link Point#Point()}.
     */
    
    public void testPoint() {
        Point p = new Point("SyntaxTest.txt");
       // assertNotNull(p);
        p.close();
    }

    /**
     * Test method for 
     * {@link Point#beginParsing(java.lang.String, java.lang.String)}.
     */
    public void testBeginParsing() {
        Point p = new Point("SyntaxTest.txt");
        p.beginParsing("SyntaxTest.txt");
      //  assertNotNull(p.getData().getBST().getRoot());
        p.close();
    }
    
    /**
     * tests the individual switch cases
     */
    public void testAllCases() {
        Point p = new Point("SyntaxTest.txt");
  //      assertTrue(p.getBST().isEmpty());
        p.beginParsing("SyntaxTest.txt");
      //  assertFalse(p.getBST().isEmpty());
    //    p.readCommand("12");
        p.close();
    }

}
