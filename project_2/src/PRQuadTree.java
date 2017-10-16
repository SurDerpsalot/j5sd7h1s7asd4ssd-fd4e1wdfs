/**
 * RegionSearch
 * duplicate
 * dump
 * insert
 * delete
 * @author m1newc
 * @version 0.1
 *
 */
public class PRQuadTree {

     private BucketNode root; //the root of the PRQuadTree
     private BucketNode temp; //a BucketNode instance accessible
     						  //from the outside
     private BucketNode[] tempArray; //an externally accessible 
     								 //array of BucketNodes
     private double min; //minimum value of the coordinate space
     private double max; //maximum value of the coordinate space
     private double level; //level of the node, used to determine the 
     					   //indentation of Dump
     private double nodes; //number of nodes returned from the dump
     private boolean removeSuccess; //global to indicate 'node removal success'
     /**
      * default constructor
      */
     public PRQuadTree() {
         root = new BucketNode(0, 1024, 0, 1024);
         temp = null;
         min = 0;
         max = 1024;
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
     */
    public void setRootNode(BucketNode t) {
        root = new BucketNode(t);
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
     * point the temporary array to an existing array of BucketNodes
     * 
     * @param t an array of BucketNodes
     */
    public void setTempArray(BucketNode[] t) {
        tempArray = t;
    };

    ///////////////////////////////////////////////
    // Get values
    ///////////////////////////////////////////////
    /**
     * returns the number of nodes in the PRQuadTree that
     * have been dumped
     * @return nodes
     */
    public double getNodes() {
    	return nodes;
    }
    
    /**
     * get the pointer to the PRQuadTree's tempArray.
     * 
     * @return tempArray
     */
    public BucketNode[] getTempArray() {
        return tempArray;
    };
    
    /**
     * get the value of the PRQuadTree tree's root.
     * 
     * @return BucketNode root.
     */
    public BucketNode getRoot() {
        return root;
    }

    /**
     * get the value of the PRQuadTree tree's temp.
     * 
     * @return BucketNode temp.
     */
    public BucketNode getTemp() {
        return temp;
    }
    
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
    	TreeNode newNode = new TreeNode(name,x,y);
    	insert(root,newNode);
    }
    public void insert(BucketNode rt, TreeNode newNode) {
    	if(rt.getIsInternalNode())
    	{
    		//goes in the NE quadrant
    		if((newNode.getX()      == (rt.getXMax() / 2) &&
    				newNode.getY()  == (rt.getYMax() / 2)) || 
    				(newNode.getX() >  (rt.getXMax() / 2) &&
    				newNode.getY()  >= (rt.getYMax() / 2)))
    		{
    			insert(rt.getNE(), newNode);
    		}
    		//goes in the NW quadrant
    		else if((newNode.getX() == (rt.getXMax() / 2) &&
    				newNode.getY()  >  (rt.getYMax() / 2)) || 
    				(newNode.getX() <  (rt.getXMax() / 2) &&
    				newNode.getY()  >  (rt.getYMax() / 2)))
    		{
    			insert(rt.getNW(), newNode);
    		}
    		//goes in the SW quadrant
    		else if((newNode.getX() <  (rt.getXMax() / 2) &&
    				newNode.getY()  == (rt.getYMax() / 2)) || 
    				(newNode.getX() <  (rt.getXMax() / 2) &&
    				newNode.getY()  <  (rt.getYMax() / 2)))
    		{
    			insert(rt.getSW(), newNode);
    		}
    		//goes in the SE quadrant
    		else if((newNode.getX() == (rt.getXMax() / 2) &&
    				newNode.getY()  <  (rt.getYMax() / 2)) || 
    				(newNode.getX() >  (rt.getXMax() / 2) &&
    				newNode.getY()  <  (rt.getYMax() / 2)))
    		{
    			insert(rt.getSE(), newNode);
    		}
    	}
    	else if(rt.bucketList[1] != null || rt.bucketList[2] != null 
    			|| rt.bucketList[0] !=null)
    	{
    		if(rt.bucketList[0] == null) {
    			rt.bucketList[0] = newNode;
    		}
    		else if(rt.bucketList[1] == null) {
    			rt.bucketList[1] = newNode;
    		}
    		else if(rt.bucketList[2] == null) {
    			rt.bucketList[2] = newNode;
    		}
    	}
    	else
    	{
    		createNewLevel(rt, newNode);
    	}
    }
    /**
     * 	Takes a full BucketNode and breaks it down to be an internal
     *  node and create a new level under it containing the original points.
     * @param rt is the node that needs to be broken down
     */
    public void createNewLevel(BucketNode rt, TreeNode newNode) {
    	rt.isInternalNode = true;
    	rt.nE = new BucketNode((rt.getXMax() / 2), rt.getXMax(), rt.getYMin(), (rt.getYMax()/2));
    	rt.nW = new BucketNode(rt.getXMin(), (rt.getXMax() / 2), rt.getYMin(), (rt.getYMax()/2));
    	rt.sW = new BucketNode(rt.getXMin(), (rt.getXMax() / 2), (rt.getYMax() / 2), rt.getYMax());
    	rt.sE = new BucketNode((rt.getXMax() / 2), rt.getXMax(), (rt.getYMax() / 2), rt.getYMax());
    	insert(rt,rt.bucketList[0]);
    	insert(rt,rt.bucketList[1]);
    	insert(rt,rt.bucketList[2]);
    	insert(rt,newNode);
    }
    
    /**
     * Call this function before the dump function. It resets the indentation
     * variable as well as a counter
     */
    public void preDumpQuadTree() {
    	level = 0;
    	nodes = 0;
    }
    
    /**
     * Produces a dump of the QuadTree's nodes
     * @param rt is the node being passed in.
     */
    public void dumpQuadTree(BucketNode rt) {
    	for(double i = 0; i < level; i++) {
    		System.out.print("  ");
    	}
    	System.out.print("Node at "+rt.getXMin()+", "+rt.getYMin()+", "
    			+(rt.getXMin()-rt.getXMax())+": ");
    	nodes = nodes + 1;
    	if (rt.getIsInternalNode()) {
    		System.out.print("Internal\n");
    		level = level + 1;
    		dumpQuadTree(rt.getNW());
    		dumpQuadTree(rt.getNE());
    		dumpQuadTree(rt.getSW());
    		dumpQuadTree(rt.getSE());
    	}
    	else
    	{
    		if(rt.bucketList[0] == null && rt.bucketList[1] == null &&
    				rt.bucketList[2] == null) {
    			System.out.print("Empty\n");
    		}
    		else
    		{
    			if(rt.bucketList[0] !=null)
    			{
    				System.out.print("\n");
    				System.out.print("("+rt.bucketList[0].getName()+", "
    						+rt.bucketList[0].getX()+", "
    						+rt.bucketList[0].getY()+")");
    			}
    			if(rt.bucketList[1] !=null)
    			{
    				System.out.print("\n");
    				System.out.print("("+rt.bucketList[1].getName()+", "
    						+rt.bucketList[1].getX()+", "
    						+rt.bucketList[1].getY()+")");
    			}
    			if(rt.bucketList[2] !=null)
    			{
    				System.out.print("\n");
    				System.out.print("("+rt.bucketList[2].getName()+", "
    						+rt.bucketList[2].getX()+", "
    						+rt.bucketList[2].getY()+")");
    			}
    			System.out.print("\n");
    		}
    	}
    }
    
    
/////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////
    /**
     * 
     * @author 
     */
    private class BucketNode {
        private TreeNode [] bucketList = {null, null, null};
        private BucketNode nE;
        private BucketNode nW;
        private BucketNode sE;
        private BucketNode sW;
        private double xMin;
        private double xMax;
        private double yMin;
        private double yMax;
        private boolean isInternalNode;
        
        /**
         * Basic Constructor
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
        }
        
        /**
         * Copy Constructor
         */
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
        }
        ///////////////////////////////////////////////
        // Get values
        /**
         * 
         * @return
         */
        public double getXMin() {
            return xMin;
        }
        /**
         * 
         * @return
         */
        public double getXMax() {
            return xMax;
        }
        /**
         * 
         * @return
         */
        public double getYMin() {
            return yMin;
        }
        /**
         * 
         * @return
         */
        public double getYMax() { 
            return yMax;
        }
        /**
         * 
         * @return
         */
        public BucketNode getSW() {
            return sW;
        }
        /**
         * 
         * @return
         */
        public BucketNode getSE() {
            return sE;
        }
        /**
         * 
         * @return
         */
        public BucketNode getNW() {
            return nW;
        }
        /**
         * 
         * @return
         */
        public BucketNode getNE() {
            return nE;
        }
        public boolean getIsInternalNode() {
        	return isInternalNode;
        }
    }
        
}
