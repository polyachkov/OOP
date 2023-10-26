package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.fail;

public class TreeTest {
    private Tree<String> root;

    @BeforeEach
    public void setup() throws DataNullException {
        root = new Tree<>("R1");
    }

    @Test
    public void testAddChild() throws DataNullException {
        Tree<String> a = root.addChild("A");
        assertNotNull(a);
        assertEquals("A", a.getData());
        assertEquals(1, root.getChildren().size());
    }

    @Test
    public void testAddChildNullData() {
        try {
            root.addChild((String) null);
            fail("Expected DataNullException"); //Если ошибка не возникла
        } catch (DataNullException e) {
            // Всё хорошо, ошибка возникла
        }
    }

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
    @Test
    public void toStringTest() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");

        String expected = "Data --- R1\nChildren --- [ A B ]";
        String result = root.toString();

        assertEquals(expected, result);
    }

    @Test
    public void cloneTest() throws DataNullException {
        Tree<String> a = root.addChild("A");
        Tree<String> b = root.addChild("B");
        Tree<String> c = a.addChild("C");
        Tree<String> d = a.addChild("D");

        try {
            Tree<String> clone = root.clone();

            // Check that the cloned tree is not the same object
            assertNotSame(root, clone);

            // Check that the data is the same
            assertEquals(root.getData(), clone.getData());

            // Check that children are deep copied
            for (int i = 0; i < root.getChildren().size(); i++) {
                assertNotSame(root.getChildren().get(i), clone.getChildren().get(i));
                assertEquals(root.getChildren().get(i).getData(), clone.getChildren().get(i).getData());
            }

            // Check that the structure is the same
            assertEquals(root.toString(), clone.toString());
        } catch (CloneNotSupportedException e) {
            fail("Cloning should not throw an exception");
        }
    }

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
