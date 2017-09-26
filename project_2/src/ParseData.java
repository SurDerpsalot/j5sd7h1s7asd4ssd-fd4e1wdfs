//package project_1;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.PrintWriter;
import java.util.Scanner;
/**
 * 
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-18
 *This class parses input data line by line when provided an input file name.
 *The outputs for  the parser go to the output file name provided.
 */
public class ParseData  {
    private BST rectTree; //the instance of a BST
    private Scanner sc; //the scanner for the input file
    /**
     * x coordinate
     */
    double x;
    /**
     * y coordinate
     */
    double y;
    /**
     * width
     */
    double w;
    /**
     * height
     */
    double h;
    
    /**
     * base constructor
     * @param filename is the input file name
     */
    public ParseData(String filename) {
        //this is just the constructor
        rectTree = new BST();
        try {
            sc = new Scanner(new File(filename));
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    } 
    /**
     * closes the scanner for this class
     */
    public void close() {
        sc.close();
    }
    /**
     * gets the BST rectTree.
     * @return the BST implementation for this instance of the class
     */
    public BST getBST() {
        return rectTree;
    }
    /**
     * this is the switch statement that parses the individual
     * commands.
     * @param cmd is the argument to parse
     */
    public void readCommand(String cmd) {
        rectTree.setTempNode();
        switch(cmd) {
            case "insert" ://Found an insert command
                String name = sc.next();
                x = sc.nextDouble();
                y = sc.nextDouble();
                w = sc.nextDouble();
                h = sc.nextDouble();
                rectTree.setTempNode(name, x, y, w, h);
                if (rectTree.getTemp().isValid()) {
                    System.out.print("Rectangle accepted:");
                    System.out.printf("(%s,%.0f,%.0f,%.0f,%.0f)\n",
                            name, x, y, w, h);                      
                    rectTree.setRootNode(rectTree.insert(
                            rectTree.getRoot(), rectTree.getTemp()));
                } 
                else {
                    System.out.print("Rectangle rejected:");
                    System.out.printf("(%s,%.0f,%.0f,%.0f,%.0f)\n",
                            name, x, y, w, h);                      
                } 
                break;
            case "remove" ://Found a remove command
                if (sc.hasNextDouble()) {
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    w = sc.nextDouble();
                    h = sc.nextDouble();                      
                    if  (!rectTree.remove(x, y, w, h)) {
                        System.out.print("Rectangle rejected: ");
                        System.out.printf("(%.0f,%.0f,%.0f,%.0f)\n",
                                x, y, w, h);                   
                    } 
                } 
                else {
                    name = sc.next();
                    if (!rectTree.remove(name)) {
                        System.out.printf("Rectangle rejected: %s\n",
                                name);
                    } 
                } 
                break;
            case "search" : //Found a search command
                name = sc.next();
                rectTree.searchTree(name);
                break;
            case "regionsearch" : //Found a regional search command
                x = sc.nextDouble();
                y = sc.nextDouble();
                w = sc.nextDouble();
                h = sc.nextDouble();                      
                if (h > 0 && w > 0) { //check that parameters are valid
                    System.out.print("Rectangles intersecting region");
                    System.out.printf("(%.0f, %.0f, %.0f, %.0f):\n", 
                            x, y, w, h);
                    rectTree.setTempNode("temp", x, y, w, h);
                    rectTree.setTempArray(rectTree.regionsearch(
                        rectTree.getRoot(), rectTree.getTemp()));
                    if (rectTree.getTempArray() != null) {
                        for (int i = 0; 
                            i < rectTree.getTempArray().length; i++) {
                            System.out.printf(
                                "(%s,%.0f,%.0f,%.0f,%.0f)\n",
                                rectTree.getTempArray()[i].getName(),
                                rectTree.getTempArray()[i].getX(),
                                rectTree.getTempArray()[i].getY(),
                                rectTree.getTempArray()[i].getW(),
                                rectTree.getTempArray()[i].getH());
                        } 
                    } 
                } 
                else { //the region search parameters were invalid
                    System.out.printf(
                        "Rectangle rejected: (%.0f, %.0f, %.0f, %.0f)\n",
                        x, y, w, h);
                }
                break;
            case "dump" : //Found a dump command
                System.out.println("BST dump:");
                rectTree.treeDump(); //writer
                break;
            case "intersections" : //Find intersections command
                System.out.print(rectTree.intersections());
                break;
            default : //Found an unrecognized command
                System.out.println("Unrecognized input " + cmd);
                break;
        } //end switch
        
    }
    /**
     * beginParsing reads and executes the input file commands and runs.
     * @param filename is the name of the input file to parse
     */
    public void beginParsing(String filename) {
        try  {
           //Open our file with read/write access
            while (sc.hasNext()) { //While the scanner has information to read
                String cmd = sc.next(); //Read the next term
                readCommand(cmd);
            } //end while loop
            sc.close();
        } //end try block
        catch (Exception e)  {
            e.printStackTrace();
        } //end catch block
    } //end beginParsing function


} //end of ParseData class
