package algorithms;

public abstract class SortingAlgorithm {

    // atributo
    protected String algorithmName;

    // construtor
    public SortingAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    // método abstrato
    public abstract void sort(int[] data);

    // getter
    public String getName() {
        return algorithmName;
    }

    /* métodos auxiliares */
    // trocar dois elementos no array
    protected void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // imprimir o array
    protected void printArray(int[] data) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}