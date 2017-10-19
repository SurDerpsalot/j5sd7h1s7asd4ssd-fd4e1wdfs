//import org.junit.Test;
import java.util.ArrayList;

public class PRQuadTreeTest extends student.TestCase{

	
	public void testPRQuadTree() {
		PRQuadTree p = new PRQuadTree();
		assertNotNull(p);
	}

	
	public void testPRQuadTreeBucketNode() {
        PRQuadTree p = new PRQuadTree();
        assertNotNull(p.getRoot());     
        //set the mins and maxes
        assertEquals((int)p.getRoot().getXMin(), 0);        
        assertEquals((int)p.getRoot().getXMax(), 1024);        
        assertEquals((int)p.getRoot().getYMin(), 0);        
        assertEquals((int)p.getRoot().getYMax(), 1024); 
        //no children yet
        assertNull(p.getRoot().getNE());
        assertNull(p.getRoot().getNW());
        assertNull(p.getRoot().getSE());
        assertNull(p.getRoot().getSW());
        //not an internal node
        assertFalse(p.getRoot().getIsInternalNode());
        //the bucket is created
        assertEquals(p.getRoot().getBucket().size(), 0);
	}

	
	public void testSetRootNode() {
        PRQuadTree p = new PRQuadTree();
        assertNotNull(p.getRoot());
	}

	
	public void testSetTempNode() {
        PRQuadTree p = new PRQuadTree();
        assertNotNull(p.getRoot());     
        assertNull(p.getTemp());
        p.setTempNode(p.getRoot());
        assertNotNull(p.getTemp());
        assertEquals((int)p.getTemp().getXMin(), 0);        
        assertEquals((int)p.getTemp().getXMax(), 1024);        
        assertEquals((int)p.getTemp().getYMin(), 0);        
        assertEquals((int)p.getTemp().getYMax(), 1024); 
        
	}

	
	public void testSetTempArray() {
        PRQuadTree p = new PRQuadTree();
        p.setTempNode(2,4,1,2);
        p.setRootNode(10, 20, 30, 40);
        assertNotNull(p.getRoot());     
        p.getTempArray().add(p.getRoot());
//        p.pushTempArray(p.getRoot());
        assertNotNull(p.getTempArray());
        assertEquals(p.getTempArray().size(), 1);
        assertEquals((int)p.getTempArray().get(0).getXMin(), 0);
        assertEquals((int)p.getTempArray().get(0).getXMax(), 1024);
        assertEquals((int)p.getTempArray().get(0).getYMin(), 0);
        assertEquals((int)p.getTempArray().get(0).getYMax(), 1024);
        p.pushTempArray(p.getTemp());
        assertEquals(p.getTempArray().size(), 2);
        assertEquals((int)p.getTempArray().get(1).getXMin(), 10);
        assertEquals((int)p.getTempArray().get(1).getXMax(), 20);
        assertEquals((int)p.getTempArray().get(1).getYMin(), 30);
        assertEquals((int)p.getTempArray().get(1).getYMax(), 40);
        p.setTempNode(20,40,10,20);
        p.pushTempArray(p.getTemp());
        assertEquals(p.getTempArray().size(), 3);
        assertEquals((int)p.getTempArray().get(2).getXMin(), 20);
        assertEquals((int)p.getTempArray().get(2).getXMax(), 40);
        assertEquals((int)p.getTempArray().get(2).getYMin(), 10);
        assertEquals((int)p.getTempArray().get(2).getYMax(), 20);
	}
	
 
	public void testinsertPoint(){
        PRQuadTree p = new PRQuadTree();
        assertNotNull(p.getRoot());     
        p.insertPoint("newrootNW", 1.0, 2.0);
        p.insertPoint("newrootNE", 550.0, 3.0);
        p.insertPoint("newrootSW", 2, 552.0);
        assertEquals(p.getRoot().getBucket().size(), 3);
        assertFalse(p.getRoot().getIsInternalNode());
        assertEquals(p.getRoot().getBucket().get(0).start.getData().getName(), "newrootNW");
        assertEquals((int)p.getRoot().getBucket().get(0).start.getData().getX(), 1);
        assertEquals((int)p.getRoot().getBucket().get(0).start.getData().getY(), 2);
        assertEquals(p.getRoot().getBucket().get(1).start.getData().getName(), "newrootNE");
        assertEquals((int)p.getRoot().getBucket().get(1).start.getData().getX(), 550);
        assertEquals((int)p.getRoot().getBucket().get(1).start.getData().getY(), 3);
        assertEquals(p.getRoot().getBucket().get(2).start.getData().getName(), "newrootSW");
        assertEquals((int)p.getRoot().getBucket().get(2).start.getData().getX(), 2);
        assertEquals((int)p.getRoot().getBucket().get(2).start.getData().getY(), 552);
        //creates new children for the PRQuad tree
        p.insertPoint("newrootSE", 550.0, 603.0);
        assertTrue(p.getRoot().getIsInternalNode());
        assertEquals(p.getRoot().getBucket().size(), 0);
        assertEquals(p.getRoot().getNE().getBucket().size(), 1);
        assertNotNull(p.getRoot().getNE().getBucket().get(0).start.getData().getName());
        assertEquals(p.getRoot().getNE().getBucket().get(0).start.getData().getName(), 
                "newrootNE");
        assertEquals(p.getRoot().getSE().getBucket().size(), 1);
        assertEquals(p.getRoot().getSE().getBucket().get(0).start.getData().getName(), 
                "newrootSE");
        assertEquals(p.getRoot().getNW().getBucket().size(), 1);
        assertEquals(p.getRoot().getNW().getBucket().get(0).start.getData().getName(), 
                "newrootNW");
        assertEquals(p.getRoot().getSW().getBucket().size(), 1);
        assertEquals(p.getRoot().getSW().getBucket().get(0).start.getData().getName(), 
                "newrootSW");
        assertEquals(p.getRoot().getSE().getBucket().get(0).getSize(), 1);
        p.insertPoint("newrootSEdupe", 550.0, 603.0);
        p.insertPoint("newrootSEdupe2", 550.0, 603.0);
        assertEquals(p.getRoot().getSE().getBucket().size(), 1);
        assertEquals(p.getRoot().getSE().getBucket().get(0).getSize(), 3);
        assertEquals(p.getRoot().getSE().getBucket().get(0).start.getData().getName(), 
                "newrootSE");
        assertEquals(p.getRoot().getSE().getBucket().get(0).end.getData().getName(), 
                "newrootSEdupe2");
	}
	public void testDumpQuadTree() {
        PRQuadTree p = new PRQuadTree();
        p.insertPoint("NW0", 0, 600);
        p.insertPoint("NW1", 1, 601);
        p.insertPoint("NW2", 2, 602);
        p.insertPoint("SW2", 600, 602);
        assertTrue(p.getRoot().getIsInternalNode());
        assertFalse(p.getRoot().getNW().getIsInternalNode());
        p.preDumpQuadTree();
    }

     
    public void testDeletePoint() {
        PRQuadTree p = new PRQuadTree();
        assertNotNull(p.getRoot());     
        p.insertPoint("newrootNW", 1.0, 2.0);
        p.insertPoint("newrootNE", 550.0, 3.0);
        p.insertPoint("newrootSW", 2, 552.0);
        //creates new children for the PRQuad tree
        p.insertPoint("newrootSE", 550.0, 603.0);
        TreeNode t = new TreeNode("newrootNW", 23.0, 34.0);
        assertFalse(p.deleteSearch(t));
        TreeNode t2 = new TreeNode("newroot", 1.0, 2.0);
        assertFalse(p.deleteSearch(t2));
        TreeNode t3 = new TreeNode("newrootNW", 1.0, 2.0);
       // p.preDumpQuadTree();
        assertTrue(p.deleteSearch(t3));
        assertFalse(p.deleteSearch(t3));
        TreeNode t4 = new TreeNode("newrootNE", 550.0, 3.0);
        assertTrue(p.deleteSearch(t4));
        TreeNode t5 = new TreeNode("newrootSW", 2.0, 552.0);
        assertTrue(p.deleteSearch(t5));
        TreeNode t6 = new TreeNode("newrootSE", 550.0, 603.0);
        assertTrue(p.deleteSearch(t6));        
    }
    public void testRegionSearch() {
        PRQuadTree p = new PRQuadTree();
        p.insertPoint("newrootNW", 1.0, 2.0);
        p.insertPoint("newrootNE", 550.0, 3.0);
        p.insertPoint("newrootSW", 2, 552.0);
        p.insertPoint("newrootSE", 550.0, 603.0);
        p.insertPoint("newrootSEdupe", 550.0, 603.0);
        p.insertPoint("newrootSE2", 552.0, 603.0);
      //  p.preDumpQuadTree();
        assertEquals(p.regionSearch(0,1024,0,1024), 6);
        p.insertPoint("newrootSW2", 21, 552.0);
        p.insertPoint("newrootSW3", 500, 552.0);
        p.insertPoint("newrootSW4", 23, 552.0);
        assertEquals(p.regionSearch(0,1024,0,1024), 9);
        assertEquals(p.regionSearch(549, 1000, 549, 1024),3);
        TreeNode t = new TreeNode("newrootNW", 1.0, 2.0); 
        TreeNode t2 = new TreeNode("newrootSE2", 552.0, 603.0); 
        TreeNode t3 = new TreeNode("newrootSEdupe", 550.0, 603.0); 
        assertTrue(p.deleteSearch(t));
        assertTrue(p.deleteSearch(t2));
        assertTrue(p.deleteSearch(t3));
        //p.preDumpQuadTree();
        assertEquals(p.regionSearch(0, 1024, 0, 1024),6);
        assertEquals(p.regionSearch(549, 1000, 549, 1024),1);
    }

    public void testDuplicates() {
        PRQuadTree p = new PRQuadTree();
        p.duplicates(p.getRoot());
        p.insertPoint("NWdupe", 1.0, 2.0);
        p.insertPoint("NW", 1.0, 2.0);
        p.insertPoint("NWdupe", 1.0, 2.0);
        p.insertPoint("NWdupe2", 1.0, 2.0);
        p.insertPoint("NE", 550.0, 3.0);
        p.insertPoint("NEdupe", 550.0, 3.0);
        p.insertPoint("NEdupe", 550.0, 3.0);
        p.insertPoint("NEdupe", 550.0, 3.0);
        p.insertPoint("SW", 2, 552.0);
        p.insertPoint("SWdupe", 2, 552.0);
        p.insertPoint("SWdupe", 2, 552.0);
        p.insertPoint("SWdupe", 2, 552.0);
        p.insertPoint("SE", 550.0, 603.0);
        p.insertPoint("SEdupe", 550.0, 603.0);
        p.insertPoint("SEdupe", 550.0, 603.0);
        p.insertPoint("SE2", 552.0, 603.0);
        p.insertPoint("SE2", 552.0, 603.0);
        p.insertPoint("SE2", 552.0, 603.0);
        p.duplicates(p.getRoot());
        assertEquals(p.regionSearch(0,1024,0,1024), 18);
        p.insertPoint("SW2", 21, 552.0);
        p.insertPoint("SW3", 500, 552.0);
        p.insertPoint("SW4", 23, 552.0);
        TreeNode t = new TreeNode("NWdupe", 1.0, 2.0); 
        TreeNode t2 = new TreeNode("SE2", 552.0, 603.0); 
        TreeNode t3 = new TreeNode("SEdupe", 550.0, 603.0); 
        p.preDumpQuadTree();
        assertTrue(p.deleteSearch(t));
        p.preDumpQuadTree();
        assertTrue(p.deleteSearch(t));
        p.preDumpQuadTree();
        assertFalse(p.deleteSearch(t));
        assertEquals(p.regionSearch(0,1024,0,1024), 19);
        assertTrue(p.deleteSearch(t2));
        assertTrue(p.deleteSearch(t3));
        assertEquals(p.regionSearch(0,1024,0,1024), 17);
        TreeNode t4 = new TreeNode("NWdupe2", 1.0, 2.0); 
        assertTrue(p.deleteSearch(t4));
        p.duplicates(p.getRoot());
    }
    public void testDuplicates2() {
        PRQuadTree p = new PRQuadTree();
        p.duplicates(p.getRoot());
        p.insertPoint("NWdupe", 1.0, 2.0);
        p.insertPoint("NW2", 1.0, 22.0);
        p.insertPoint("NW2", 1.0, 22.0);
        p.insertPoint("NW3", 1.0, 23.0);
        p.insertPoint("NW4", 1.0, 24.0);
        p.insertPoint("NW", 1.0, 2.0);
        p.insertPoint("NWdupe", 1.0, 2.0);
        p.insertPoint("NWdupe2", 1.0, 2.0);
        p.insertPoint("NE", 550.0, 3.0);
        p.insertPoint("NEdupe", 550.0, 1.0);
        p.insertPoint("NEdupe", 550.0, 2.0);
        p.insertPoint("NEdupe", 550.0, 4.0);
        p.insertPoint("SW", 2, 552.0);
        p.insertPoint("SWdupe", 3, 552.0);
        p.insertPoint("SWdupe", 4, 552.0);
        p.insertPoint("SWdupe", 5, 552.0);
        p.insertPoint("SE", 550.0, 603.0);
        p.insertPoint("SEdupe", 575.0, 603.0);
        p.insertPoint("SEdupe", 550.0, 606.0);
        p.insertPoint("SE2", 600.0, 603.0);
        p.insertPoint("SE3", 552.0, 603.0);
        p.insertPoint("SE2", 552.0, 605.0);
        p.duplicates(p.getRoot());

 
    }
    
}
