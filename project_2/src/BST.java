/**
 *
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-18 This is the base class for my implementation of a binary
 *          search tree. Several elements of this class was based on OpenDSA
 *          code. There is one subclass, the TreeNode.
 */
public class BST {

    private TreeNode root; //the root of the BST
    private TreeNode temp; //a TreeNode instance accessible from the outside
    private TreeNode[] tempArray; //an externally accessible array of TreeNodes
    private double min; //minimum value of the coordinate space
    private double max; //maximum value of the coordinate space
    private boolean removeSuccess; //global to indicate 'node removal success'

    /**
     * default constructor
     */
    public BST() {
        root = null;
        temp = null;
        min = 0;
        max = 1024;
    };

    /**
     * BST constructor given a TreeNode
     * @param rt  is the root node you want to give the BST
     */
    public BST(TreeNode rt) {
        root = rt;
    };

    /**
     * this function inserts a new TreeNode into the BST.
     * The sort key is the name field, and if the same name is given to 
     * a new instance of TreeNode, that node will be pushed to the right.
     * @param rt  the root node being called
     * @param newNode the parameters for the new node
     * @return a pointer to the root of the BST that includes the new node
     */
    public TreeNode insert(TreeNode rt, TreeNode newNode) {
        if (rt == null) {
            return newNode;
        } 
        else if (rt.getName().compareTo(newNode.getName()) > 0) {
            // the root's name is after the new one alphabetically
            rt.setLeft(insert(rt.getLeft(), newNode));
        } 
        else { // the root's name preceded that of the new node
            rt.setRight(insert(rt.getRight(), newNode));
        }
        return rt;

    };

    /**
     * sets the root node to a default constructed TreeNode.
     * This is accessible from externally.
     */
    public void setRootNode() {
        root = new TreeNode();
    };

    /**
     * set the root node with the parameters of 
     * an existing TreeNode. 
     * @param t   is the existing TreeNode to copy
     */
    public void setRootNode(TreeNode t) {
        root = new TreeNode(t);
    };

    /**
     * set the root node with the given parameters
     * 
     * @param name1 is the name of the node
     * @param x1  is the x coordinate
     * @param y1  is the y coordinate
     */
    public void setRootNode(String name1, double x1, double y1) {
        root = new TreeNode(name1, x1, y1);
    };

    /**
     * set the temp node to a default constructed TreeNode.
     */
    public void setTempNode() {
        temp = new TreeNode();
    };

    /**
     * set the temp node with the parameters of an existing TreeNode.
     * 
     * @param t   is the TreeNode to copy
     */
    public void setTempNode(TreeNode t) {
        temp = t;
    };

    /**
     * set the temp node with the given parameters
     * 
     * @param n1  is the name of the node
     * @param x1  is the x coordinate
     * @param y1  is the y coordinate
     */
    public void setTempNode(String n1, double x1, double y1) {
        temp = new TreeNode(n1, x1, y1);
    };

    /**
     * point the temporary array to an existing array of TreeNodes
     * 
     * @param t
     *            an array of TreeNodes
     */
    public void setTempArray(TreeNode[] t) {
        tempArray = t;
    };

    /**
     * get the pointer to the BST's tempArray.
     * 
     * @return tempArray
     */
    public TreeNode[] getTempArray() {
        return tempArray;
    };

    ///////////////////////////////////////////////
    // Get values
    /**
     * get the value of the BST tree's root.
     * 
     * @return TreeNode root.
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * get the value of the BST tree's temp.
     * 
     * @return TreeNode temp.
     */
    public TreeNode getTemp() {
        return temp;
    }

    //////////////////////////////////////////////////////
    // Validate node features
    /**
     * Identifies if the BST is empty.
     * 
     * @return true if the BST is empty.
     */
    public boolean isEmpty() {
        return (root == null);
    };

    /**
     * removes a single node that contains the name parameter from the BST.
     * 
     * @param name the string identifier of the rectangle
     * @return boolean true if the rectangle was found and removed
     */
    public boolean remove(String name) {
        removeSuccess = false;
        setRootNode(removeByName(root, name));
        if (root != null && root.getName() == null) {
            root = null;
        }
        return removeSuccess;
    }

    /**
     * removes a single node that contains the following name from the BST.
     * @param x   the starting x-coordinate
     * @param y   the starting y coordinate
     * @return boolean true if the rectangle was found and removed
     */
    public boolean remove(double x, double y) {
        removeSuccess = false;
        setRootNode(removeByCoordinates(root, x, y));
        if (root != null && root.getName() == null) {
            root = null;
        }
        return removeSuccess;
    }

    /**
     * Identifies if given coordinates match those of the provided TreeNode
     * 
     * @param t   is the TreeNode to compare
     * @param x   is the x coordinate to compare
     * @param y   is the y coordinate to compare
     * @return true if all the coordinates, width, and height match t.
     */
    private boolean isCoordinateMatch(TreeNode t, double x, double y) {

        return ((t.getX() == x) && (t.getY() == y));
    }
    /////////////////////////////////////////////////////////////////

    /**
     * Delete the maximum valued element in a subtree. From OpenDSA.
     * 
     * @param rt
     *            is the root of the level
     * @return the root after deletion
     */
    private TreeNode deletemax(TreeNode rt) {
        if (rt.getRight() == null) {
            return rt.getLeft();
        }
        rt.setRight(deletemax(rt.getRight()));
        return rt;
    }

    /**
     * Get the maximum valued element in a subtree. From OpenDSA.
     * 
     * @param rt
     *            is the root of the current level.
     * @return the maximum value in the subtrees of rt.
     */
    private TreeNode getmax(TreeNode rt) {
        if (rt.getRight() == null) {
            return rt;
        }
        return getmax(rt.getRight());
    }

    /**
     * remove the first TreeNode in the current root's branches that has the
     * name specified.
     * 
     * @param rt  is the current TreeNode branch
     * @param name is the String that matches on the TreeNode to be removed
     * @return the child after removal of the names TreeNode.
     */
    private TreeNode removeByName(TreeNode rt, String name) {
        if (rt == null) { // the TreeNode could not be removed in this section
            return null;
        } 
        else if (rt.getName() == null) { //the treeNode is empty
            return null;
        }
        if (rt.getName().compareTo(name) > 0) { 
            rt.setLeft(removeByName(rt.getLeft(), name));
        } 
        else if (rt.getName().compareTo(name) < 0) {
            rt.setRight(removeByName(rt.getRight(), name));
        } 
        else { // Found it
            removeSuccess = true;
            if (rt.getLeft() == null) {
                return rt.getRight();
            } 
            else if (rt.getRight() == null) {
                return rt.getLeft();
            } 
            else { // Two children
                temp = getmax(rt.getLeft());
                rt.setValue(temp);
                rt.setLeft(deletemax(rt.getLeft()));
            }
        }
        return rt;
    }

    /**
     * remove the first TreeNode in the current root's branches that has the
     * parameters specified.
     * 
     * @param rt
     *            the current TreeNode branch
     * @param x
     *            is the x coordinate to compare
     * @param y
     *            is the y coordinate to compare
     * @return the TreeNode left in that BST location after compared to the
     *         coordinates, height and width.
     */
    private TreeNode removeByCoordinates(TreeNode rt, double x, double y) {
        if (rt == null) {
            return null;
        }
        //set the leaves of the root given if there is a coordinate match or not
        if (!isCoordinateMatch(rt, x, y)) {
            rt.setLeft(removeByCoordinates(rt.getLeft(), x, y));
            rt.setRight(removeByCoordinates(rt.getRight(), x, y));
        } 
        else if (!removeSuccess) { // Found it the first time
            removeSuccess = true;
            if (rt.getLeft() == null) {
                return rt.getRight();
            } 
            else if (rt.getRight() == null) {
                return rt.getLeft();
            } 
            else { // Two children
                TreeNode temp1 = getmax(rt.getLeft());
                rt.setValue(temp1);
                rt.setLeft(deletemax(rt.getLeft()));
            }
        }
        return rt;
    }
    /**
     * searches the BST for a node with the given name
     * @param name is the name of the node to find
     */
    public void searchTree(String name) {
        setTempArray(search(getRoot(), name));
        if (getTempArray() == null || getTempArray().length == 0) {
            System.out.printf("Rectangle not found: %s\n", name);
        } 
        else {
            for (int i = 0; i < getTempArray().length; i++) {
                System.out.print("Rectangle found: ");
                System.out.printf(
                    "(%s,%.0f,%.0f)\n",
                    name, 
                    getTempArray()[i].getX(),
                    getTempArray()[i].getY());
            } 
        } 
    }
    
    /**
     * searches for all the TreeNode instances with the name provided
     * 
     * @param rt
     *            is the name of the base node
     * @param name
     *            is the name of the TreeNode we are looking for
     * @return an array of all TreeNodes with the specified name.
     */
    private TreeNode[] search(TreeNode rt, String name) {
        if (rt == null) {
            return null;
        } 
        else if (rt.getName() == null) {
            return null;
        }
        TreeNode thisValue = null;
        TreeNode[] leftChildValue = search(rt.getLeft(), name);
        TreeNode[] rightChildValue = search(rt.getRight(), name);
        int searchCount = 0; // number of nodes found
        if (rt.getName().compareTo(name) == 0) { // found it
            thisValue = new TreeNode(rt);
            searchCount++;
        }
        if (leftChildValue != null) { // the left child found some
            searchCount += leftChildValue.length;
        }
        if (rightChildValue != null) { // the right child found some
            searchCount += rightChildValue.length;
        }
        TreeNode[] all = new TreeNode[searchCount];
        if (leftChildValue != null) {
            System.arraycopy(leftChildValue, 0, all, 0, leftChildValue.length);
            if (thisValue != null) {
                all[leftChildValue.length] = thisValue;
            }
        } 
        else if (thisValue != null) {
            all[0] = thisValue;
        }
        if (rightChildValue != null) {
            System.arraycopy(rightChildValue, 0, all, searchCount 
                    - rightChildValue.length, rightChildValue.length);
        }

        return all;
    }

    /**
     * Runs an in-Order traversal of the BST starting at the root.
     * @return is the number of nodes in the BST
     */
    public int treeDump() { // (PrintWriter pw) {
        int count = inorderDump(root, 0);
        System.out.printf("BST size is %d\n", count);
        return count;
    }

    public boolean isValid(TreeNode t){
    	return t.isValid(min, max);
    }
    /**
     * performs an in-order traversal starting at the given rt, and prints
     * them.
     * 
     * @param rt  is the base of the traversal.
     * @param height is the level the node is at
     */
    private int inorderDump(TreeNode rt, int height) {
        if (rt != null) {
            int count = 0;
            count = inorderDump(rt.getLeft(), height + 1);
            if (rt.getName() == null) {
                System.out.printf("Node has depth %d, Value (null)\n",
                        height);
                return 0;
            } 
            else {
                System.out.printf(
                        "Node has depth %d, Value (%s,%.0f,%.0f)\n",
                        height, rt.getName(),
                        rt.getX(), rt.getY());
            }
            count = count + inorderDump(rt.getRight(), height + 1);
            return count + 1;
        }
        else if (rt == getRoot()) {
            System.out.println("Node has depth 0, Value (null)");            
        }
        return 0;
    }

}