package org.example;

import java.util.*;

public class BFSIterator<DataType> implements Iterator<Tree<DataType>> {
    private final Queue<Tree<DataType>> queue;
    private final int expectedModificationCount;

    public BFSIterator(Tree<DataType> Tree_s) {
        queue = new LinkedList<>();
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
        if (expectedModificationCount != modCnt) {
            throw new ConcurrentModificationException("Был модифицирован - ошибка");
        }
    }
}