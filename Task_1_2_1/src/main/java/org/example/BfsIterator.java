package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * BfsIterator for Tree.
 *
 * @param <DataType>
 *
 */
public class BfsIterator<DataType> implements Iterator<Tree<DataType>> {
    private final Tree<DataType> tree;
    private final Queue<Tree<DataType>> queue;
    private final int expectedModificationCount;

    /**
     * tree getter.
     */
    public Tree<DataType> getTree() {
        return tree;
    }

    /**
     * queue getter.
     */
    public Queue<Tree<DataType>> getQueue() {
        return queue;
    }

    /**
     * mod cnt ex getter.
     */
    public int getExpectedModificationCount() {
        return expectedModificationCount;
    }

    /**
     * BfsIterator constructor.
     *
     * @param Tree_s
     *
     */
    public BfsIterator(Tree<DataType> Tree_s) {
        queue = new LinkedList<>();
        this.tree = Tree_s;
        queue.add(Tree_s);
        expectedModificationCount = Tree_s.getModCnt();
    }

    /**
     * hasNext.
     */
    @Override
    public boolean hasNext() {
        checkConcurrentModification();
        return !queue.isEmpty();
    }

    /**
     * next.
     */
    @Override
    public Tree<DataType> next() {
        checkConcurrentModification();

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Tree<DataType> current = queue.poll();
        if (current != null) {
            queue.addAll(current.getChildren());
        }
        return current;
    }

    /**
     * exception.
     */
    private void checkConcurrentModification() {
        if (expectedModificationCount != tree.getModCnt()) {
            throw new ConcurrentModificationException("Был модифицирован - ошибка");
        }
    }
}