package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static int[] readCSV(String filePath) {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = fileReader.readLine()) != null) {
                // remover espaços em branco
                line = line.trim();

                // ignorar linhas vazias
                if (!line.isEmpty()) {
                    try {
                        int number = Integer.parseInt(line);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        System.err.println("Aviso: linha ignorada (não é um número válido): " + line);
                    }
                }
            }

            System.out.println("Arquivo lido com sucesso: " + filePath);
            System.out.println("Total de números lidos: " + numbers.size());

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + filePath);
            System.err.println("Mensagem de erro: " + e.getMessage());
            return new int[0];
        }

        // converter List<Integer> para int[]
        int[] result = numbers.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

    public static boolean fileExists(String filePath) {
        try {
            java.io.File file = new java.io.File(filePath);
            return file.exists() && file.canRead();
        } catch (Exception e) {
            return false;
        }
    }

    public static void printArrayInfo(int[] data, String label) {
        if (data.length == 0) {
            System.out.println(label + ": array vazio");
            return;
        }

        System.out.println("\n" + label + ":");
        System.out.println("  Tamanho: " + data.length + " elementos");
        System.out.println("  Primeiro elemento: " + data[0]);
        System.out.println("  Último elemento: " + data[data.length - 1]);

        // mostrar alguns elementos do meio se o array for grande
        if (data.length > 10) {
            System.out.print("  Primeiros 5: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(data[i] + " ");
            }
            System.out.println("...");
        } else {
            System.out.print("  Elementos: ");
            for (int num : data) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}