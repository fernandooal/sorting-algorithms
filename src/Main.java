import algorithms.*;
import model.SortingResult;
import util.*;

import java.util.ArrayList;
import java.util.List;


public class Main {

    // Configuração dos arquivos CSV
    private static final String DATA_PATH = "data/";

    // Tipos de dados
    private static final String[] DATA_TYPES = {"aleatorio", "crescente", "decrescente"};
    private static final String[] DATA_TYPE_LABELS = {"Aleatório", "Crescente", "Decrescente"};

    // Tamanhos dos conjuntos de dados
    private static final int[] DATA_SIZES = {100, 1000, 10000};

    public static void main(String[] args) {
        // Cabeçalho do programa
        printHeader();

        // Inicializa os algoritmos
        List<SortingAlgorithm> algorithms = initializeAlgorithms();

        // Lista para armazenar todos os resultados
        List<SortingResult> allResults = new ArrayList<>();

        // Contadores de progresso
        int totalTests = DATA_TYPES.length * DATA_SIZES.length * algorithms.size();
        int currentTest = 0;
        int successCount = 0;
        int failCount = 0;

        System.out.println("Iniciando testes...\n");

        // Executa os testes para cada combinação de tipo e tamanho
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

                // Verifica se o arquivo existe
                if (!CSVReader.fileExists(fileName)) {
                    System.err.println("ERRO: Arquivo não encontrado: " + fileName);
                    System.err.println("Certifique-se de que a pasta 'data' existe e contém os arquivos CSV");
                    failCount += algorithms.size();
                    currentTest += algorithms.size();
                    continue;
                }

                // Lê os dados do arquivo
                int[] data = CSVReader.readCSV(fileName);

                if (data.length == 0) {
                    System.err.println("ERRO: Arquivo vazio ou inválido: " + fileName);
                    failCount += algorithms.size();
                    currentTest += algorithms.size();
                    continue;
                }

                System.out.println("Dados carregados: " + data.length + " elementos\n");

                // Testa cada algoritmo com esses dados
                for (SortingAlgorithm algorithm : algorithms) {
                    currentTest++;

                    // Exibe progresso
                    System.out.printf("[%d/%d] Testando %s...",
                            currentTest, totalTests, algorithm.getName());

                    try {
                        // Mede o tempo de execução com validação
                        SortingResult result = PerformanceMeasurer.measureExecutionTime(
                                algorithm, data, dataTypeLabel, size, true
                        );

                        // Adiciona o resultado à lista
                        allResults.add(result);

                        // Exibe resultado
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

        // Exibe resumo da execução
        printExecutionSummary(totalTests, successCount, failCount);

        // Se não houver resultados, encerra
        if (allResults.isEmpty()) {
            System.err.println("\nNENHUM RESULTADO COLETADO. Verifique os erros acima.");
            return;
        }

        // Gera e exibe o relatório completo
        System.out.println("\n" + "=".repeat(80));
        System.out.println("GERANDO RELATÓRIO FINAL");
        System.out.println("=".repeat(80));
        System.out.println();

        ReportGenerator.printFullReport(allResults);

        // Gera CSV para gráficos
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DADOS EM FORMATO CSV (para importar em planilhas)");
        System.out.println("=".repeat(80));

        String csvOutput = ReportGenerator.generateCSVOutput(allResults);
        System.out.println(csvOutput);

        // Salva CSV em arquivo
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SALVANDO RESULTADOS EM ARQUIVO");
        System.out.println("=".repeat(80));

        String csvFileName = "resultados.csv";
        util.ReportExporter.saveCSV(csvFileName, csvOutput);

        // Salva relatório completo em arquivo texto
        String reportFileName = "relatorio.txt";
        String fullReport = ReportGenerator.generateTextReport(allResults) + "\n" +
                ReportGenerator.generateTable(allResults) + "\n" +
                ReportGenerator.generateStatistics(allResults) + "\n" +
                ReportGenerator.generateRanking(allResults) + "\n" +
                ReportGenerator.generateAnalysis(allResults);
        util.ReportExporter.saveReport(reportFileName, fullReport);

        // Mensagem final
        printFooter();
    }

    private static List<SortingAlgorithm> initializeAlgorithms() {
        List<SortingAlgorithm> algorithms = new ArrayList<>();

        // Adiciona os três algoritmos
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
            System.out.println("\nTODOS OS TESTES FORAM EXECUTADOS COM SUCESSO!");
        } else {
            System.out.println("\nALGUNS TESTES FALHARAM. Verifique os erros acima.");
        }
        System.out.println("=".repeat(80));
    }

    private static void printFooter() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(" ".repeat(25) + "EXECUÇÃO CONCLUÍDA COM SUCESSO!");
        System.out.println("=".repeat(80));
        System.out.println("\nArquivos gerados:");
        System.out.println("  📊 resultados_tde04.csv - Dados para gráficos");
        System.out.println("  📄 relatorio_tde04.txt - Relatório completo");
        System.out.println("\nPróximos passos:");
        System.out.println("  1. Abra resultados_tde04.csv no Excel/Google Sheets");
        System.out.println("  2. Crie gráficos com os dados");
        System.out.println("  3. Escreva sua análise baseada no relatório");
        System.out.println("=".repeat(80));
        System.out.println();
    }
}