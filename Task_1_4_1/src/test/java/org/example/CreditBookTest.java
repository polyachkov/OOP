package org.example;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditBookTest {
    @Test
    public void test() {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "Digital Platforms", 2);

        System.out.println(credits.grades);
        assertEquals(credits.avgMarks(), 3.5);
    }
}
