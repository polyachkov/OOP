package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * BfsIterator for Tree.
 *
 * @param <DataT>
 *
 */
public class BfsIterator<DataT> implements Iterator<Tree<DataT>> {
    private final Tree<DataT> tree;
    private final Queue<Tree<DataT>> queue;
    private final int expectedModificationCount;

    /**
     * tree getter.
     */
    public Tree<DataT> getTree() {
        return tree;
    }

    /**
     * queue getter.
     */
    public Queue<Tree<DataT>> getQueue() {
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
     * @param TreeS
     *
     */
    public BfsIterator(Tree<DataT> TreeS) {
        queue = new LinkedList<>();
        this.tree = TreeS;
        queue.add(TreeS);
        expectedModificationCount = TreeS.getModCnt();
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
    public Tree<DataT> next() {
        checkConcurrentModification();

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Tree<DataT> current = queue.poll();
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