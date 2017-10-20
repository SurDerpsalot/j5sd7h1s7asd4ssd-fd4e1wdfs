/**
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-19
 *
 */
public class Point1Test extends student.TestCase {

    /**
     * Test method for {@link Rectangle1#main(java.lang.String[])}.
     */
    
    public void testMain() {
        Point1 test = new Point1();
        String[] ourArguments = { "SyntaxTest.txt" };
        test.main(ourArguments);
        assertEquals(ourArguments[0].compareTo("SyntaxTest.txt"), 0);
        assertNotNull(test);
    }

}
