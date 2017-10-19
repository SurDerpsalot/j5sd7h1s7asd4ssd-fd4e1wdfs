import java.util.ArrayList;

/**
 * 
 * @author m1newc
 * @version 0.1
 *
 */
public class PRQuadTree {

    private BucketNode root; //the root of the PRQuadTree
    private BucketNode temp; //a BucketNode instance accessible
                              //from the outside
    private ArrayList<BucketNode> tempArray = new ArrayList<BucketNode>();
                                     //array of BucketNodes
    private int level; //level of the node, used to determine the 
                           //indentation of Dump
    private int nodes; //number of nodes returned from the dump or regionsearch
    /**
     * the last treeNode that was removed
     */
    TreeNode deletedTreeNode; 
    /**
     * default constructor
     */
    public PRQuadTree() {
        root = new BucketNode(0, 1024, 0, 1024);
        temp = null;
   //     min = 0;
   //     max = 1024;
        level = 0;
        nodes = 0;
    };

    /**
    * PRQuadTree constructor given a BucketNode
    * @param rt  is the root node you want to give the PRQuadTree
    */
    public PRQuadTree(BucketNode rt) {
        root = rt;
    };

   ///////////////////////////////////////////////
   // Set values
   ///////////////////////////////////////////////
   
    /**
    * set the root node with the parameters of 
    * an existing BucketNode. 
    * @param t is the existing BucketNode to copy
    *
    public void setRootNode(BucketNode t) {
        root = new BucketNode(t);
    };
*/
    /**
    * set the root node with the parameters of an existing BucketNode.
    * 
    * @param xMin is the minimum value of x
    * @param xMax is the maximum value of x
    * @param yMin is the minimum value of y
    * @param yMax is the maximum value of y
    */
    public void setRootNode(double xMin, double xMax, 
            double yMin, double yMax) {
        temp = new BucketNode(xMin, xMax, yMin, yMax);
    };

    /**
    * set the temp node with the parameters of an existing BucketNode.
    * 
    * @param t is the BucketNode to copy
    */
    public void setTempNode(BucketNode t) {
        temp = t;
    };

    /**
    * set the temp node with the parameters of an existing BucketNode.
    * 
    * @param xMin is the minimum value of x
    * @param xMax is the maximum value of x
    * @param yMin is the minimum value of y
    * @param yMax is the maximum value of y
    */
    public void setTempNode(double xMin, double xMax, 
            double yMin, double yMax) {
        temp = new BucketNode(xMin, xMax, yMin, yMax);
    };
    /**
    * point the temporary array to an existing array of BucketNodes
    * 
    * @param e an array of BucketNodes
    */
    public void pushTempArray(BucketNode e) {
        tempArray.add(e);
    };

   ///////////////////////////////////////////////
   // Get values
   ///////////////////////////////////////////////
   /**
    * returns the number of nodes in the PRQuadTree that
    * have been dumped
    * @return nodes
    */
    public int getNodes() {
        return nodes;
    };
   
    /**
    * get the pointer to the PRQuadTree's tempArray.
    * 
    * @return tempArray
    */
    public ArrayList<BucketNode> getTempArray() {
        return tempArray;
    };
   
   /**
    * get the value of the PRQuadTree tree's root.
    * 
    * @return BucketNode root.
    */
    public BucketNode getRoot() {
        return root;
    };

    /**
    * get the value of the PRQuadTree tree's temp.
    * 
    * @return BucketNode temp.
    */
    public BucketNode getTemp() {
        return temp;
    };
   
   ///////////////////////////////////////////////
   // Modification Functions
   ///////////////////////////////////////////////
   
   /**
    *  Inserts a point into the PRQuadTree
    * @param name is the name of the point being inserted
    * @param x is the x coordinate of the point being inserted
    * @param y is the y coordinate of the point being inserted
    */
    public void insertPoint(String name, double x, double y) {
        TreeNode newNode = new TreeNode(name, x, y);
        insert(root, newNode);
    };
    /**
     * Determines if the bucket can add the new TreeNode without overflowing
     * @param rt the current bucketNode
     * @param newNode the TreeNode to add
     * @return if the bucket is too full
     */
    private boolean isBucketTooFull(BucketNode rt, TreeNode newNode) {
        if (rt.bucketList.size() <= 3) {
            int count = 0;
            boolean isRepeat = false;
            for (int i = 0; i < rt.bucketList.size(); i++) {
                count = count + rt.bucketList.get(i).size;
                if (newNode.getX() == 
                    rt.bucketList.get(i).getData().getX()
                    && newNode.getY() == 
                    rt.bucketList.get(i).getData().getY()) {
                    isRepeat = true;
                }
            }
            if (isRepeat || count < 3) {
                return false;
            } 
        }
        return true;
    }
    /**
    * 
    * @param rt is the root of the PRQuad tree
    * @param newNode is the new point element to be added
    */
    public void insert(BucketNode rt, TreeNode newNode) {
        boolean inserted = false;
        if (rt.getIsInternalNode()) {
            double xRange = rt.getXMax() - rt.getXMin();
            double yRange = rt.getYMax() - rt.getYMin();
            double xMidpoint = rt.getXMin() + (xRange / 2);
            double yMidpoint = rt.getYMin() + (yRange / 2);
            if (newNode.getX() > rt.getXMax() 
                    || newNode.getX() < rt.getXMin()
                    || newNode.getY() > rt.getYMax() 
                    || newNode.getY() < rt.getYMin()) {
                //TODO: fixthis
            }
            
            
            //goes in the NE quadrant
            else if (newNode.getX() >= xMidpoint && 
                    newNode.getY() < yMidpoint) {
                insert(rt.getNE(), newNode);
            }
            //goes in the NW quadrant
            else if (newNode.getX() < xMidpoint && 
                    newNode.getY() < yMidpoint) {
                   //NW corner may have values
                insert(rt.getNW(), newNode);
            }
            //goes in the SW quadrant
            else if (newNode.getX() < xMidpoint && 
                       newNode.getY() >= yMidpoint) {
                   
                insert(rt.getSW(), newNode);
            }
            //goes in the SE quadrant
            else if (newNode.getX() >= xMidpoint && 
                       newNode.getY() >= yMidpoint) {
                insert(rt.getSE(), newNode);
            }
        }
        else if (!isBucketTooFull(rt, newNode))
        {
            LinkedList link = new LinkedList();
            if (rt.bucketList.size() == 0)
            {
                link.insertAtEnd(newNode);
                rt.bucketList.add(link);
            }
            else
            {
                for (int i = 0; i < rt.bucketList.size(); i++)
                {
                    if (rt.bucketList.get(i).getData().getX() 
                        == newNode.getX() && 
                        rt.bucketList.get(i).getData().getY() 
                        == newNode.getY())
                    {
                        inserted = true;
                        rt.bucketList.get(i).insertAtEnd(newNode);
                    }
                }
                if (!inserted)
                {
                    link.insertAtEnd(newNode);
                    rt.bucketList.add(link);
                }
            }           
        }
        else
        {
            createNewLevel(rt, newNode);
        }
    }

    /**
    * Returns true if the current level in the PRQuad tree 
    * is able to contract its child
    * @param rt is the current level of the PRQuad tree
    * @return if it is contractible
    */
    private boolean isContractible(BucketNode rt) {
        if (!rt.getIsInternalNode()) {
            return false;
        }
        int numberofChildren = 0;
        int numberofNodes = 0;
        if (rt.getNE() != null && rt.getNE().getTreeNodeCount() > 0) {
            numberofChildren++;
            numberofNodes = numberofNodes + rt.getNE().getTreeNodeCount();
        }
        if (rt.getNW() != null && rt.getNW().getTreeNodeCount() > 0) {
            numberofChildren++;
            numberofNodes = numberofNodes + rt.getNW().getTreeNodeCount();
        }
        if (rt.getSE() != null && rt.getSE().getTreeNodeCount() > 0) {
            numberofChildren++;
            numberofNodes = numberofNodes + rt.getSE().getTreeNodeCount();
        }
        if (rt.getSW() != null && rt.getSW().getTreeNodeCount() > 0) {
            numberofChildren++;
            numberofNodes = numberofNodes + rt.getSW().getTreeNodeCount();
        }
        return (numberofChildren == 1 || numberofNodes <= 3);
    }
    /**
    * Contracts the tree at a given root.
    * @param rt is the root node from which you check if it can contract
    */
    public void contractTreeAtNode(BucketNode rt) {
        if (rt.getNW() != null) {
            rt = rt.getNW();
        }
        else if (rt.getNE() != null) {
            rt = rt.getNE();
        }
        else if (rt.getSE() != null) {
            rt = rt.getSE();
        }
        else if (rt.getSW() != null) {
            rt = rt.getSW();
        }
    }
    /**
    * searches for the first node with the characteristics from findNode
    * and deletes it
    * @param findNode has the values of the node we want to delete
    * @return true if a node was deleted, false if it could not be found
    */
    public boolean deleteSearch(TreeNode findNode) {
        if (deleteSearchRecursive(findNode, root)) {
           // System.out.print("Found");
            if (isContractible(root)) {
                contractTreeAtNode(root);
            }
            return true;
        }
        else {
            //System.out.print("Not Found");            
            return false;
        }
    }
    /**
     * the recursive search for a node to delete
     * @param findNode is the value set to delete
     * @param rt is the root of the tree to search
     * @return true if a node was deleted
     */
    private boolean deleteSearchRecursive(TreeNode findNode, 
            BucketNode rt) {
        if (rt == null) {
            return false;
        }
        boolean deletion = false;
        double xRange = rt.getXMax() - rt.getXMin();
        double yRange = rt.getYMax() - rt.getYMin();
        double xMidpoint = rt.getXMin() + (xRange / 2);
        double yMidpoint = rt.getYMin() + (yRange / 2);
        if (!rt.getIsInternalNode()) { //leaf node
            for (int i = 0; i < rt.bucketList.size(); i++) { 
                int j = rt.bucketList.get(i).findPoint(findNode.getName());
                if ((j >= 0 || findNode.getName().compareTo("") == 0) && 
                       rt.bucketList.get(i).getData(j).getX() ==
                       findNode.getX() &&
                       rt.bucketList.get(i).getData(j).getY() ==
                       findNode.getY()) {
                    deletedTreeNode = rt.bucketList.get(i).getData(j);
                    rt.bucketList.get(i).deleteAtPos(j);
                    return true;
                }
            }                   
            return false;
        }
        else {            //go to the child that has the right values
            //west
            if (findNode.getX() < xMidpoint) {
               //north
                if (findNode.getY() < yMidpoint) {
                    deletion = deleteSearchRecursive(findNode, rt.getNW());
                }
                else { //south
                    deletion = deleteSearchRecursive(findNode, rt.getSW());
                }
            }
            else { //east
                //north
                if (findNode.getY() < yMidpoint) {
                    deletion = deleteSearchRecursive(findNode, rt.getNE());
                }
                else { //south
                    deletion = deleteSearchRecursive(findNode, rt.getSE());
                }
            }
        }
        if (isContractible(rt)) {
            contractTreeAtNode(rt);
        }
        return deletion;
    }
   
    /**
     * This is the function called to perform a region search
     * @param minX is the minimum x coordinate
     * @param maxX is the maximum x coordinate
     * @param minY is the minimum y coordinate
     * @param maxY is the maximum y coordinate
     * @return the number of nodes found in this region
     */
    public int regionSearch(double minX, double maxX,
            double minY, double maxY) {
        nodes = 0;
        System.out.print("Points Intersecting Region: ");
        System.out.printf("(%.0f, %.0f, %.0f, %.0f)\n", 
                minX, minY, maxX - minX, maxY - minY);
        ArrayList<TreeNode> results = regionSearchRecursive(
                root, minX, maxX, minY, maxY);
        int maxLevel = results.size();
        for (int i = 0; i < maxLevel; i++) {
            System.out.printf("(%s, %.0f, %.0f)\n", 
                    results.get(i).getName(),
                    results.get(i).getX(),
                    results.get(i).getY());           
        }
        System.out.print(nodes + " QuadTree Nodes Visited\n");
        return maxLevel;
    }
    /**
     * Checks if a node is in the sought range
    *@param node the values for the node
    * @param minX is the minimum x in the sought range
    * @param maxX is the maximum x in the sought range
    * @param minY is the minimum y in the sought range
    * @param maxY is the maximum y in the sought range
    * @return true if the node is in the e=intended range
    */
    private boolean nodeInRange(TreeNode node, double minX, double maxX,
           double minY, double maxY) {
        return (node.getX() <= maxX 
               && node.getX() >= minX 
               && node.getY() <= maxY
               && node.getY() >= minY);
    }
   /**
    * the recursive search for nodes that exist in a given range
    * @param rt the root node
    * @param minX the region search's minimum x value
    * @param maxX the region search's maximum x value
    * @param minY the region search's minimum y value
    * @param maxY the region search's maximum y value
    * @return the number of QuadTree Nodes hit
    */
    private ArrayList<TreeNode> regionSearchRecursive(BucketNode rt, 
           double minX, double maxX,
           double minY, double maxY) {
        ArrayList<TreeNode> nodesInRegion = new ArrayList<TreeNode>();
        nodes++;
        if (!rt.getIsInternalNode()) {
            for (int i = 0; i < rt.bucketList.size(); i++) {
                for (int j = 0; j < rt.bucketList.get(i).getSize(); j++)
                {
                    if (nodeInRange(rt.bucketList.get(i).getData(j), 
                           minX, maxX, minY, maxY)) {
                        nodesInRegion.add(rt.bucketList.get(i).getData(j));
                 //      System.out.print("Found in RegionSearch.\n");
                    }
                }
            }
        }
        else {
            double xRange = rt.getXMax() - rt.getXMin();
            double yRange = rt.getYMax() - rt.getYMin();
            double xMidpoint = rt.getXMin() + (xRange / 2);
            double yMidpoint = rt.getYMin() + (yRange / 2);
            if (minX < xMidpoint && minY < yMidpoint) {
                //NW corner may have values
                nodesInRegion.addAll(regionSearchRecursive(rt.getNW(), 
                        minX, maxX, minY, maxY));
            }
            if (minX < xMidpoint && maxY >= yMidpoint) {
                //SW corner may have values
                nodesInRegion.addAll(regionSearchRecursive(rt.getSW(), 
                        minX, maxX, minY, maxY));
            }
            if (maxX >= xMidpoint && minY < yMidpoint) {
                //NE corner may have values
                nodesInRegion.addAll(regionSearchRecursive(rt.getNE(), 
                        minX, maxX, minY, maxY));
            }
            if (maxX >= xMidpoint && maxY >= yMidpoint) {
                //SE corner may have values
                nodesInRegion.addAll(regionSearchRecursive(rt.getSE(), 
                        minX, maxX, minY, maxY));
            }
        }
        return nodesInRegion;
    }
    /**
     *     Takes a full BucketNode and breaks it down to be an internal
     *  node and create a new level under it containing the original points.
     * @param rt is the node that needs to be broken down
     * @param newNode is the node being inserted into the tree
     */
    public void createNewLevel(BucketNode rt, TreeNode newNode) {
        rt.isInternalNode = true;
        double xRange = rt.getXMax() - rt.getXMin();
        double yRange = rt.getYMax() - rt.getYMin();
        double xMidpoint = rt.getXMin() + (xRange / 2);
        double yMidpoint = rt.getYMin() + (yRange / 2);

        rt.nE = new BucketNode(xMidpoint, rt.getXMax(), 
                rt.getYMin(), yMidpoint);
        rt.nW = new BucketNode(rt.getXMin(), xMidpoint, 
                rt.getYMin(), yMidpoint);
        rt.sW = new BucketNode(rt.getXMin(), xMidpoint, 
                yMidpoint, rt.getYMax());
        rt.sE = new BucketNode(xMidpoint, rt.getXMax(), 
                yMidpoint, rt.getYMax());      
        for (int i = rt.bucketList.size() - 1; i >= 0; i--) {
            for (int j = 0; j < rt.bucketList.get(i).getSize(); j++)
            {
                insert(rt, rt.bucketList.get(i).getData(j));
            }
            rt.bucketList.remove(i);
       }
       insert(rt, newNode);
    }
   
     /**
      * Call this function before the dump function. It resets the indentation
      * variable as well as a counter
      */
    public void preDumpQuadTree() {
        level = 0;
        nodes = 0;
        dumpQuadTree(root);
        System.out.print("QuadTree Size: " + nodes + 
                " QuadTree Nodes Printed.\n");
    }
   
    /**
     * Produces a dump of the QuadTree's nodes
     * @param rt is the node being passed in.
     */
    public void dumpQuadTree(BucketNode rt) {
        for (double i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.print("Node at " 
               + rt.getXMin() + ", " 
               + rt.getYMin() + ", "
               + (rt.getXMax() - rt.getXMin()) + ": ");
        nodes = nodes + 1;
        if (rt.getIsInternalNode()) {
            System.out.print("Internal\n");
            level = level + 1;
            dumpQuadTree(rt.getNW());
            dumpQuadTree(rt.getNE());
            dumpQuadTree(rt.getSW());
            dumpQuadTree(rt.getSE());
            level = level - 1;
        }
        else
        {
            int bucketSize = rt.bucketList.size();
            if (bucketSize == 0) {
                System.out.print("Empty\n");
            }
            else if (bucketSize > 3) {
                System.out.print("error!\n");
            }
            else
            {
                for (int i = 0; i < rt.bucketList.size() ; i++)
                {
                    for (int j = 0; j < rt.bucketList.get(i).getSize(); j++)
                    {
                        System.out.print("\n");
                        for (double k = 0; k < level; k++) {
                            System.out.print("  ");
                        }
                        System.out.print("("
                                + rt.bucketList.get(i).getData(j).getName()
                                + ", "
                                + rt.bucketList.get(i).getData(j).getX()
                                + ", "
                                + rt.bucketList.get(i).getData(j).getY()
                                + ")");
                    }
                }
                System.out.print("\n");
            }
        }
    }   
    /**
     * runs the duplicate command starting at the root.
     */
    public void findDuplicates() {
        duplicates(root);        
    }
    /**
     * Traverses the Tree through all nodes that are not empty and 
     * compares the points inside for duplication.
     * @param rt is the root node
     */
    public void duplicates(BucketNode rt) {
        if (rt.isInternalNode) 
        {
            if (rt.getNE().bucketList.size() > 0 || 
                rt.getNE().getIsInternalNode())
            {
                duplicates(rt.getNE());
            }
            if (rt.getNW().bucketList.size() > 0 || 
                rt.getNW().getIsInternalNode())
            {
                duplicates(rt.getNW());
            }
            if (rt.getSE().bucketList.size() > 0 || 
                rt.getSE().getIsInternalNode())
            {
                duplicates(rt.getSE());
            }
            if (rt.getSW().bucketList.size() > 0 || 
                rt.getSW().getIsInternalNode())
            {
                duplicates(rt.getSW());
            }
        }
        else 
        {
            for (int i = 0; i < rt.bucketList.size(); i++)
            {
                if (rt.bucketList.get(i).getSize() > 1)
                {
                    System.out.println("("
                            + rt.bucketList.get(i).getData().getX()
                            + ", "
                            + rt.bucketList.get(i).getData().getY() + ")");
                }
            }  
        }
    }
/////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////
    /**
     * This class is the instance of a node in the PRQuadTree
     * @author m1newc and bfin96
     *
     */
    class BucketNode {
        private ArrayList<LinkedList> bucketList;
        private BucketNode nE;
        private BucketNode nW;
        private BucketNode sE;
        private BucketNode sW;
        private double xMin;
        private double xMax;
        private double yMin;
        private double yMax;
        private boolean isInternalNode;
        
        public int getTreeNodeCount() {
            int count = bucketList.size();
            for (int i = 0; i < bucketList.size(); i++) {
                count = count + bucketList.get(i).size;
            }
            return count;
        }
        /**
         * This is the basic constructor
         * @param minX is the minimum x value
         * @param maxX is the maximum x value
         * @param minY is the minimum y value
         * @param maxY is the maximum y value
         */
        public BucketNode(double minX, double maxX, double minY,
                double maxY) {
            nE = null;
            nW = null;
            sE = null;
            sW = null;
            xMin = minX;
            xMax = maxX;
            yMin = minY;
            yMax = maxY;
            isInternalNode = false;
            bucketList = new ArrayList<LinkedList>();
        }

        /**
         * This is the copy constructor
         * @param t the node to be copied
        public BucketNode(BucketNode t) {
            nE = t.getNE();
            nW = t.getNW();
            sE = t.getSE();
            sW = t.getSW();
            xMin = t.getXMin();
            xMax = t.getXMax();
            yMin = t.getYMin();
            yMax = t.getYMax();
            isInternalNode = t.getIsInternalNode();
        }*/
        ///////////////////////////////////////////////
        // Get values
        /**
         * 
         * @return the minimum x value
         */
        public double getXMin() {
            return xMin;
        }
        /**
         * 
         * @return the maximum x value
         */
        public double getXMax() {
            return xMax;
        }
        /**
         * 
         * @return the minimum y value
         */
        public double getYMin() {
            return yMin;
        }
        /**
         * 
         * @return the maximum y value
         */
        public double getYMax() { 
            return yMax;
        }
        /**
         * 
         * @return the SW bucketnode
         */
        public BucketNode getSW() {
            return sW;
        }
        /**
         * 
         * @return the SE bucketnode
         */
        public BucketNode getSE() {
            return sE;
        }
        /**
         * 
         * @return the NW bucketnode
         */
        public BucketNode getNW() {
            return nW;
        }
        /**
         * 
         * @return the NE bucketnode
         */
        public BucketNode getNE() {
            return nE;
        }
        /**
         * 
         * @return if the node is an internal node or not
         */
        public boolean getIsInternalNode() {
            return isInternalNode;
        }
        /**
         * 
         * @return the arraylist of linkedlists of points in the tree
         */
        public ArrayList<LinkedList> getBucket() {
            return bucketList;
        }

    }  
    
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /**
     * These are the supporting nodes to be used in the LinkedList
     * @author 
     *
     */
    class PointNode
    {
        private TreeNode data;
        private PointNode link;
     
        /**
         * This is the basic constructor of PointNode
         */
        public PointNode()
        {
            link = null;
            data = null;
        }    
        /**
         * This constructor builds a non-empty PointNode
         * @param node is the TreeNode to be stored in the LinkedList
         * @param ptr is the pointer to the next element in the LinkedList
         */
        public PointNode(TreeNode node, PointNode ptr)
        {
            data = node;
            link = ptr;
        }    
        /**
         * This function sets the link to the next node
         * @param ptr the node to be linked to
         */
        public void setLink(PointNode ptr)
        {
            link = ptr;
        }    
        /**
         * This function sets the TreeNode in the current node  
         * @param node the TreeNode to be stored
         */
        public void setData(TreeNode node)
        {
            data = node;
        }    
        /**
         * This function gets the link to next node in the LinkedList
         * @return the link to the next node
         */
        public PointNode getLink()
        {
            return link;
        }    
        /**
         * This function retrieves the data from the current node
         * @return the TreeNode stored in the current node
         */
        public TreeNode getData()
        {
            return data;
        }
    }
     
    /**
     * This is the custom LinkedList class for Project 2.
     * It is designed to hold PointNodes, which are just
     * elements of the LinkList designed to hold TreeNodes.
     * @author Brad bfin96
     *
     */
    class LinkedList
    {
        private PointNode start;
        private PointNode end;
        private int size;
     
        /**
         * This is the basic constructor of the LinkedList
         */
        public LinkedList()
        {
            start = null;
            end = null;
            size = 0;
        }
        /**
         * This function checks if the LinkedList is empty
         * @return a boolean positive if the List is empty
         */
        public boolean isEmpty()
        {
            return start == null;
        }
        /**
         * This function returns the size
         * @return the size of the LinkedList
         */
        public int getSize()
        {
            return size;
        }
        /**
         * This function inserts a TreeNode at the end of the LinkedList
         * @param node is the node to be inserted
         */
        public void insertAtEnd(TreeNode node)
        {
            PointNode newPtr = new PointNode(node, null);    
            size++ ;    
            if (start == null) 
            {
                start = newPtr;
                end = start;
            }
            else 
            {
                end.setLink(newPtr);
                end = newPtr;
            }
        }
        /**
         * This function deletes the TreeNode stored at a passed in position
         * @param pos is the index of the TreeNode to be deleted
         */
        public void deleteAtPos(int pos)
        {        
            if (pos < 0 || pos >= size) {
                return; //out of bounds
            }
            else if (pos == 0) 
            {
                start = start.getLink();
            }
            else if (pos + 1 == size) 
            { //last element
                PointNode s = start;
                PointNode t = start;
                while (s != end)
                {
                    t = s;
                    s = s.getLink();
                }
                end = t;
                end.setLink(null);
            }
            else { //in the middle of the list
                PointNode ptr = start;
                for (int i = 1; i < size - 1; i++) {
                    if (i == pos) {
                        PointNode tmp = ptr.getLink();
                        tmp = tmp.getLink();
                        ptr.setLink(tmp);
                        i = size;
                    }
                    ptr = ptr.getLink();
                }
            }
            size--; 
        }    
        /**
         * Gets the TreeNode from the beginning of the LinkedList
         * @return the first TreeNode stored in the LinkedList
         */
        public TreeNode getData()
        {
            return start.getData();
        }
        /**
         * Function that returns the TreeNode from a specific index in
         * the linked list.
         * @param find is the index of the data you are trying to get
         * @return the TreeNode stored at the index in the LinkedList
         */
        public TreeNode getData(int find)
        {
            PointNode ptr = start;
            for (int i = 0; i < find; i++)
            {
                ptr = ptr.getLink();
            }
            return ptr.getData();
        }
        /**
         * Function that retrieves the index of a named point in the linked
         * list. that index can be used in the DeletePos function to remove 
         * that TreeNode from the list.
         * @param name is the name of the point that is being searched for
         * @return the position in the LinkedList of the point
         */
        public int findPoint(String name)
        {
            PointNode ptr = start;
            for (int i = 0; i < size; i++) 
            {
                if (ptr.data.getName().compareTo(name) == 0) 
                {
                    return i;
                }
                ptr = ptr.getLink();
            }
            return -1;
        }
    }
}