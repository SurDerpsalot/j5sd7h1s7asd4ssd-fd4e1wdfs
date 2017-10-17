

/**
 * 
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-19
 * Based on the Scanner code from 
 * https://canvas.vt.edu/courses/54667/modules/items/307874
 * This is the main project where rectangles are inserted & analyzed as a set.
 */
public class Point {
   /**
    * @param args command line arguments contain the input file 
    * to be parsed, and the output file name
    */
    public static void main(String[] args) {     
        String filename = args[0]; //Pass the function a full filepath
        ParseData p = new ParseData(args[0]);
        p.beginParsing(filename);
        
        
        
        
        p.close();
    }
}
