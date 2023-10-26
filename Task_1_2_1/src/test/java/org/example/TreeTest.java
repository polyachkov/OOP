package org.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TreeTest {
    private Tree<String> root;

    /**
     * Before each test.
     * @throws DataNullException
     */
    @BeforeEach
    public void setup() throws DataNullException {
        root = new Tree<>("R1");
    }

    /**
     * addchild test.
     * @throws DataNullException
     */
    @Test
    public void testAddChild() throws DataNullException {
        Tree<String> a = root.addChild("A");
        assertNotNull(a);
        assertEquals("A", a.getData());
        assertEquals(1, root.getChildren().size());
    }

    /**
     * DataNullException test.
     */
    @Test
    public void testAddChildNullData() {
        try {
            root.addChild((String) null);
            fail("Expected DataNullException"); //Если ошибка не возникла
        } catch (DataNullException e) {
            // Всё хорошо, ошибка возникла
        }
    }

    /**
     * testRemoveChild.
     * @throws DataNullException
     */
    @Test
    public void testRemoveChild() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");
        assertEquals(2, root.getChildren().size());

        a.remove();
        assertEquals(1, root.getChildren().size());
        assertNull(a.getData());
        assertNull(a.getParent());
    }

    /**
     * DfsIterator test.
     * @throws DataNullException
     */
    @Test
    public void testDFSIterator() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");
        Tree<String> c = a.addChild("C");
        Tree<String> d = a.addChild("D");

        StringBuilder result = new StringBuilder();
        for (Tree<String> node : root) {
            result.append(node.getData());
            result.append(" ");
        }
        assertEquals("R1 A C D B ", result.toString());
    }

    /**
     * BfsIterator test.
     * @throws DataNullException
     */
    @Test
    public void testBFSIterator() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");
        Tree<String> c = a.addChild("C");
        Tree<String> d = a.addChild("D");

        StringBuilder result = new StringBuilder();
        Iterator<Tree<String>> iterator = root.bfsIterator();
        while (iterator.hasNext()) {
            result.append(iterator.next().getData());
            result.append(" ");
        }
        assertEquals("R1 A B C D ", result.toString());
    }

    /**
     * some tests of setters and getters.
     * @throws DataNullException
     */
    @Test
    public void SettersGettersTest() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");

        List<Tree<String>> children = new ArrayList<Tree<String>>();

        a.setData("C");

        assertEquals(a.getData(), "C");
        assertEquals(a.getParent(), root);
        assertEquals(a.getModCnt(), 1);
        assertEquals(a.getChildren(), new ArrayList<>());
    }

    /**
     * test toString.
     * @throws DataNullException
     */
    @Test
    public void toStringTest() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");

        String expected = "Data --- R1\nChildren --- [ A B ]";
        String result = root.toString();

        assertEquals(expected, result);
    }

    /**
     * clone test.
     * @throws DataNullException
     */
    @Test
    public void cloneTest() throws DataNullException, CloneNotSupportedException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");
        Tree<String> c = a.addChild("C");
        Tree<String> d = a.addChild("D");

        Tree<String> clone = root.clone();
        assertNotSame(root, clone);

        assertEquals(root.getData(), clone.getData());

        for (int i = 0; i < root.getChildren().size(); i++) {
            assertNotSame(root.getChildren().get(i), clone.getChildren().get(i));
            assertEquals(root.getChildren().get(i).getData(), clone.getChildren().get(i).getData());
        }
    }

    /**
     * equals test.
     * @throws DataNullException
     */
    @Test
    public void equalsTest() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");

        Tree<String> root2 = new Tree<>("R1");
        Tree<String> a2 = root2.addChild("A");
        Tree<String> b2 = root2.addChild("B");

        assertEquals(root, root2);
    }
}
