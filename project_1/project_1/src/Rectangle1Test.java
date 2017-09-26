/**
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-19
 *
 */
public class Rectangle1Test extends student.TestCase {

    /**
     * Test method for {@link Rectangle1#main(java.lang.String[])}.
     */
    
    public void testMain() {
        Rectangle1 test = new Rectangle1();
        String[] ourArguments = { "SyntaxTest.txt" };
        Rectangle1.main(ourArguments);
        assertEquals(ourArguments[0].compareTo("SyntaxTest.txt"), 0);
        assertNotNull(test);
    }

}
