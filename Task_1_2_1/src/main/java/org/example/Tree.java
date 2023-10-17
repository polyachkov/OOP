package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tree<DataType> {
    DataType data;
    List<Tree<DataType>> children;
    public void Tree(DataType data) {
        this.data = data;
        children = new ArrayList<>();
    }

    

}
