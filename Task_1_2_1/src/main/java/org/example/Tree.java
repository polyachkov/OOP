package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tree<DataType> {
    private Tree<DataType> parent = null;
    private DataType data;
    private List<Tree<DataType>> children;

    public Tree(DataType data) {
        this.data = data;
        children = new ArrayList<>();
    }

    public Tree<DataType> getParent() {
        return parent;
    }

    public DataType getData() {
        return data;
    }

    public List<Tree<DataType>> getChildren() {
        return children;
    }

    public void setParent(Tree<DataType> parent) {
        this.parent = parent;
    }

    public void setData(DataType data) {
        this.data = data;
    }

    public void setChildren(List<Tree<DataType>> children) {
        this.children = children;
    }


    public Tree<DataType> addChild(DataType data) {
        Tree<DataType> child = new Tree<>(data);
        child.setParent(this);
        return child;
    }

}
