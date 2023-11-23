package org.example;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditBookTest {
    @Test
    public void Avgtest() {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "OOP", 4);
        credits.addMark(1, "Digital Platforms", 2);
        credits.addMark(2, "OOP", 3);
        credits.addMark(2, "OOP", 5);
        credits.addMark(2, "Digital Platforms", 5);
        credits.addMark(3, "OOP", 5);
        credits.addMark(3, "OOP", 3);
        credits.addMark(3, "Digital Platforms", 4);

        assertEquals(credits.avgMarks(), 4.0);
    }

    @Test
    public void isBigMoneyMoneyTest() {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "OOP", 4);
        credits.addMark(1, "Digital Platforms", 5);

        credits.addMark(2, "OOP", 5);
        credits.addMark(2, "Digital Platforms", 5);
        credits.addMark(2, "Math", 5);
        credits.addMark(2, "Imperative Programming", 4);

        assertTrue(credits.isBigMoneyMoney(1));
        assertFalse(credits.isBigMoneyMoney(2));
    }

    @Test
    public void isRedDiplomaTestTrue() {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "OOP", 4);
        credits.addMark(1, "Digital Platforms", 5);
        credits.setQualificationMark(5);

        assertTrue(credits.isRedDiploma());
    }

    @Test
    public void isRedDiplomaTestFalse() {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "OOP", 4);
        credits.addMark(1, "Digital Platforms", 5);
        credits.setQualificationMark(4);

        assertFalse(credits.isRedDiploma());
    }

    @Test
    public void deleteMarkTest() {
        CreditBook credits = new CreditBook("Dmitry", "22216");
        credits.addMark(1, "OOP", 5);
        credits.addMark(1, "Digital Platforms", 5);
        credits.addMark(2, "OOP", 2);
        credits.addMark(2, "Digital Platforms", 2);
        credits.setQualificationMark(5);

        credits.deleteMark(2, "OOP", 2);
        credits.deleteMark(2, "Digital Platforms", 2);

        assertTrue(credits.isRedDiploma());
    }
}
