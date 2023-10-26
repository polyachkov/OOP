package org.example;

import java.util.*;

public class BfsIterator<DataType> implements Iterator<Tree<DataType>> {
    private final Tree<DataType> tree;
    private final Queue<Tree<DataType>> queue;
    private final int expectedModificationCount;

    public Tree<DataType> getTree() {
        return tree;
    }

    public Queue<Tree<DataType>> getQueue() {
        return queue;
    }

    public int getExpectedModificationCount() {
        return expectedModificationCount;
    }

    public BfsIterator(Tree<DataType> Tree_s) {
        queue = new LinkedList<>();
        this.tree = Tree_s;
        queue.add(Tree_s);
        expectedModificationCount = Tree_s.getModCnt();
    }

    @Override
    public boolean hasNext() {
        checkConcurrentModification();
        return !queue.isEmpty();
    }

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

    private void checkConcurrentModification() {
        if (expectedModificationCount != tree.getModCnt()) {
            throw new ConcurrentModificationException("Был модифицирован - ошибка");
        }
    }
}