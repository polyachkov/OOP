package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for bfsIterator.
 */
public class BfsIteratorTest {
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
     * BfsIterator test.
     *
     * @throws DataNullException
     *
     */
    @Test
    public void testBfsIterator() throws DataNullException {
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
