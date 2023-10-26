package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * DfsIterator for Tree.
 * @param <DataType>
 */
public class DfsIterator<DataType> implements Iterator<Tree<DataType>> {
    private final Stack<Tree<DataType>> stack;
    private final Tree<DataType> tree;
    private final int expectedModCnt;

    /**
     * DfsIterator constructor.
     * @param tree
     */
    public DfsIterator(Tree<DataType> tree) {
        stack = new Stack<>();
        this.tree = tree;
        stack.push(tree);
        expectedModCnt = tree.getModCnt();
    }

    /**
     * stack getter.
     * @return
     */
    public Stack<Tree<DataType>> getStack() {
        return stack;
    }

    /**
     * tree getter.
     * @return
     */
    public Tree<DataType> getTree() {
        return tree;
    }

    /**
     * mod cnt ex getter.
     * @return
     */
    public int getExpectedModCnt() {
        return expectedModCnt;
    }

    /**
     * hasNext.
     * @return
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
     * @return
     */
    @Override
    public Tree<DataType> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Tree<DataType> current = stack.pop();
        for (int i = current.getChildren().size() - 1; i >= 0; i--) {
            stack.push(current.getChildren().get(i));
        }
        return current;
    }
}
