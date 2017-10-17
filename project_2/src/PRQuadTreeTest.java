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

	public void testinsertPoint(){
        PRQuadTree p = new PRQuadTree();
        assertNotNull(p.getRoot());     
        p.insertPoint("newrootNW", 1.0, 2.0);
        p.insertPoint("newrootNE", 550.0, 3.0);
        p.insertPoint("newrootSW", 2, 552.0);
        assertEquals(p.getRoot().getBucket().size(), 3);
        assertFalse(p.getRoot().getIsInternalNode());
        assertEquals(p.getRoot().getBucket().get(0).getName(), "newrootNW");
        assertEquals((int)p.getRoot().getBucket().get(0).getX(), 1);
        assertEquals((int)p.getRoot().getBucket().get(0).getY(), 2);
        assertEquals(p.getRoot().getBucket().get(1).getName(), "newrootNE");
        assertEquals((int)p.getRoot().getBucket().get(1).getX(), 550);
        assertEquals((int)p.getRoot().getBucket().get(1).getY(), 3);
        assertEquals(p.getRoot().getBucket().get(2).getName(), "newrootSW");
        assertEquals((int)p.getRoot().getBucket().get(2).getX(), 2);
        assertEquals((int)p.getRoot().getBucket().get(2).getY(), 552);
        //creates new children for the PRQuad tree
        p.insertPoint("newrootSE", 550.0, 603.0);
        assertTrue(p.getRoot().getIsInternalNode());
        assertEquals(p.getRoot().getBucket().size(), 0);
        assertEquals(p.getRoot().getNE().getBucket().size(), 1);
        assertNotNull(p.getRoot().getNE().getBucket().get(0).getName());
        assertEquals(p.getRoot().getNE().getBucket().get(0).getName(), 
                "newrootNE");
        assertEquals(p.getRoot().getSE().getBucket().size(), 1);
        assertEquals(p.getRoot().getSE().getBucket().get(0).getName(), 
                "newrootSE");
        assertEquals(p.getRoot().getNW().getBucket().size(), 1);
        assertEquals(p.getRoot().getNW().getBucket().get(0).getName(), 
                "newrootNW");
        assertEquals(p.getRoot().getSW().getBucket().size(), 1);
        assertEquals(p.getRoot().getSW().getBucket().get(0).getName(), 
                "newrootSW");
	}
	
    
}
