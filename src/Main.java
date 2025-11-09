import algorithms.SortingAlgorithm;
import algorithms.QuickSort;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] data = new int[]{7, 1, 3, 5, 2, 6, 4};

        System.out.println("Array original: " + Arrays.toString(data));

        SortingAlgorithm quickSort = new QuickSort();
        quickSort.sort(data);

        System.out.println("Array ordenado: " + Arrays.toString(data));
    }
}