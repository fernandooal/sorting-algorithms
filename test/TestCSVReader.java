import util.CSVReader;

public class TestCSVReader {

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("TESTE DE LEITURA DE ARQUIVOS CSV");
        System.out.println("=".repeat(80));
        System.out.println();

        // Define o caminho base dos arquivos
        String dataPath = "data/";

        // Define os arquivos que serão testados
        String[] testFiles = {
                "aleatorio_100.csv",
                "aleatorio_1000.csv",
                "aleatorio_10000.csv",
                "crescente_100.csv",
                "crescente_1000.csv",
                "crescente_10000.csv",
                "decrescente_100.csv",
                "decrescente_1000.csv",
                "decrescente_10000.csv"
        };

        int successCount = 0;
        int failCount = 0;

        // Testa cada arquivo
        for (String fileName : testFiles) {
            String filePath = dataPath + fileName;

            System.out.println("\n" + "-".repeat(80));
            System.out.println("Testando arquivo: " + fileName);
            System.out.println("-".repeat(80));

            // Verifica se o arquivo existe
            if (!CSVReader.fileExists(filePath)) {
                System.err.println("ERRO: Arquivo não encontrado: " + filePath);
                System.err.println("Certifique-se de que a pasta 'data' existe e contém os arquivos CSV");
                failCount++;
                continue;
            }

            // Tenta ler o arquivo
            int[] data = CSVReader.readCSV(filePath);

            // Verifica se a leitura foi bem-sucedida
            if (data.length == 0) {
                System.err.println("ERRO: Arquivo vazio ou não foi possível ler: " + filePath);
                failCount++;
                continue;
            }

            // Exibe informações sobre o array lido
            CSVReader.printArrayInfo(data, "Dados carregados");

            // Verifica a consistência dos dados
            verificarConsistencia(data, fileName);

            successCount++;
        }

        // Exibe resumo final
        System.out.println("\n" + "=".repeat(80));
        System.out.println("RESUMO DOS TESTES");
        System.out.println("=".repeat(80));
        System.out.println("Total de arquivos testados: " + testFiles.length);
        System.out.println("Leituras bem-sucedidas: " + successCount);
        System.out.println("Leituras com falha: " + failCount);

        if (failCount == 0) {
            System.out.println("\nTODOS OS ARQUIVOS FORAM LIDOS COM SUCESSO!");
        } else {
            System.err.println("\nALGUNS ARQUIVOS FALHARAM NA LEITURA. Verifique os erros acima.");
        }

        System.out.println("=".repeat(80));
    }

    private static void verificarConsistencia(int[] data, String fileName) {
        System.out.println("\nVerificação de consistência:");

        // Extrai informações do nome do arquivo
        String tipo = "";
        int tamanhoEsperado = 0;

        if (fileName.contains("aleatorio")) {
            tipo = "aleatório";
        } else if (fileName.contains("crescente")) {
            tipo = "crescente";
        } else if (fileName.contains("decrescente")) {
            tipo = "decrescente";
        }

        if (fileName.contains("100")) {
            tamanhoEsperado = 100;
        } else if (fileName.contains("1000")) {
            tamanhoEsperado = 1000;
        } else if (fileName.contains("10000")) {
            tamanhoEsperado = 10000;
        }

        // Verifica o tamanho
        if (data.length == tamanhoEsperado) {
            System.out.println("  Tamanho: OK (" + data.length + " elementos)");
        } else {
            System.err.println("  Tamanho: AVISO - esperado " + tamanhoEsperado +
                    ", encontrado " + data.length);
        }

        // Verifica a ordem (apenas para crescente e decrescente)
        if (tipo.equals("crescente")) {
            boolean ordenado = verificarOrdemCrescente(data);
            if (ordenado) {
                System.out.println("  Ordem: OK (ordem crescente verificada)");
            } else {
                System.err.println("  Ordem: AVISO - deveria estar em ordem crescente");
            }
        } else if (tipo.equals("decrescente")) {
            boolean ordenado = verificarOrdemDecrescente(data);
            if (ordenado) {
                System.out.println("  Ordem: OK (ordem decrescente verificada)");
            } else {
                System.err.println("  Ordem: AVISO - deveria estar em ordem decrescente");
            }
        } else {
            System.out.println("  Ordem: não aplicável (dados aleatórios)");
        }
    }

    private static boolean verificarOrdemCrescente(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean verificarOrdemDecrescente(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] < data[i + 1]) {
                return false;
            }
        }
        return true;
    }
}