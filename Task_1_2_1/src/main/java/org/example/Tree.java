package org.example;

import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;
import java.util.*;

public class Tree<DataType> {
    public Tree<DataType> parent = null;
    public DataType data;
    public List<Tree<DataType>> children;

    private int modCnt = 0; // Число модификаций дерева

    public Tree(DataType data) {
        this.data = data;
        children = new ArrayList<>();
    }

    public Tree<DataType> addChild(Tree<DataType> child) {
        if (child != null) {
            child.parent = this;
            this.children.add(child);
            modCnt++;
            return child;
        } else {
            throw new NullPointerException("child pointer is null");
        }
    }

    public Tree<DataType> addChild(DataType data) {
        Tree<DataType> child = new Tree<>(data);
        child.parent = this;
        this.children.add(child);
        modCnt++;
        return child;
    }

    public void remove() {
        if (this.parent != null) {
            this.parent.children.remove(this);
            modCnt++;
        }
        this.data = null;
        this.children = null;
    }

    public Iterator<Tree<DataType>> dfsIterator() {
        return new DFSIterator();
    }

    private class DFSIterator implements Iterator<Tree<DataType>> {
        private final Stack<Tree<DataType>> stack;
        private final int expectedModCnt;

        public DFSIterator() {
            stack = new Stack<>();
            stack.push(Tree.this);
            expectedModCnt = modCnt;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCnt != modCnt) {
                throw new ConcurrentModificationException("Был модифицирован - ошибка");
            }
            return !stack.isEmpty();
        }

        @Override
        public Tree<DataType> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Tree<DataType> current = stack.pop();
            for (int i = current.children.size() - 1; i >= 0; i--) {
                stack.push(current.children.get(i));
            }
            return current;
        }
    }

    public Iterator<Tree<DataType>> bfsIterator() {
        return new BFSIterator();
    }

    private class BFSIterator implements Iterator<Tree<DataType>> {
        private final Queue<Tree<DataType>> queue;
        private final int expectedModificationCount;

        public BFSIterator() {
            queue = new LinkedList<>();
            queue.add(Tree.this);
            expectedModificationCount = modCnt;
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
                queue.addAll(current.children);
            }
            return current;
        }

        private void checkConcurrentModification() {
            if (expectedModificationCount != modCnt) {
                throw new ConcurrentModificationException("Был модифицирован - ошибка");
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tree<DataType> some_tree = (Tree<DataType>) obj;
        return this.data.equals(some_tree.data) && this.equals_children(some_tree);
    }

    public boolean equals_children( Tree<DataType> some_tree) {
        List<Tree<DataType>> list1 = new ArrayList<>(List.copyOf(this.children));
        List<Tree<DataType>> list2 = new ArrayList<>(List.copyOf(some_tree.children));
        if (list1.size() != list2.size()) {
            return false;
        }
        for (Tree<DataType> curr : list1) {
            for (int j = 0; j < list2.size(); j++) {
                if (curr.equals(list2.get(j))) {
                    list2.remove(j);
                    break;
                } else if (j == list2.size() - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print()
    {
        System.out.println(this);
        System.out.println(this.data);
        System.out.println(this.children.toString());
        for (Tree<DataType> t : this.children) {
            t.print();
        }
    }


}
