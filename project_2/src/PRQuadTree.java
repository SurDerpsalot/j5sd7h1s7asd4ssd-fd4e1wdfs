/**
 * RegionSearch
 * duplicate
 * dump
 * insert
 * delete
 * @author 
 * @version 0.1
 *
 */
public class PRQuadTree {

	 private BucketNode root; //the root of the PRQuadTree
	 private BucketNode temp; //a BucketNode instance accessible from the outside
	 private BucketNode[] tempArray; //an externally accessible array of BucketNodes
	 private double min; //minimum value of the coordinate space
	 private double max; //maximum value of the coordinate space
	 private boolean removeSuccess; //global to indicate 'node removal success'
	 /**
	     * default constructor
	     */
	    public PRQuadTree() {
	        root = new BucketNode(0, 1024, 0, 1024);
	        temp = null;
	        min = 0;
	        max = 1024;
	    };

	    /**
	     * PRQuadTree constructor given a BucketNode
	     * @param rt  is the root node you want to give the PRQuadTree
	     */
	    public PRQuadTree(BucketNode rt) {
	        root = rt;
	    };

	    /**
	     * set the root node with the parameters of 
	     * an existing BucketNode. 
	     * @param t   is the existing BucketNode to copy
	     */
	    public void setRootNode(BucketNode t) {
	        root = new BucketNode(t);
	    };

	   	/**
	     * set the temp node with the parameters of an existing BucketNode.
	     * 
	     * @param t   is the BucketNode to copy
	     */
	    public void setTempNode(BucketNode t) {
	        temp = t;
	    };

	    /**
	     * point the temporary array to an existing array of BucketNodes
	     * 
	     * @param t
	     *            an array of BucketNodes
	     */
	    public void setTempArray(BucketNode[] t) {
	        tempArray = t;
	    };

	    /**
	     * get the pointer to the PRQuadTree's tempArray.
	     * 
	     * @return tempArray
	     */
	    public BucketNode[] getTempArray() {
	        return tempArray;
	    };

	    ///////////////////////////////////////////////
	    // Get values
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

	    /**
	     * 
	     * @author 
	     */
	    private class BucketNode {
	    	private BucketNode [] bucketList = {null, null, null};
	    	private BucketNode nE;
	    	private BucketNode nW;
	    	private BucketNode sE;
	    	private BucketNode sW;
	    	private double xMin;
	    	private double xMax;
	    	private double yMin;
	    	private double yMax;
	    	
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
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public double getXMin(){
	    		return xMin;
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public double getXMax(){
	    		return xMax;
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public double getYMin(){
	    		return yMin;
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public double getYMax(){
	    		return yMax;
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public BucketNode getSW(){
	    		return sW;
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public BucketNode getSE(){
	    		return sE;
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public BucketNode getNW(){
	    		return nW;
	    	}
	    	/**
	    	 * 
	    	 * @return
	    	 */
	    	public BucketNode getNE(){
	    		return nE;
	    	}
	    }
	    
}
