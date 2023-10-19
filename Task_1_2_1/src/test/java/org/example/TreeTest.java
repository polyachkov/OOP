package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
    private Tree<String> root;

    @Test
    public void testAddChild() {
        root = new Tree<>("R1");
        Tree<String> a = root.addChild("A");
        assertNotNull(a);
        assertEquals("A", a.data);
        assertEquals(1, root.children.size());
    }

    @Test
    public void testRemove() {
        root = new Tree<>("R1");
        Tree<String> a = root.addChild("A");
        a.addChild("B");
        a.addChild("C");

        a.remove();
        assertNull(a.data);
        assertNull(a.children);
        assertEquals(0, root.children.size());
    }
}