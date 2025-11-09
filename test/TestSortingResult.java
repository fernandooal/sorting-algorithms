import model.SortingResult;

public class TestSortingResult {

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("TESTE UNITÁRIO: SortingResult");
        System.out.println("=".repeat(80));
        System.out.println();

        // contadores
        int passedTests = 0;
        int failedTests = 0;

        System.out.println("Teste 1: Criação de objeto e getters");
        System.out.println("-".repeat(80));
        try {
            SortingResult result = new SortingResult(
                    "Bubble Sort",
                    "Aleatório",
                    1000,
                    5_000_000L  // 5 milhões de nanosegundos = 5 ms
            );

            // verificar getters
            boolean test1 = result.getAlgorithmName().equals("Bubble Sort");
            boolean test2 = result.getDataType().equals("Aleatório");
            boolean test3 = result.getDataSize() == 1000;
            boolean test4 = result.getExecutionTimeNanos() == 5_000_000L;

            if (test1 && test2 && test3 && test4) {
                System.out.println("PASSOU - Todos os getters retornam valores corretos");
                passedTests++;
            } else {
                System.out.println("FALHOU - Algum getter não retornou o valor esperado");
                failedTests++;
            }

            System.out.println("Algorithm: " + result.getAlgorithmName());
            System.out.println("DataType: " + result.getDataType());
            System.out.println("DataSize: " + result.getDataSize());
            System.out.println("TimeNanos: " + result.getExecutionTimeNanos());

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 2: Conversão de nanosegundos para milissegundos");
        System.out.println("-".repeat(80));
        try {
            SortingResult result = new SortingResult(
                    "Quick Sort",
                    "Crescente",
                    100,
                    2_500_000L  // 2.5 milhões de nanos = 2.5 ms
            );

            double millis = result.getExecutionTimeMillis();

            // verificar se a conversão está correta
            if (Math.abs(millis - 2.5) < 0.001) {
                System.out.println("PASSOU - Conversão para milissegundos está correta");
                System.out.println("2.500.000 ns = " + millis + " ms");
                passedTests++;
            } else {
                System.out.println("FALHOU - Esperado 2.5 ms, obtido " + millis + " ms");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 3: Conversão de nanosegundos para segundos");
        System.out.println("-".repeat(80));
        try {
            SortingResult result = new SortingResult(
                    "Insertion Sort",
                    "Decrescente",
                    10000,
                    1_500_000_000L  // 1.5 bilhão de nanos = 1.5 segundos
            );

            double seconds = result.getExecutionTimeSeconds();

            // verificar se a conversão está correta
            if (Math.abs(seconds - 1.5) < 0.001) {
                System.out.println("PASSOU - Conversão para segundos está correta");
                System.out.println("  1.500.000.000 ns = " + seconds + " s");
                passedTests++;
            } else {
                System.out.println("FALHOU - Esperado 1.5 s, obtido " + seconds + " s");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 4: Método toString()");
        System.out.println("-".repeat(80));
        try {
            SortingResult result = new SortingResult(
                    "Bubble Sort",
                    "Aleatório",
                    1000,
                    3_456_789L
            );

            String str = result.toString();

            // verificar se contém as informações principais
            boolean containsAlgorithm = str.contains("Bubble Sort");
            boolean containsDataType = str.contains("Aleatório");
            boolean containsSize = str.contains("1000");

            if (containsAlgorithm && containsDataType && containsSize) {
                System.out.println("PASSOU - toString() contém todas as informações");
                System.out.println("  Saída: " + str);
                passedTests++;
            } else {
                System.out.println("FALHOU - toString() não contém todas as informações");
                System.out.println("  Saída: " + str);
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 5: Método toDetailedString()");
        System.out.println("-".repeat(80));
        try {
            SortingResult result = new SortingResult(
                    "Quick Sort",
                    "Crescente",
                    100,
                    1_234_567L
            );

            String detailedStr = result.toDetailedString();

            // verificar se contém as informações principais
            boolean containsAlgorithm = detailedStr.contains("Quick Sort");
            boolean containsDataType = detailedStr.contains("Crescente");
            boolean containsSize = detailedStr.contains("100");

            if (containsAlgorithm && containsDataType && containsSize) {
                System.out.println("PASSOU - toDetailedString() contém todas as informações");
                System.out.println("\nSaída:");
                System.out.println(detailedStr);
                passedTests++;
            } else {
                System.out.println("FALHOU - toDetailedString() não contém todas as informações");
                failedTests++;
            }

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 6: Valores extremos - tempo muito pequeno");
        System.out.println("-".repeat(80));
        try {
            SortingResult result = new SortingResult(
                    "Quick Sort",
                    "Aleatório",
                    10,
                    100L  // 100 nanosegundos = 0.0001 ms
            );

            double millis = result.getExecutionTimeMillis();

            System.out.println("PASSOU - Consegue lidar com tempo muito pequeno");
            System.out.println("  100 ns = " + millis + " ms");
            passedTests++;

        } catch (Exception e) {
            System.out.println("FALHOU - Exceção: " + e.getMessage());
            failedTests++;
        }
        System.out.println();


        System.out.println("Teste 7: Valores extremos - tempo muito grande");
        System.out.println("-".repeat(80));
        try {
            SortingResult result = new SortingResult(
                    "Bubble Sort",
                    "Decrescente",
                    1000000,
                    30_000_000_000L  // 30 bilhões de nanos = 30 segundos
            );

            double seconds = result.getExecutionTimeSeconds();

            System.out.println("PASSOU - Consegue lidar com tempo muito grande");
            System.out.println("  30.000.000.000 ns = " + seconds + " s");
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
}