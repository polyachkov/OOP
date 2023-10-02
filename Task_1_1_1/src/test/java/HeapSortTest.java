import java.util.Random;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.example.HeapSort;

public class HeapSortTest {

    @Test
    public void testHeapSort() {
        int[] arr = {12, 11, 13, 5, 6, 7};
        int[] expected = {5, 6, 7, 11, 12, 13};

        HeapSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};

        HeapSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {5, 4, 5, 5, 5};
        int[] expected = {4, 5, 5, 5, 5};

        HeapSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testMassiveArray() {
        int[] arr = new int[100000];
        Random random = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int n = random.nextInt(Integer.MAX_VALUE);
            arr[i] = n;
        }

        int[] expected = Arrays.copyOf(arr, 100000);

        Arrays.sort(expected);

        HeapSort.sort(arr);

        assertArrayEquals(expected, arr);
    }
}