package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * DfsIterator for Tree.
 *
 * @param <DataT>
 *
 */
public class DfsIterator<DataT> implements Iterator<Tree<DataT>> {
    private final Stack<Tree<DataT>> stack;
    private final Tree<DataT> tree;
    private final int expectedModCnt;

    /**
     * DfsIterator constructor.
     *
     * @param tree
     *
     */
    public DfsIterator(Tree<DataT> tree) {
        stack = new Stack<>();
        this.tree = tree;
        stack.push(tree);
        expectedModCnt = tree.getModCnt();
    }

    /**
     * stack getter.
     */
    public Stack<Tree<DataT>> getStack() {
        return stack;
    }

    /**
     * tree getter.
     */
    public Tree<DataT> getTree() {
        return tree;
    }

    /**
     * mod cnt ex getter.
     *
     */
    public int getExpectedModCnt() {
        return expectedModCnt;
    }

    /**
     * hasNext.
     */
    @Override
    public boolean hasNext() {
        if (expectedModCnt != tree.getModCnt()) {
            throw new ConcurrentModificationException("Был модифицирован - ошибка");
        }
        return !stack.isEmpty();
    }

    /**
     * next.
     */
    @Override
    public Tree<DataT> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Tree<DataT> current = stack.pop();
        for (int i = current.getChildren().size() - 1; i >= 0; i--) {
            stack.push(current.getChildren().get(i));
        }
        return current;
    }
}
