package util;

import java.io.IOException;
import java.io.PrintWriter;

public class ReportExporter {

    public static boolean saveToFile(String filePath, String content) {
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
        // Garante que o arquivo termina com .csv
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
        // Garante que o arquivo termina com .txt
        if (!filePath.toLowerCase().endsWith(".txt")) {
            filePath += ".txt";
        }

        boolean success = saveToFile(filePath, reportContent);

        if (success) {
            System.out.println("\nRelatório salvo com sucesso: " + filePath);
        } else {
            System.err.println("\nErro ao salvar relatório: " + filePath);
        }

        return success;
    }
}