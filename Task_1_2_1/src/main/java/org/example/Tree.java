package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Tree class.
 *
 * @param <DataT>
 *
 */
public class Tree<DataT> implements Iterable<Tree<DataT>>, Cloneable {
    private Tree<DataT> parent = null;
    private DataT data;
    private List<Tree<DataT>> children;

    private int modCnt = 0; // Число модификаций дерева

    /**
     * Tree constructor.
     *
     * @param data
     *
     * @throws DataNullException
     *
     */
    public Tree(DataT data) throws DataNullException {
        if (data == null) {
            throw new DataNullException();
        }
        this.data = data;
        children = new ArrayList<>();
    }

    /**
     * get Parent.
     */
    public Tree<DataT> getParent() {
        return this.parent;
    }

    /**
     * get Data.
     */
    public DataT getData() {
        return this.data;
    }

    /**
     * set Data for Node.
     *
     * @param data
     *
     */
    public void setData(DataT data) {
        this.data = data;
        modCnt ++;
    }

    /**
     * getChildren.
     */
    public List<Tree<DataT>> getChildren() {
        return children;
    }

    /**
     * get ModCnt.
     */
    public int getModCnt() {
        return modCnt;
    }

    /**
     * addChild for Tree.
     *
     * @param child
     *
     */
    public Tree<DataT> addChild(Tree<DataT> child) {
        if (child != null) {
            child.parent = this;
            this.children.add(child);
            modCnt++;
            return child;
        } else {
            throw new NullPointerException("child pointer is null");
        }
    }

    /**
     * addChild (for data).
     *
     * @param data
     *
     * @throws DataNullException
     *
     */
    public Tree<DataT> addChild(DataT data) throws DataNullException {
        Tree<DataT> child = new Tree<>(data);
        child.parent = this;
        this.children.add(child);
        modCnt++;
        return child;
    }

    /**
     * remove.
     */
    public void remove() {
        if (this.parent != null) {
            this.parent.children.remove(this);
            modCnt++;
        }
        this.data = null;
        this.parent = null;
    }

    public void delete() {
        if (this.parent != null) {
            this.parent.children.remove(this);
            modCnt++;
            this.parent.children.addAll(this.children);
        }
        this.data = null;
        this.parent = null;
    }

    /**
     * dfsIterator.
     */
    public Iterator<Tree<DataT>> dfsIterator() {
        return new DfsIterator<DataT>(this);
    }

    /**
     * base iterator (Interface iterable).
     */
    @NotNull
    @Override
    public Iterator<Tree<DataT>> iterator() {
        return dfsIterator();
    }

    /**
     * bfsIterator.
     */
    public Iterator<Tree<DataT>> bfsIterator() {
        return new BfsIterator<DataT>(this);
    }

    /**
     * equals.
     *
     * @param obj
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Tree<?> someTree = (Tree<?>) obj;

        if (!data.equals(someTree.data)) {
            return false;
        }

        if (children.size() != someTree.children.size()) {
            return false;
        }

        for (int i = 0; i < this.getChildren().size(); i++) {
            if (!this.getChildren().get(i).equals(someTree.getChildren().get(i))) {
                return false;
            }
        }

        return true;
    }



    /**
     * equals_children.
     *
     * @param some_tree
     */
    private boolean equals_children(Tree<?> some_tree) {
        List<Tree<?>> list1 = new ArrayList<>(List.copyOf(this.children));
        List<Tree<?>> list2 = new ArrayList<>(List.copyOf(some_tree.children));
        if (list1.size() != list2.size()) {
            return false;
        }
        for (Tree<?> curr : list1) {
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

    /**
     * clone method.
     *
     * @throws CloneNotSupportedException
     *
     */
    @Override
    @SuppressWarnings("unchecked")
    public Tree<DataT> clone() throws CloneNotSupportedException {
        try {
            Tree<DataT> clonedTree = (Tree<DataT>) super.clone();

            clonedTree.data = this.data;

            clonedTree.children = new ArrayList<>();
            for (Tree<DataT> child : this.children) {
                Tree<DataT> clonedChild = child.clone();
                clonedChild.parent = clonedTree;
                clonedTree.children.add(clonedChild);
            }

            clonedTree.modCnt = 0;

            return clonedTree;
        } catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException("Cloning failed");
        }
    }

    /**
     * toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data --- ").append(data);
        if (!children.isEmpty()) {
            sb.append("\nChildren --- [ ");
            for (Tree<DataT> child : children) {
                sb.append(child.getData()).append(" ");
            }
            sb.append("]");
        }
        return sb.toString();
    }

}
