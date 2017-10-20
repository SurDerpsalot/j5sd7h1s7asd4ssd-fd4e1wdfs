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
public class Point  {
    //private BST rectTree; //the instance of a BST
    private Data d;
    private Scanner sc; //the scanner for the input file
    /**
     * returns a pointer to the used instance of the Data class
     * @return the data class for this instance of Point
     */
    public Data getData() {
        return d;
    }
    
    /**
     * base constructor
     * @param filename is the input file name
     */
    public Point(String filename) {
        //this is just the constructor
        d = new Data();
        //rectTree = new BST();
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
     * beginParsing reads and executes the input file commands and runs.
     * @param filename is the name of the input file to parse
     */
    public void beginParsing(String filename) {
        try  {
           //Open our file with read/write access
            while (sc.hasNext()) { //While the scanner has information to read
                String cmd = sc.next(); //Read the next term
                d.readCommand(cmd);
            } //end while loop
            sc.close();
        } //end try block
        catch (Exception e)  {
            e.printStackTrace();
        } //end catch block
    } //end beginParsing function
    /**
     * This holds the data and tree used to organize it.
     * A BST and a PR Quad Tree.
     * @author maden
     *
     */
    class Data {
        /**
         * x coordinate
         */
        private double x;
        /**
         * y coordinate
         */
        private double y;
        /**
         * an instantiation of our BST tree
         */
        private BST rectTree;
        /**
         * an instantiation of our PRQuadTree
         */
        private PRQuadTree quadTree;
        /**
         * base constructor of the Data class
         */
        public Data() {
            rectTree = new BST();
            quadTree = new PRQuadTree();
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
                    rectTree.setTempNode(name, x, y);
                    if (rectTree.isValid(rectTree.getTemp())) {
                        System.out.print("Point Inserted: ");
                        System.out.printf("(%s, %.0f, %.0f)\n",
                                name, x, y);                      
                        rectTree.setRootNode(rectTree.insert(
                                rectTree.getRoot(), rectTree.getTemp()));
                        quadTree.insertPoint(name, x, y);
                    } 
                    else {
                        System.out.print("Point Rejected: ");
                        System.out.printf("(%s, %.0f, %.0f)\n",
                                name, x, y);                      
                    } 
                    break;
                case "remove" ://Found a remove command
                    if (sc.hasNextDouble()) {
                        x = sc.nextDouble();
                        y = sc.nextDouble();
                        //search by coordinate
                        if (quadTree.deleteSearch(new TreeNode("", x, y))) {
                            rectTree.remove(quadTree.deletedTreeNode);
                            quadTree.deleteSearch(rectTree.deletedNode);
                        }
                        else {
                            System.out.print("Point Rejected: ");
                            System.out.printf("(%.0f, %.0f)\n", x, y); 
                        }
                         
                    } 
                    else {
                        name = sc.next();
                        if (rectTree.remove(name)) {
                            if (!quadTree.deleteSearch(rectTree.deletedNode)) {
                                System.out.printf("Point Rejected: %s\n",
                                        name);                                
                            } 
                        }
                        else {
                            System.out.printf("Point Rejected: %s\n",
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
                    double h;
                    double w;
                    w = sc.nextDouble();
                    h = sc.nextDouble();
                    if (h > 0 && w > 0) { //check that parameters are valid
                        quadTree.regionSearch(x, x + w, y, y + h);
                    } 
                    else { //the region search parameters were invalid
                        System.out.printf(
                            "Invalid region: (%.0f, %.0f, %.0f, %.0f)\n",
                            x, y, w, h);
                    }
                    break;
                case "dump" : //Found a dump command
                    System.out.println("BST dump:");
                    rectTree.treeDump(); //writer
                    System.out.println("QuadTree dump:");
                    quadTree.preDumpQuadTree();
                    break;
                case "duplicates" : //Find intersections command
                    System.out.println("Duplicate Points:");
                    quadTree.findDuplicates();
                    break;
                default : //Found an unrecognized command
                    System.out.println("Unrecognized input " + cmd);
                    break;
            } //end switch
            
        }
        /**
         * gets the BST rectTree.
         * @return the BST implementation for this instance of the class
         */
        public BST getBST() {
            return rectTree;
        }
        

    }  //end of Data class
} //end of ParseData class
