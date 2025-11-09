import model.SortingResult;
import util.ReportGenerator;
import java.util.ArrayList;
import java.util.List;

public class TestReportGenerator {

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("TESTE UNITÁRIO: ReportGenerator");
        System.out.println("=".repeat(80));
        System.out.println();

        int passedTests = 0;
        int failedTests = 0;

        List<SortingResult> testResults = createTestResults();

        System.out.println("Teste 1: Geração de relatório de texto");
        System.out.println("-".repeat(80));
        try {
            String textReport = ReportGenerator.generateTextReport(testResults);

            // verificar se contém as seções principais
            boolean hasTitle = textReport.contains("RELATÓRIO DE ANÁLISE");
            boolean hasDataType = textReport.contains("Tipo de Dados:");
            boolean hasResults = textReport.contains("Bubble Sort") ||
                    textReport.contains("Quick Sort");

            if (hasTitle && hasDataType && hasResults) {
                System.out.println("PASSOU - Relatório de texto gerado corretamente");
                System.out.println("\nPrévia do relatório:");
                System.out.println(textReport.substring(0, Math.min(300, textReport.length())) + "...");
                passedTests++;
            } else {
                System.out.println("FALHOU - Relatório incompleto");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();

        System.out.println("Teste 2: Geração de tabela formatada");
        System.out.println("-".repeat(80));
        try {
            String table = ReportGenerator.generateTable(testResults);

            // verificar se contém elementos da tabela
            boolean hasHeader = table.contains("TABELA DE RESULTADOS");
            boolean hasColumns = table.contains("Algoritmo") &&
                    table.contains("Tipo de Dados") &&
                    table.contains("Tamanho");
            boolean hasSeparator = table.contains("=") || table.contains("|");

            if (hasHeader && hasColumns && hasSeparator) {
                System.out.println("PASSOU - Tabela gerada corretamente");
                System.out.println("\nPrévia da tabela:");
                String[] lines = table.split("\n");
                for (int i = 0; i < Math.min(8, lines.length); i++) {
                    System.out.println(lines[i]);
                }
                if (lines.length > 8) {
                    System.out.println("...");
                }
                passedTests++;
            } else {
                System.out.println("FALHOU - Tabela incompleta");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();

        System.out.println("Teste 3: Geração de saída CSV");
        System.out.println("-".repeat(80));
        try {
            String csv = ReportGenerator.generateCSVOutput(testResults);

            // verificar formato CSV
            boolean hasHeader = csv.startsWith("Algoritmo,Tipo de Dados,Tamanho,Tempo");
            boolean hasCommas = csv.contains(",");
            String[] lines = csv.split("\n");
            boolean hasMultipleLines = lines.length > 1;

            if (hasHeader && hasCommas && hasMultipleLines) {
                System.out.println("PASSOU - CSV gerado corretamente");
                System.out.println("\nPrévia do CSV:");
                for (int i = 0; i < Math.min(5, lines.length); i++) {
                    System.out.println(lines[i]);
                }
                if (lines.length > 5) {
                    System.out.println("...");
                }
                passedTests++;
            } else {
                System.out.println("FALHOU - CSV incompleto");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();

        System.out.println("Teste 4: Geração de análise comparativa");
        System.out.println("-".repeat(80));
        try {
            String analysis = ReportGenerator.generateAnalysis(testResults);

            // verificar se contém comparações
            boolean hasTitle = analysis.contains("ANÁLISE COMPARATIVA");
            boolean hasComparison = analysis.contains("Mais rápido") ||
                    analysis.contains("Mais lento");

            if (hasTitle && hasComparison) {
                System.out.println("PASSOU - Análise gerada corretamente");
                System.out.println("\nPrévia da análise:");
                System.out.println(analysis.substring(0, Math.min(400, analysis.length())));
                if (analysis.length() > 400) {
                    System.out.println("...");
                }
                passedTests++;
            } else {
                System.out.println("FALHOU - Análise incompleta");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();

        System.out.println("Teste 5: Geração de estatísticas gerais");
        System.out.println("-".repeat(80));
        try {
            String stats = ReportGenerator.generateStatistics(testResults);

            // verificar se contém estatísticas
            boolean hasTitle = stats.contains("ESTATÍSTICAS GERAIS");
            boolean hasTotal = stats.contains("Total de testes");
            boolean hasAverage = stats.contains("média") || stats.contains("Tempo médio");

            if (hasTitle && hasTotal && hasAverage) {
                System.out.println("PASSOU - Estatísticas geradas corretamente");
                System.out.println("\nPrévia das estatísticas:");
                System.out.println(stats.substring(0, Math.min(500, stats.length())));
                if (stats.length() > 500) {
                    System.out.println("...");
                }
                passedTests++;
            } else {
                System.out.println("FALHOU - Estatísticas incompletas");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();

        System.out.println("Teste 6: Geração de ranking de desempenho");
        System.out.println("-".repeat(80));
        try {
            String ranking = ReportGenerator.generateRanking(testResults);

            // verificar se contém ranking
            boolean hasTitle = ranking.contains("RANKING");
            boolean hasPositions = ranking.contains("1.") && ranking.contains("média");

            if (hasTitle && hasPositions) {
                System.out.println("PASSOU - Ranking gerado corretamente");
                System.out.println("\nPrévia do ranking:");
                System.out.println(ranking);
                passedTests++;
            } else {
                System.out.println("FALHOU - Ranking incompleto");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();

        System.out.println("Teste 7: Geração de relatório completo");
        System.out.println("-".repeat(80));
        try {
            System.out.println("Gerando relatório completo...\n");
            ReportGenerator.printFullReport(testResults);

            System.out.println("\nPASSOU - Relatório completo gerado sem erros");
            passedTests++;

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

    private static List<SortingResult> createTestResults() {
        List<SortingResult> results = new ArrayList<>();

        // Bubble Sort
        results.add(new SortingResult("Bubble Sort", "Aleatório", 100, 5_000_000L));
        results.add(new SortingResult("Bubble Sort", "Aleatório", 1000, 50_000_000L));
        results.add(new SortingResult("Bubble Sort", "Crescente", 100, 2_000_000L));
        results.add(new SortingResult("Bubble Sort", "Crescente", 1000, 20_000_000L));
        results.add(new SortingResult("Bubble Sort", "Decrescente", 100, 8_000_000L));
        results.add(new SortingResult("Bubble Sort", "Decrescente", 1000, 80_000_000L));

        // Insertion Sort
        results.add(new SortingResult("Insertion Sort", "Aleatório", 100, 3_000_000L));
        results.add(new SortingResult("Insertion Sort", "Aleatório", 1000, 30_000_000L));
        results.add(new SortingResult("Insertion Sort", "Crescente", 100, 1_000_000L));
        results.add(new SortingResult("Insertion Sort", "Crescente", 1000, 10_000_000L));
        results.add(new SortingResult("Insertion Sort", "Decrescente", 100, 6_000_000L));
        results.add(new SortingResult("Insertion Sort", "Decrescente", 1000, 60_000_000L));

        // Quick Sort
        results.add(new SortingResult("Quick Sort", "Aleatório", 100, 500_000L));
        results.add(new SortingResult("Quick Sort", "Aleatório", 1000, 5_000_000L));
        results.add(new SortingResult("Quick Sort", "Crescente", 100, 400_000L));
        results.add(new SortingResult("Quick Sort", "Crescente", 1000, 4_000_000L));
        results.add(new SortingResult("Quick Sort", "Decrescente", 100, 600_000L));
        results.add(new SortingResult("Quick Sort", "Decrescente", 1000, 6_000_000L));

        return results;
    }
}