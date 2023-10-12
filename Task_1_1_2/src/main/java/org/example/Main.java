package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

/**
 * Main class. This class makes no sense for us.
 */
public class Main {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        var p3 = p1.sub(p2);
        System.out.println(p3);
    }
}