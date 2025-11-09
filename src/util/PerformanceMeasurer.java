package util;

import algorithms.SortingAlgorithm;
import model.SortingResult;


public class PerformanceMeasurer {

    public static SortingResult measureExecutionTime(
            SortingAlgorithm algorithm,
            int[] data,
            String dataType,
            int dataSize
    ) {
        int[] dataCopy = data.clone();

        long startTime = System.nanoTime();

        algorithm.sort(dataCopy);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        return new SortingResult(algorithm.getName(), dataType, dataSize, executionTime);
    }

    public static SortingResult measureExecutionTime(
            SortingAlgorithm algorithm,
            int[] data,
            String dataType,
            int dataSize,
            boolean validate
    ) {
        SortingResult result = measureExecutionTime(algorithm, data, dataType, dataSize);

        // se validação foi solicitada, verifica se está ordenado
        if (validate) {
            int[] dataCopy = data.clone();
            algorithm.sort(dataCopy);

            if (!isSorted(dataCopy)) {
                throw new RuntimeException(
                        "ERRO: O algoritmo " + algorithm.getName() +
                                " não ordenou o array corretamente!"
                );
            }
        }

        return result;
    }

    public static boolean isSorted(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSortedDescending(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] < data[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static SortingResult measureAverageExecutionTime(
            SortingAlgorithm algorithm,
            int[] data,
            String dataType,
            int dataSize,
            int iterations
    ) {
        long totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            int[] dataCopy = data.clone();

            long startTime = System.nanoTime();
            algorithm.sort(dataCopy);
            long endTime = System.nanoTime();

            totalTime += (endTime - startTime);
        }

        long averageTime = totalTime / iterations;

        return new SortingResult(algorithm.getName(), dataType, dataSize, averageTime);
    }

    public static void printResult(SortingResult result) {
        System.out.println(result.toString());
    }

    public static void printDetailedResult(SortingResult result) {
        System.out.println(result.toDetailedString());
    }
}