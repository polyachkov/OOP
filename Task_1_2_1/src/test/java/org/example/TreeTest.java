package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;



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
}
