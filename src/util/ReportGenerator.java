package util;

import model.SortingResult;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class ReportGenerator {

    public static String generateTextReport(List<SortingResult> results) {
        StringBuilder report = new StringBuilder();

        report.append("=".repeat(80)).append("\n");
        report.append("RELATÓRIO DE ANÁLISE DE ALGORITMOS DE ORDENAÇÃO\n");
        report.append("=".repeat(80)).append("\n\n");

        Map<String, List<SortingResult>> resultsByDataType = groupByDataType(results);

        for (String dataType : resultsByDataType.keySet()) {
            report.append("Tipo de Dados: ").append(dataType).append("\n");
            report.append("-".repeat(80)).append("\n");
            report.append(String.format("%-20s %-15s %-20s\n", "Algoritmo", "Tamanho", "Tempo (ms)"));
            report.append("-".repeat(80)).append("\n");

            for (SortingResult result : resultsByDataType.get(dataType)) {
                report.append(String.format("%-20s %-15d %-20.6f\n",
                        result.getAlgorithmName(),
                        result.getDataSize(),
                        result.getExecutionTimeMillis()));
            }
            report.append("\n");
        }

        return report.toString();
    }

    public static String generateTable(List<SortingResult> results) {
        StringBuilder table = new StringBuilder();

        table.append("\nTABELA DE RESULTADOS (em milissegundos)\n");
        table.append("=".repeat(100)).append("\n");
        table.append(String.format("%-20s | %-20s | %-15s | %-20s\n",
                "Algoritmo", "Tipo de Dados", "Tamanho", "Tempo (ms)"));
        table.append("=".repeat(100)).append("\n");

        for (SortingResult result : results) {
            table.append(String.format("%-20s | %-20s | %-15d | %-20.6f\n",
                    result.getAlgorithmName(),
                    result.getDataType(),
                    result.getDataSize(),
                    result.getExecutionTimeMillis()));
        }
        table.append("=".repeat(100)).append("\n");

        return table.toString();
    }

    public static String generateCSVOutput(List<SortingResult> results) {
        StringBuilder csv = new StringBuilder();

        csv.append("Algoritmo,Tipo de Dados,Tamanho,Tempo (ms)\n");

        for (SortingResult result : results) {
            csv.append(String.format("%s,%s,%d,%.6f\n",
                    result.getAlgorithmName(),
                    result.getDataType(),
                    result.getDataSize(),
                    result.getExecutionTimeMillis()));
        }

        return csv.toString();
    }

    public static String generateAnalysis(List<SortingResult> results) {
        StringBuilder analysis = new StringBuilder();

        analysis.append("\nANÁLISE COMPARATIVA\n");
        analysis.append("=".repeat(80)).append("\n\n");

        Map<String, List<SortingResult>> grouped = groupByDataTypeAndSize(results);

        for (String key : grouped.keySet()) {
            List<SortingResult> group = grouped.get(key);
            if (group.size() > 1) {
                SortingResult fastest = findFastest(group);
                SortingResult slowest = findSlowest(group);

                analysis.append(String.format("Para %s:\n", key));
                analysis.append(String.format("  Mais rápido: %s (%.6f ms)\n",
                        fastest.getAlgorithmName(), fastest.getExecutionTimeMillis()));
                analysis.append(String.format("  Mais lento: %s (%.6f ms)\n",
                        slowest.getAlgorithmName(), slowest.getExecutionTimeMillis()));

                double difference = slowest.getExecutionTimeMillis() / fastest.getExecutionTimeMillis();
                analysis.append(String.format("  Diferença: %.2fx mais rápido\n\n",
                        difference));
            }
        }

        return analysis.toString();
    }

    public static String generateStatistics(List<SortingResult> results) {
        StringBuilder stats = new StringBuilder();

        stats.append("\nESTATÍSTICAS GERAIS\n");
        stats.append("=".repeat(80)).append("\n\n");
        stats.append("Total de testes executados: ").append(results.size()).append("\n");

        long uniqueAlgorithms = results.stream()
                .map(SortingResult::getAlgorithmName)
                .distinct()
                .count();
        stats.append("Algoritmos testados: ").append(uniqueAlgorithms).append("\n");

        long uniqueDataTypes = results.stream()
                .map(SortingResult::getDataType)
                .distinct()
                .count();
        stats.append("Tipos de dados testados: ").append(uniqueDataTypes).append("\n");

        double totalTime = results.stream()
                .mapToDouble(SortingResult::getExecutionTimeMillis)
                .sum();
        stats.append(String.format("Tempo total de execução: %.6f ms\n", totalTime));

        double avgTime = totalTime / results.size();
        stats.append(String.format("Tempo médio por teste: %.6f ms\n", avgTime));

        // teste mais rápido
        SortingResult fastest = results.stream()
                .min((r1, r2) -> Long.compare(r1.getExecutionTimeNanos(), r2.getExecutionTimeNanos()))
                .orElse(null);

        if (fastest != null) {
            stats.append(String.format("\nTeste mais rápido:\n"));
            stats.append(String.format("  %s\n", fastest.toString()));
        }

        // teste mais lento
        SortingResult slowest = results.stream()
                .max((r1, r2) -> Long.compare(r1.getExecutionTimeNanos(), r2.getExecutionTimeNanos()))
                .orElse(null);

        if (slowest != null) {
            stats.append(String.format("\nTeste mais lento:\n"));
            stats.append(String.format("  %s\n", slowest.toString()));
        }

        stats.append("\n");

        return stats.toString();
    }

    public static String generateRanking(List<SortingResult> results) {
        StringBuilder ranking = new StringBuilder();

        ranking.append("\nRANKING DE DESEMPENHO MÉDIO\n");
        ranking.append("=".repeat(80)).append("\n\n");

        // agrupar por algoritmo e calcula média
        Map<String, List<SortingResult>> byAlgorithm = new HashMap<>();
        for (SortingResult result : results) {
            byAlgorithm.computeIfAbsent(result.getAlgorithmName(), k -> new ArrayList<>())
                    .add(result);
        }

        // calcular tempo médio de cada algoritmo
        List<AlgorithmAverage> averages = new ArrayList<>();
        for (Map.Entry<String, List<SortingResult>> entry : byAlgorithm.entrySet()) {
            double avg = entry.getValue().stream()
                    .mapToDouble(SortingResult::getExecutionTimeMillis)
                    .average()
                    .orElse(0.0);
            averages.add(new AlgorithmAverage(entry.getKey(), avg));
        }

        // ordenar por tempo médio (do menor para o maior)
        averages.sort((a1, a2) -> Double.compare(a1.avgTime, a2.avgTime));

        // exibir ranking
        int position = 1;
        for (AlgorithmAverage avg : averages) {
            ranking.append(String.format("%d. %s - %.6f ms (média)\n",
                    position++, avg.algorithmName, avg.avgTime));
        }

        ranking.append("\n");

        return ranking.toString();
    }

    public static void printFullReport(List<SortingResult> results) {
        System.out.println(generateTextReport(results));
        System.out.println(generateTable(results));
        System.out.println(generateStatistics(results));
        System.out.println(generateRanking(results));
        System.out.println(generateAnalysis(results));
    }

    // métodos auxiliares
    private static Map<String, List<SortingResult>> groupByDataType(List<SortingResult> results) {
        Map<String, List<SortingResult>> grouped = new HashMap<>();
        for (SortingResult result : results) {
            grouped.computeIfAbsent(result.getDataType(), k -> new ArrayList<>()).add(result);
        }
        return grouped;
    }

    private static Map<String, List<SortingResult>> groupByDataTypeAndSize(List<SortingResult> results) {
        Map<String, List<SortingResult>> grouped = new HashMap<>();
        for (SortingResult result : results) {
            String key = result.getDataType() + " - " + result.getDataSize() + " elementos";
            grouped.computeIfAbsent(key, k -> new ArrayList<>()).add(result);
        }
        return grouped;
    }

    private static SortingResult findFastest(List<SortingResult> results) {
        SortingResult fastest = results.get(0);
        for (SortingResult result : results) {
            if (result.getExecutionTimeNanos() < fastest.getExecutionTimeNanos()) {
                fastest = result;
            }
        }
        return fastest;
    }

    private static SortingResult findSlowest(List<SortingResult> results) {
        SortingResult slowest = results.get(0);
        for (SortingResult result : results) {
            if (result.getExecutionTimeNanos() > slowest.getExecutionTimeNanos()) {
                slowest = result;
            }
        }
        return slowest;
    }

    private static class AlgorithmAverage {
        String algorithmName;
        double avgTime;

        AlgorithmAverage(String algorithmName, double avgTime) {
            this.algorithmName = algorithmName;
            this.avgTime = avgTime;
        }
    }
}