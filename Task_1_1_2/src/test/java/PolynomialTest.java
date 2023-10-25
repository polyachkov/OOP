import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Polynomial;
import org.junit.jupiter.api.Test;



/**
 * This is class for test the org.example.Polynomial class.
 */
public class PolynomialTest {

    @Test
    public void sumTest() {
        int[] arr1 = {5, 6, 7, 3, 0, 0};
        int[] arr2 = {5, 4, 0, 0, 5, 6, 8, 3};

        var pol1 = new Polynomial(arr1);
        var pol2 = new Polynomial(arr2);

        pol1 = pol1.sum(pol2);

        int[] expected = {10, 10, 7, 3, 5, 6, 8, 3};

        assertArrayEquals(pol1.getCoefficients(), expected);
    }

    @Test
    public void subTest() {
        int[] arr1 = {5, 6, 7, 3, 0, 0};
        int[] arr2 = {5, 4, 0, 0, 5, 6, 8, 3};

        var pol1 = new Polynomial(arr1);
        var pol2 = new Polynomial(arr2);

        pol1 = pol1.sub(pol2);

        int[] expected = {0, 2, 7, 3, -5, -6, -8, -3};

        assertArrayEquals(pol1.getCoefficients(), expected);
    }

    @Test
    public void mulTest() {
        int[] arr1 = {3, 5, 0, 1};
        int[] arr2 = {2, 0, 0, 2, 0, 0, 0, 0};

        var pol1 = new Polynomial(arr1);
        var pol2 = new Polynomial(arr2);

        pol1 = pol1.mul(pol2);

        int[] expected = {6, 10, 0, 8, 10, 0, 2};

        assertArrayEquals(pol1.getCoefficients(), expected);
    }

    @Test
    public void evaluateTest() {
        int[] arr1 = {3, 5, 0, 1, 7, 8, 0, 0};

        var pol1 = new Polynomial(arr1);

        int res = pol1.evaluate(2);

        int expected = 389;

        assertEquals(res, expected);
    }

    @Test
    public void differentiateTest() {
        int[] arr1 = {3, 5, 0, 1, 7, 8, 0, 0};

        var pol1 = new Polynomial(arr1);

        pol1 = pol1.differentiate(2);

        int[] expected = {0, 6, 84, 160};

        assertArrayEquals(pol1.getCoefficients(), expected);

    }

    @Test
    public void toStringTest() {
        int[] arr1 = {3, 5, 0, 1, 7, 8, 0};

        var pol1 = new Polynomial(arr1);

        String result = pol1.toString();

        String expected = "8x^5 + 7x^4 + x^3 + 5x + 3";

        assertEquals(result, expected);

    }

    @Test
    public void toStringEmpty() {
        int[] arr1 = {};

        var pol1 = new Polynomial(arr1);

        String result = pol1.toString();

        String expected = "0";

        assertEquals(result, expected);

    }

    @Test
    public void equalsTest() {
        int[] arr1 = {0, 6, 7, 3, 7, 99, 0, 0, 0, 0};
        int[] arr2 = {0, 6, 7, 3, 7, 99, 0};

        var pol1 = new Polynomial(arr1);
        var pol2 = new Polynomial(arr2);

        assertEquals(pol1, pol2);
    }
}
