import algorithms.SortingAlgorithm;
import algorithms.BubbleSort;
import algorithms.InsertionSort;
import algorithms.QuickSort;
import model.SortingResult;
import util.PerformanceMeasurer;


public class TestPerformanceMeasurer {

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("TESTE UNITÁRIO: PerformanceMeasurer");
        System.out.println("=".repeat(80));
        System.out.println();

        // contadores
        int passedTests = 0;
        int failedTests = 0;

        System.out.println("Teste 1: Medição básica de tempo de execução");
        System.out.println("-".repeat(80));
        try {
            int[] data = {5, 2, 8, 1, 9, 3, 7, 4, 6};
            SortingAlgorithm algorithm = new BubbleSort();

            SortingResult result = PerformanceMeasurer.measureExecutionTime(
                    algorithm, data, "Teste", data.length
            );

            // verificar se o resultado foi criado corretamente
            boolean test1 = result.getAlgorithmName().equals("Bubble Sort");
            boolean test2 = result.getDataType().equals("Teste");
            boolean test3 = result.getDataSize() == 9;
            boolean test4 = result.getExecutionTimeNanos() > 0;

            if (test1 && test2 && test3 && test4) {
                System.out.println("PASSOU - Medição realizada com sucesso");
                System.out.println("Algoritmo: " + result.getAlgorithmName());
                System.out.println("Tempo: " + result.getExecutionTimeMillis() + " ms");
                passedTests++;
            } else {
                System.out.println("FALHOU - Resultado incorreto");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 2: Verificação de array ordenado (isSorted)");
        System.out.println("-".repeat(80));
        try {
            int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            int[] unsortedArray = {5, 2, 8, 1, 9, 3, 7, 4, 6};

            boolean test1 = PerformanceMeasurer.isSorted(sortedArray);
            boolean test2 = !PerformanceMeasurer.isSorted(unsortedArray);

            if (test1 && test2) {
                System.out.println("PASSOU - isSorted() funciona corretamente");
                System.out.println("Array [1,2,3,4,5,6,7,8,9]: ordenado = " + test1);
                System.out.println("Array [5,2,8,1,9,3,7,4,6]: ordenado = " + !test2);
                passedTests++;
            } else {
                System.out.println("FALHOU - isSorted() não funciona corretamente");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 3: Validação de ordenação correta");
        System.out.println("-".repeat(80));
        try {
            int[] data = {5, 2, 8, 1, 9, 3, 7, 4, 6};
            SortingAlgorithm algorithm = new QuickSort();

            // medir com validação
            SortingResult result = PerformanceMeasurer.measureExecutionTime(
                    algorithm, data, "Teste", data.length, true
            );

            System.out.println("PASSOU - Algoritmo ordenou corretamente (validação passou)");
            System.out.println("  " + result.toString());
            passedTests++;

        } catch (Exception e) {
            System.out.println("FALHOU - Algoritmo não ordenou corretamente ou exceção");
            System.out.println("  Mensagem: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 4: Array original não é modificado");
        System.out.println("-".repeat(80));
        try {
            int[] originalData = {5, 2, 8, 1, 9, 3, 7, 4, 6};
            int[] copyBeforeMeasure = originalData.clone();

            SortingAlgorithm algorithm = new InsertionSort();
            PerformanceMeasurer.measureExecutionTime(
                    algorithm, originalData, "Teste", originalData.length
            );

            // verificar se o array original permanece igual
            boolean unchanged = true;
            for (int i = 0; i < originalData.length; i++) {
                if (originalData[i] != copyBeforeMeasure[i]) {
                    unchanged = false;
                    break;
                }
            }

            if (unchanged) {
                System.out.println("PASSOU - Array original não foi modificado");
                System.out.print("Array original: ");
                printArray(originalData);
                passedTests++;
            } else {
                System.out.println("FALHOU - Array original foi modificado");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 5: Comparação de desempenho entre algoritmos");
        System.out.println("-".repeat(80));
        try {
            int[] data = generateRandomArray(1000);

            SortingAlgorithm bubble = new BubbleSort();
            SortingAlgorithm insertion = new InsertionSort();
            SortingAlgorithm quick = new QuickSort();

            SortingResult result1 = PerformanceMeasurer.measureExecutionTime(
                    bubble, data, "Aleatório", data.length
            );

            SortingResult result2 = PerformanceMeasurer.measureExecutionTime(
                    insertion, data, "Aleatório", data.length
            );

            SortingResult result3 = PerformanceMeasurer.measureExecutionTime(
                    quick, data, "Aleatório", data.length
            );

            System.out.println("PASSOU - Comparação realizada com sucesso");
            System.out.println("\nResultados para 1000 elementos aleatórios:");
            System.out.println("  " + result1.toString());
            System.out.println("  " + result2.toString());
            System.out.println("  " + result3.toString());
            passedTests++;

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 6: Medição de tempo médio (múltiplas execuções)");
        System.out.println("-".repeat(80));
        try {
            int[] data = {5, 2, 8, 1, 9, 3, 7, 4, 6};
            SortingAlgorithm algorithm = new QuickSort();

            SortingResult avgResult = PerformanceMeasurer.measureAverageExecutionTime(
                    algorithm, data, "Teste", data.length, 10
            );

            System.out.println("PASSOU - Medição média realizada (10 iterações)");
            System.out.println("  " + avgResult.toString());
            passedTests++;

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 7: Verificação de ordem decrescente");
        System.out.println("-".repeat(80));
        try {
            int[] descendingArray = {9, 8, 7, 6, 5, 4, 3, 2, 1};
            int[] randomArray = {5, 2, 8, 1, 9};

            boolean test1 = PerformanceMeasurer.isSortedDescending(descendingArray);
            boolean test2 = !PerformanceMeasurer.isSortedDescending(randomArray);

            if (test1 && test2) {
                System.out.println("PASSOU - isSortedDescending() funciona corretamente");
                passedTests++;
            } else {
                System.out.println("FALHOU - isSortedDescending() não funciona corretamente");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();

        // resumo dos testes
        System.out.println("=".repeat(80));
        System.out.println("RESUMO DOS TESTES");
        System.out.println("=".repeat(80));
        System.out.println("Total de testes: " + (passedTests + failedTests));
        System.out.println("Testes aprovados: " + passedTests);
        System.out.println("Testes falhados: " + failedTests);

        if (failedTests == 0) {
            System.out.println("\nTestes concluídos com sucesso.");
        } else {
            System.out.println("\nAlguns testes falharam. Verifique os detalhes acima.");
        }
        System.out.println("=".repeat(80));
    }

    // métodos auxiliares
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 10000);
        }
        return array;
    }

    private static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < Math.min(array.length, 10); i++) {
            System.out.print(array[i]);
            if (i < Math.min(array.length, 10) - 1) {
                System.out.print(", ");
            }
        }
        if (array.length > 10) {
            System.out.print(", ...");
        }
        System.out.println("]");
    }
}