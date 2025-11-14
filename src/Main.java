import algorithms.*;
import model.SortingResult;
import util.*;

import java.util.ArrayList;
import java.util.List;


public class Main {

    // configuração dos arquivos CSV, tipos e tamanhos de dados
    private static final String DATA_PATH = "data/";
    private static final String REPORT_PATH = "report/";
    private static final String[] DATA_TYPES = {"aleatorio", "crescente", "decrescente"};
    private static final String[] DATA_TYPE_LABELS = {"Aleatório", "Crescente", "Decrescente"};
    private static final int[] DATA_SIZES = {100, 1000, 10000};

    public static void main(String[] args) {
        printHeader();
        List<SortingAlgorithm> algorithms = initializeAlgorithms();
        List<SortingResult> allResults = new ArrayList<>();

        // contadores de progresso
        int totalTests = DATA_TYPES.length * DATA_SIZES.length * algorithms.size();
        int currentTest = 0;
        int successCount = 0;
        int failCount = 0;

        System.out.println("Iniciando testes...\n");

        // execução dos testes para cada combinação de tipo e tamanho
        for (int i = 0; i < DATA_TYPES.length; i++) {
            String dataType = DATA_TYPES[i];
            String dataTypeLabel = DATA_TYPE_LABELS[i];

            System.out.println("=".repeat(80));
            System.out.println("Processando dados do tipo: " + dataTypeLabel);
            System.out.println("=".repeat(80));

            for (int size : DATA_SIZES) {
                String fileName = DATA_PATH + dataType + "_" + size + ".csv";

                System.out.println("\n" + "-".repeat(80));
                System.out.println("Conjunto: " + dataTypeLabel + " - " + size + " elementos");
                System.out.println("Arquivo: " + fileName);
                System.out.println("-".repeat(80));

                if (!CSVReader.fileExists(fileName)) {
                    System.err.println("ERRO: Arquivo não encontrado: " + fileName);
                    System.err.println("Certifique-se de que a pasta 'data' existe e contém os arquivos CSV");
                    failCount += algorithms.size();
                    currentTest += algorithms.size();
                    continue;
                }

                int[] data = CSVReader.readCSV(fileName);

                if (data.length == 0) {
                    System.err.println("ERRO: Arquivo vazio ou inválido: " + fileName);
                    failCount += algorithms.size();
                    currentTest += algorithms.size();
                    continue;
                }

                System.out.println("Dados carregados: " + data.length + " elementos\n");

                // teste de cada algoritmo
                for (SortingAlgorithm algorithm : algorithms) {
                    currentTest++;

                    // exibir progresso
                    System.out.printf("[%d/%d] Testando %s...",
                            currentTest, totalTests, algorithm.getName());

                    try {
                        SortingResult result = PerformanceMeasurer.measureExecutionTime(
                                algorithm, data, dataTypeLabel, size, true
                        );

                        allResults.add(result);

                        // exibir resultado
                        System.out.printf(" ✓ %.6f ms\n", result.getExecutionTimeMillis());
                        successCount++;

                    } catch (Exception e) {
                        System.out.println(" ✗ ERRO");
                        System.err.println("  Mensagem: " + e.getMessage());
                        failCount++;
                    }
                }
            }
            System.out.println();
        }

        // exibir resumo da execução
        printExecutionSummary(totalTests, successCount, failCount);

        if (allResults.isEmpty()) {
            System.err.println("\nNENHUM RESULTADO COLETADO. Verifique os erros acima.");
            return;
        }

        // gerar e exibir o relatório
        System.out.println("\n" + "=".repeat(80));
        System.out.println("GERANDO RELATÓRIO FINAL");
        System.out.println("=".repeat(80));
        System.out.println();
        ReportGenerator.printFullReport(allResults);

        System.out.println("\n" + "=".repeat(80));
        System.out.println("DADOS EM FORMATO CSV (para importar em planilhas)");
        System.out.println("=".repeat(80));
        String csvOutput = ReportGenerator.generateCSVOutput(allResults);
        System.out.println(csvOutput);

        System.out.println("\n" + "=".repeat(80));
        System.out.println("SALVANDO RESULTADOS EM ARQUIVO");
        System.out.println("=".repeat(80));
        String csvFileName = REPORT_PATH + "resultados.csv";
        util.ReportExporter.saveCSV(csvFileName, csvOutput);

        // salvar relatório
        String reportFileName = REPORT_PATH + "relatorio.txt";
        String fullReport = ReportGenerator.generateTextReport(allResults) + "\n" +
                ReportGenerator.generateTable(allResults) + "\n" +
                ReportGenerator.generateStatistics(allResults) + "\n" +
                ReportGenerator.generateRanking(allResults) + "\n" +
                ReportGenerator.generateAnalysis(allResults);
        util.ReportExporter.saveReport(reportFileName, fullReport);

        printFooter();
    }

    private static List<SortingAlgorithm> initializeAlgorithms() {
        List<SortingAlgorithm> algorithms = new ArrayList<>();

        algorithms.add(new BubbleSort());
        algorithms.add(new InsertionSort());
        algorithms.add(new QuickSort());

        System.out.println("\nAlgoritmos carregados:");
        for (SortingAlgorithm algorithm : algorithms) {
            System.out.println("  • " + algorithm.getName());
        }
        System.out.println();

        return algorithms;
    }

    private static void printHeader() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(" ".repeat(15) + "SISTEMA DE AVALIAÇÃO DE ALGORITMOS DE ORDENAÇÃO");
        System.out.println(" ".repeat(30) + "Análise de Desempenho");
        System.out.println("=".repeat(80));
    }

    private static void printExecutionSummary(int total, int success, int fail) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("RESUMO DA EXECUÇÃO");
        System.out.println("=".repeat(80));
        System.out.println("Total de testes executados: " + total);
        System.out.println("Testes bem-sucedidos: " + success + " ✓");
        System.out.println("Testes com falha: " + fail + (fail > 0 ? " ✗" : ""));

        if (fail == 0) {
            System.out.println("\nTestes concluídos com sucesso.");
        } else {
            System.out.println("\nAlguns testes falharam. Verifique os detalhes acima.");
        }
        System.out.println("=".repeat(80));
    }

    private static void printFooter() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(" ".repeat(25) + "EXECUÇÃO CONCLUÍDA COM SUCESSO!");
        System.out.println("=".repeat(80));
        System.out.println("\nArquivos gerados:");
        System.out.println("report/resultados.csv - Dados para gráficos");
        System.out.println("report/relatorio.txt - Relatório completo");
        System.out.println("=".repeat(80));
        System.out.println();
    }
}