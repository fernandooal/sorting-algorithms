package algorithms;

public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(int[] data) {
        quickSortRecursive(data, 0, data.length - 1);
    }

    private void quickSortRecursive(int[] data, int low, int high) {
        if (low >= high){
            return;
        }

        // sorting pivot
        int pivot = data[high];
        int available = low;

        for (int i = low; i < high; i++) {
            if (data[i] < pivot) {
                swap(data, available, i);
                available++;
            }
        }
        swap(data, available, high);

        // sort left
        quickSortRecursive(data, low, available - 1);

        // sort right
        quickSortRecursive(data, available + 1, high);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }
}