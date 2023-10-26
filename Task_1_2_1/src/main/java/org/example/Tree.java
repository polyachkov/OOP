package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree<DataType> implements Iterable<Tree<DataType>>, Cloneable {
    private Tree<DataType> parent = null;
    private DataType data;
    private List<Tree<DataType>> children;

    private int modCnt = 0; // Число модификаций дерева

    /**
     * Tree constructor.
     * @param data
     * @throws DataNullException
     */
    public Tree(DataType data) throws DataNullException {
        if (data == null) {
            throw new DataNullException();
        }
        this.data = data;
        children = new ArrayList<>();
    }

    /**
     * get Parent.
     * @return
     */
    public Tree<DataType> getParent() {return this.parent;}

    /**
     * get Data.
     * @return
     */
    public DataType getData() {
        return this.data;
    }

    /**
     * set Data for Node.
     * @param data
     */
    public void setData(DataType data) {
        this.data = data;
        modCnt ++;
    }

    /**
     * getChildren.
     * @return
     */
    public List<Tree<DataType>> getChildren() {
        return children;
    }

    /**
     * get ModCnt.
     * @return
     */
    public int getModCnt() {
        return modCnt;
    }

    /**
     * addChild for Tree.
     * @param child
     * @return
     */
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

    /**
     * addChild (for data).
     * @param data
     * @return
     * @throws DataNullException
     */
    public Tree<DataType> addChild(DataType data) throws DataNullException {
        Tree<DataType> child = new Tree<>(data);
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

    /**
     * dfsIterator.
     * @return
     */
    public Iterator<Tree<DataType>> dfsIterator() {
        return new DfsIterator<DataType>(this);
    }

    /**
     * base iterator (Interface iterable).
     * @return
     */
    @NotNull
    @Override
    public Iterator<Tree<DataType>> iterator() {
        return dfsIterator();
    }

    /**
     * bfsIterator.
     * @return
     */
    public Iterator<Tree<DataType>> bfsIterator() {
        return new BfsIterator<DataType>(this);
    }

    /**
     * equals.
     * @param obj
     * @return
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
     * @param some_tree
     * @return
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
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Tree<DataType> clone() throws CloneNotSupportedException {
        try {
            Tree<DataType> clonedTree = (Tree<DataType>) super.clone();

            clonedTree.data = this.data;

            clonedTree.children = new ArrayList<>();
            for (Tree<DataType> child : this.children) {
                Tree<DataType> clonedChild = child.clone();
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
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data --- ").append(data);
        if (!children.isEmpty()) {
            sb.append("\nChildren --- [ ");
            for (Tree<DataType> child : children) {
                sb.append(child.getData()).append(" ");
            }
            sb.append("]");
        }
        return sb.toString();
    }

}
