package org.example;

public class Main {
    public static void main(String[] args) {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "Digital Platforms", 2);

        System.out.println(credits.grades);
        System.out.println(credits.avgMarks());
    }
}