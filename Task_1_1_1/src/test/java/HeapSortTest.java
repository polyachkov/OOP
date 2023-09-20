import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.example.HeapSort;

public class HeapSortTest {

    @Test
    public void testHeapSort() {
        int[] arr = {12, 11, 13, 5, 6, 7};
        int[] expected = {5, 6, 7, 11, 12, 13};

        HeapSort.heapsort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testHeapify() {
        int[] arr = {4, 10, 3, 5, 1};
        int n = arr.length;
        int i = 0;
        int[] expected = {10, 5, 3, 4, 1};

        HeapSort.heapify(arr, n, i);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};

        HeapSort.heapsort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {5, 4, 5, 5, 5};
        int[] expected = {4, 5, 5, 5, 5};

        HeapSort.heapsort(arr);

        assertArrayEquals(expected, arr);
    }
}