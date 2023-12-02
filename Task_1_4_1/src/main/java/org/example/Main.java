package org.example;

/**
 * text.
 */
public class Main {
    /**
     * text.
     */
    public static void main(String[] args) {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 4);
        credits.addMark(1, "OOP", 3);
        credits.addMark(1, "Digital Platforms", 5);
        credits.addMark(1, "Digital atforms", 5);
        credits.addMark(1, "Digital Plrms", 5);

        System.out.println(credits.grades);
        System.out.println(credits.avgMarks());

        credits.setQualificationMark(5);

        System.out.println(credits.isRedDiploma());
        System.out.println(credits.isBigMoneyMoney(1));
    }
}