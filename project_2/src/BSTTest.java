
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
        newTree.remove("cnode");
        newTree.remove(3, 4);
        newTree.remove("ynode");
        newTree.remove("qnode");
        newTree.remove("znode");
        newTree.remove("anode");
        newTree.remove(3, 4);
        newTree.remove(3, 4);
        newTree.remove(1, 2);
        newTree.remove(1, 2);
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
        t.setLeft(leftChild);
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
        TreeNode failure = new TreeNode("fail", -23, 10230);
        assertFalse(failure.isValid(0, 1024));
    }
    
}
