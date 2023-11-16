package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

/**
 * Main class.
 */
public class Main {
    /**
     * Just main.
     *
     * @param args
     *
     * @throws DataNullException
     *
     */
    public static void main(String[] args) throws DataNullException {
        Tree<String> tree = new Tree<>("R1");
        Tree<String> a = tree.addChild("A");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        System.out.println(tree);
    }
}