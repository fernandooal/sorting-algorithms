package algorithms;

// interface para algoritmos de ordenação
public interface SortingAlgorithm {

    // método para ordenar um array de inteiros
    void sort(int[] data);

    // método para obter o nome do algoritmo
    String getName();
}