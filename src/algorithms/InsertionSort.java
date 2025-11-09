package algorithms;

/**
 * Implementação do algoritmo Insertion Sort.
 *
 * O objetivo deste código é ensinar passo a passo como o algoritmo funciona,
 * por isso todas as etapas são explicadas em detalhes nos comentários.
 *
 * O Insertion Sort é eficiente para arrays pequenos ou parcialmente ordenados.
 */
public class InsertionSort implements SortingAlgorithm {

    /**
     * Método que realiza a ordenação usando o algoritmo Insertion Sort.
     *
     * @param data o array de inteiros que será ordenado
     */
    @Override
    public void sort(int[] data) {

        // O algoritmo começa a partir do índice 1,
        // pois assumimos que o elemento data[0] já está "ordenado" por si só.
        for (int i = 1; i < data.length; i++) {

            // O elemento atual que queremos inserir na posição correta
            int key = data[i];

            // j é o índice do elemento à esquerda de i
            int j = i - 1;

            /*
             * Agora comparamos 'key' com os elementos da parte já ordenada do array.
             * Enquanto 'key' for menor que data[j], devemos mover data[j] uma posição
             * para a direita, abrindo espaço para inserir o 'key'.
             *
             * O laço continua enquanto:
             * - j >= 0 → ainda estamos dentro do array
             * - data[j] > key → o elemento da esquerda é maior que o que queremos inserir
             */
            while (j >= 0 && data[j] > key) {

                // Move o elemento data[j] para a direita (posição j+1)
                data[j + 1] = data[j];

                // Avança para o próximo elemento à esquerda
                j--;
            }

            /*
             * Quando o while terminar, j estará em:
             * - uma posição negativa, OR
             * - a posição de um elemento menor ou igual ao 'key'
             *
             * Portanto, a posição correta para inserir 'key' é j + 1.
             */
            data[j + 1] = key;
        }
    }

    /**
     * Retorna o nome do algoritmo para exibição e identificação nos relatórios.
     */
    @Override
    public String getName() {
        return "Insertion Sort";
    }
}
