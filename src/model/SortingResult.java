package model;

public class SortingResult {

    private String algorithmName;
    private String dataType;
    private int dataSize;
    private long executionTimeNanos;

    public SortingResult(String algorithmName, String dataType, int dataSize, long executionTimeNanos) {
        this.algorithmName = algorithmName;
        this.dataType = dataType;
        this.dataSize = dataSize;
        this.executionTimeNanos = executionTimeNanos;
    }

    // getters
    public String getAlgorithmName() {
        return algorithmName;
    }

    public String getDataType() {
        return dataType;
    }

    public int getDataSize() {
        return dataSize;
    }

    public long getExecutionTimeNanos() {
        return executionTimeNanos;
    }

    // converte o tempo de execução de nanosegundos para milissegundos
    public double getExecutionTimeMillis() {
        return executionTimeNanos / 1_000_000.0;
    }

    // converte o tempo de execução de nanosegundos para segundos
    public double getExecutionTimeSeconds() {
        return executionTimeNanos / 1_000_000_000.0;
    }

    // retorna uma representação em string do resultado
    @Override
    public String toString() {
        return String.format("%s | %s | %d elementos | %.4f ms",
                algorithmName, dataType, dataSize, getExecutionTimeMillis());
    }

    // retorna uma representação detalhada do resultado
    public String toDetailedString() {
        return String.format(
                "Algoritmo: %s\n" +
                        "Tipo de dados: %s\n" +
                        "Tamanho: %d elementos\n" +
                        "Tempo: %.6f ms (%.2f ns)",
                algorithmName, dataType, dataSize,
                getExecutionTimeMillis(), (double) executionTimeNanos
        );
    }
}