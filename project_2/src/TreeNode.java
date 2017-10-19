/**
     * the binary tree node class. Each node represents a rectangle.
     * 
     * @author m1newc and bfin96
     * @version 1.0
     */
public class TreeNode {
    private double x;
    private double y;
    private String name;
    private TreeNode left;
    private TreeNode right;

    /**
     * base constructor
     */
    public TreeNode() {
        left = null;
        right = null;
        x = 0;
        y = 0;
        name = "";
    };

    /**
     * TreeNode copy constructor
     * 
     * @param t
     *            is another TreeNode
     */
    public TreeNode(TreeNode t) {
        if (t != null) {
            left = t.getLeft();
            right = t.getRight();
            x = t.getX();
            y = t.getY();
            name = t.getName();
        }
    }

    /**
     * constructor
     * 
     * @param name1 is the name of the TreeNode
     * @param x1  is the x-coordinate
     * @param y1  is the y coordinate
     */
    public TreeNode(String name1, double x1, double y1) {
        left = null;
        right = null;
        x = x1;
        y = y1;
        name = name1;
    };

    /**
     * copy construct the left child
     * 
     * @param t   is the node to copy into the left child
     */
    public void setLeft(TreeNode t) {
        left = t;
    };

    /**
     * construct the left child
     * 
     * @param name1 is the name of the rectangle
     * @param x1  is the x-coordinate
     * @param y1  is the y coordinate
     */
    public void setLeft(String name1, double x1, double y1) {
        left = new TreeNode(name1, x1, y1);
    };

    /**
     * copy construct the right child
     * 
     * @param t
     *            is the node to copy into the right child
     */
    public void setRight(TreeNode t) {
        right = t;
    };

    /**
     * construct the right child
     * 
     * @param name1 is the name of the rectangle
     * @param x1  is the x-coordinate
     * @param y1  is the y coordinate
     */
    public void setRight(String name1, double x1, double y1) {
        right = new TreeNode(name1, x1, y1);
    };

    /**
     * sets a TreeNode's values to those of another TreeNode.
     * 
     * @param t   is the TreeNode to copy
     */
    public void setValue(TreeNode t) {
        if (t == null) {
            return;
        }
        x = t.getX();
        y = t.getY();
        name = t.getName();
    }

    /**
     * set the values given explicit variables
     * @param name1 is the name of the rectangle
     * @param x1  is the x-coordinate
     * @param y1  is the y coordinate
     */
    public void setValues(String name1, double x1, double y1) {
        x = x1;
        y = y1;
        name = name1;
    };

    /**
     * checks that all boxes are constrained within the designated square
     * coordinates.
     * 
     * @param min the minimum valid value
     * @param max the maximum valid value
     * @return true if the rectangle meets project specifications.
     */
    public boolean isValid(double min, double max) {
        return (Character.isLetter(name.charAt(0))
                && (x >= min) && (y >= min)
                && (x <= max) && (y <= max));
    };

    /**
         * this determines if there is an intersect between two points by
         * finding the inner rectangle. using the maximums and minimums for the
         * ranges, we can see if the rectangles intersect by noticing that the
         * maximum of each range should be smaller than the minimum of the range
         * The return value is based on whether or not the intersection region
         * is valid, and at least one of the edges is not exactly matching the
         * opposite edge of the other rectangle.
         *
         * @param t2
         *            is the TreeNode to be compared against
         * @return true if the nodes intersect.
         *
        public boolean isNodeInterect(TreeNode t2) {
            double xLow = Math.max(getX(), t2.getX());
            double yLow = Math.max(getY(), t2.getY());
            double xHigh = Math.min(getX() + getW(), t2.getX() + t2.getW());
            double yHigh = Math.min(getY() + getH(), t2.getY() + t2.getH());
            return ((xLow < xHigh) && (yLow < yHigh) 
                    && ((getX() + getW()) != t2.getX())
                    && ((getY() + getH()) != t2.getY())
                    && ((getX() != (t2.getX() + t2.getW())) 
                    || (getY() != (t2.getY() + t2.getH()))));
        }
*/
    // get each component's value in TreeNode
    /**
     * get the base of the left child
     * 
     * @return the (TreeNode) left child
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * get the base of the right child
     * 
     * @return the (TreeNode) right child
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * get the name of the TreeNode
     * 
     * @return the (String) name
     */
    public String getName() {
        return name;
    }

    /**
     * get the lower corner x-coordinate of the rectangle
     * 
     * @return the (double) x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * get the lower corner y-coordinate of the rectangle
     * 
     * @return the (double) y-coordinate
     */
    public double getY() {
        return y;
    }


}