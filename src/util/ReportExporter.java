package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportExporter {

    private static boolean ensureDirectoryExists(String filePath) {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (directory != null && !directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Diretorio criado: " + directory.getPath());
            }
            return created;
        }
        return true;
    }

    public static boolean saveToFile(String filePath, String content) {
        if (!ensureDirectoryExists(filePath)) {
            System.err.println("Erro ao criar diretorio para: " + filePath);
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(filePath))) {
            writer.print(content);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + filePath);
            System.err.println("Mensagem: " + e.getMessage());
            return false;
        }
    }

    public static boolean saveCSV(String filePath, String csvContent) {
        if (!filePath.toLowerCase().endsWith(".csv")) {
            filePath += ".csv";
        }

        boolean success = saveToFile(filePath, csvContent);

        if (success) {
            System.out.println("\nArquivo CSV salvo com sucesso: " + filePath);
        } else {
            System.err.println("\nErro ao salvar arquivo CSV: " + filePath);
        }

        return success;
    }

    public static boolean saveReport(String filePath, String reportContent) {
        if (!filePath.toLowerCase().endsWith(".txt")) {
            filePath += ".txt";
        }

        boolean success = saveToFile(filePath, reportContent);

        if (success) {
            System.out.println("\nRelatorio salvo com sucesso: " + filePath);
        } else {
            System.err.println("\nErro ao salvar relatorio: " + filePath);
        }

        return success;
    }
}