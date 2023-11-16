package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for DfsIterator.
 */
public class DfsIteratorTest {

    private Tree<String> root;

    /**
     * Before each test.
     *
     * @throws DataNullException
     *
     */
    @BeforeEach
    public void setup() throws DataNullException {
        root = new Tree<>("R1");
    }

    /**
     * DfsIterator test.
     *
     * @throws DataNullException
     *
     */
    @Test
    public void testDfsIterator() throws DataNullException {
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

}
