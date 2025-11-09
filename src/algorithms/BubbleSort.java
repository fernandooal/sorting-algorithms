package algorithms;

/**
 * Implementação do algoritmo Bubble Sort.
 *
 * Esta versão utiliza a otimização com a variável 'swapped',
 * que interrompe o algoritmo mais cedo quando o array já estiver ordenado.
 *
 * O objetivo deste código é ensinar passo a passo o funcionamento
 * do algoritmo, por isso todos os trechos são amplamente comentados.
 */
public class BubbleSort implements SortingAlgorithm {

    /**
     * Método principal que executa o algoritmo Bubble Sort.
     *
     * @param data o array de inteiros que será ordenado
     */
    @Override
    public void sort(int[] data) {

        // 'n' armazena o tamanho do array para facilitar a leitura do código
        int n = data.length;

        // A variável 'swapped' será usada para detectar se houve trocas na iteração atual
        // Se nenhuma troca ocorrer, o array já está ordenado e o algoritmo pode parar
        boolean swapped;

        // Laço externo:
        // Controla o número de passagens pelo array.
        // Após cada passagem, o maior elemento já está na posição correta no final do array.
        for (int i = 0; i < n - 1; i++) {

            // Antes de iniciar a passagem, assumimos que não houve trocas
            swapped = false;

            // Laço interno:
            // Percorre o array até a posição 'n - i - 1' porque após cada iteração
            // o maior elemento já estará na posição final e não precisa ser revisitado.
            for (int j = 0; j < n - i - 1; j++) {

                // Compara elementos adjacentes
                if (data[j] > data[j + 1]) {

                    // Se estiverem fora de ordem, trocamos
                    swap(data, j, j + 1);

                    // Como houve troca, marcamos swapped como true
                    swapped = true;
                }
            }

            // Se nenhuma troca ocorreu na passagem, o array já estava ordenado
            // Portanto, é possível interromper o algoritmo para economizar tempo
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Método auxiliar que troca dois elementos de posição no array.
     *
     * @param array o array a ser modificado
     * @param i índice do primeiro elemento
     * @param j índice do segundo elemento
     */
    private void swap(int[] array, int i, int j) {
        // A troca é feita usando uma variável temporária
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Retorna o nome do algoritmo, útil para identificação automática
     * em testes ou relatórios.
     */
    @Override
    public String getName() {
        return "Bubble Sort";
    }
}
