
/**
 *
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-18
 */
public class BSTTest extends student.TestCase {

    /**
     * test the BST tree's default implementation
     */
    
    public void testBST() {
        BST newTree = new BST();
        assertNull(newTree.getRoot());
        assertNull(newTree.getTemp());
        assertNull(newTree.getTempArray());
    }

    /**
     * test the BST construction with an existing tempNode
     */
    public void testBSTTreeNode() {
        BST newTree = new BST();
        newTree.setRootNode("newnode", 1, 2);
        BST newTree2 = new BST(newTree.getRoot());
        assertNotNull(newTree.getRoot());
        assertNotNull(newTree2.getRoot());
        assertEquals(newTree.getRoot().getX(), 
               newTree2.getRoot().getX(), 0);
        assertEquals(newTree.getRoot().getY(), 
                newTree2.getRoot().getY(), 0); 
    }

    /**
     * test the insert function
     */
    public void testInsert() {
        BST newTree = new BST();
        newTree.setRootNode("newnode", 1, 2);
        newTree.setTempNode("a_node", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("z_node", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("b_node", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("y_node", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.getRoot().getName().
                compareTo("newnode"), 0);
        assertEquals(newTree.getRoot().getLeft().
                getName().compareTo("a_node"), 0);
        assertEquals(newTree.getRoot().getRight().
                getName().compareTo("z_node"), 0);
        assertEquals(newTree.getRoot().getLeft().
                getRight().getName().compareTo("b_node"), 0);
        assertEquals(newTree.getRoot().getRight().
                getLeft().getName().compareTo("y_node"), 0);
    }

    /**
     * test setting and getting the root
     */
    public void testSetRootNode() {
        BST newTree = new BST();
        assertTrue(newTree.isEmpty());
        newTree.setRootNode();
        assertEquals((int)newTree.getRoot().getX(), 0);
        newTree.setRootNode("newnode", 1, 2);
        assertFalse(newTree.isEmpty());
        TreeNode t = newTree.getRoot();
        assertEquals(t.getName().compareTo("newnode"), 0);
    }

    /**
     * test setting the root with one argument
     */
    
    public void testSetRootNodeTreeNode() {
        BST newTree = new BST();
        assertTrue(newTree.isEmpty());
        newTree.setTempNode("temp", 1, 2);
        newTree.setRootNode(newTree.getTemp());
        assertEquals(newTree.getRoot().getName().compareTo("temp"), 0);
    }

    /**
     * test setting the root with parameters
     */
    
    public void testSetRootNodeStringDoubleDoubleDoubleDouble() {
        BST newTree = new BST();
        assertTrue(newTree.isEmpty());
        newTree.setRootNode("root", 1, 2);
        assertEquals(newTree.getRoot().getName().compareTo("root"), 0);
        assertEquals((int)newTree.getRoot().getX(), 1);
        assertEquals((int)newTree.getRoot().getY(), 2);
    }

    /**
     * test setting and getting temp node
     */
    
    public void testSetGetTempNode() {
        BST newTree = new BST();
        assertNull(newTree.getTemp());
        newTree.setTempNode();
        assertEquals((int)newTree.getTemp().getX(), 0);
        newTree.setTempNode("tempnode", 1, 2);
        assertNotNull(newTree.getTemp());
        TreeNode t = newTree.getTemp();
        assertEquals(t.getName().compareTo("tempnode"), 0);
        t = new TreeNode("test" , 3, 4);
        newTree.setTempNode(t);
        assertEquals(t.getName().compareTo("test"), 0);
    }
    
    /**
     * test setting and getting the temp array
     */
    
    public void testSetGetTempArray() {
        BST newTree = new BST();
        assertTrue(newTree.isEmpty());
        assertNull(newTree.getTempArray());
        TreeNode test = new TreeNode("test", 1, 2);
        TreeNode[] t = new TreeNode[1];
        t[0] = test;
        newTree.setTempArray(t);
        assertEquals(newTree.getTempArray()[0], t[0]);
    }
    
    /**
     * test the remove function by name and by coordinate
     */
    
    public void testRemove() {
        BST newTree = new BST();
        newTree.setRootNode("newnode", 1, 2);
        newTree.setTempNode("cnode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("ynode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("bnode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("znode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("xnode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("anode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("dnode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("aanode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.remove("ynode");
        newTree.remove("cnode");
        newTree.remove(3, 4);
        newTree.remove("qnode");
        newTree.remove("znode");
        newTree.remove("anode");
        newTree.remove(3, 4);
        newTree.remove(3, 4);
        newTree.remove(1, 2);
        newTree.remove(1, 2);
        newTree.setRootNode("RD", 3, 3);
        newTree.setTempNode("TS", 1, 1);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("FS", 2, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("AJ", 4, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("PP", 6, 6);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        TreeNode removal1 = new TreeNode("TS", 1, 1);
        TreeNode removal2 = new TreeNode("FS", 2, 2);
        TreeNode removal3 = new TreeNode("RD", 3, 3);
        TreeNode removal4 = new TreeNode("AJ", 4, 4);
        TreeNode removal5 = new TreeNode("PP", 6, 6);
        TreeNode removal6 = new TreeNode(null, 0, 0);
        newTree.isValid(removal1);
        newTree.remove(removal2);
        newTree.remove(removal1);
        newTree.remove(removal5);
        newTree.remove(removal4);
        newTree.remove(removal3);
        newTree.remove(removal6);
        newTree.setRootNode(null, 0, 0);
        newTree.treeDump();
        newTree.searchTree("TS");
        newTree.remove("RD");
        newTree.setTempNode("PP", 6, 6);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.remove("PP");
        newTree.setRootNode("hold", 0, 0);
        newTree.setTempNode("PP", 6, 6);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setRootNode(null, 0, 0);
        newTree.remove(6, 6);
        newTree.setTempNode("PP", 6, 6);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setRootNode(null);
        newTree.remove(6, 6);
        newTree.remove(removal6);
        newTree.setRootNode("g", 0, 0);
        newTree.setTempNode("c", 6, 6);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.remove("g");
        newTree.setTempNode("a", 10, 10);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.remove("c");
        newTree.remove("a");
        assertTrue(newTree.isEmpty());
    }
    
    /**
     * test the search function
     */
    
    public void testSearch() {
        BST newTree = new BST();
        newTree.setRootNode("newnode", 1, 2);
        newTree.setTempNode("cnode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("ynode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("bnode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("znode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("xnode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("anode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("anode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode();
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.searchTree("anode"), 2);
        assertEquals(newTree.searchTree("xnode"), 1);
        assertEquals(newTree.searchTree("qnode"), 0);
        
    }
    
    /**
     * test the dump function
     */
    public void testDump() {
        BST newTree = new BST();
        assertEquals(newTree.treeDump(), 0);
        newTree.setRootNode("newnode", 1, 2);
        newTree.setTempNode("cnode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.treeDump(), 2);
        newTree.setTempNode("ynode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("bnode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("znode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("xnode", 1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("anode", 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.treeDump(), 7);
        newTree.setTempNode();
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.treeDump(), 8);
    }
    
    /**
     * test TreeNode methods not used above
     */
    public void testTreeNodeSetChildren() {
        TreeNode t = new TreeNode("root", 1, 2);
        TreeNode leftChild = new TreeNode("lefty", 3, 4);
        TreeNode rightChild = new TreeNode("righty", 5, 6);
        TreeNode inval = new TreeNode("3Dawg", 10, 10);
        assertFalse(inval.isValid(0, 30));
        t.setLeft(leftChild);
        t.setValue(null);
        assertEquals(t.getLeft(), leftChild);
        t.setRight(rightChild);
        assertEquals(t.getRight(), rightChild);
        TreeNode x = new TreeNode("root", 1, 2);
        x.setLeft("left", 3, 4);
        x.setRight("right", 5, 6);
        TreeNode leftWing = new TreeNode();
        TreeNode rightWing = new TreeNode();
        leftWing.setValues("left", 3, 4);
        rightWing.setValues("right", 5, 6);
        System.out.print(x.getLeft().getName());
        System.out.print(leftWing.getName());
        assertEquals(x.getLeft().getName().compareTo(leftWing.getName()), 0);
        assertEquals(x.getRight().getName().compareTo(rightWing.getName()), 0);
        rightChild.setValue(leftChild);
        assertEquals(leftChild.getName().compareTo(rightChild.getName()), 0);
        assertTrue(t.isValid(0, 1024));
        TreeNode failure = new TreeNode("fail", 3, 10230);
        TreeNode failure2 = new TreeNode("fail", 20, 10);
        assertFalse(failure.isValid(0, 1024));
        assertTrue(failure.isValid(3, 10240));
        assertFalse(failure2.isValid(15, 30));
        assertFalse(failure2.isValid(0, 0));
        assertFalse(failure2.isValid(30, 5));
    }
    
}
