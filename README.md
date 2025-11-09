# Comparativo de Algoritmos de Ordenação em Java

Este projeto tem como objetivo implementar e comparar o desempenho de três algoritmos de ordenação clássicos: **Bubble Sort**, **Insertion Sort** e **Quick Sort**. Além disso, ele automatiza a leitura de dados, medição de desempenho e geração de relatórios textuais com os resultados.

As medições de tempo são realizadas com base em três tipos de conjuntos de dados (aleatório, crescente e decrescente), e os resultados são apresentados em forma de tabela e análise no relatório textual.

---

## 📁 Estrutura do Projeto

```text
BubbleSort/
├── data/                  # Arquivos CSV com dados de teste
├── src/
│   ├── algorithms/        # Implementações dos algoritmos de ordenação
│   │   ├── BubbleSort.java
│   │   ├── InsertionSort.java
│   │   ├── QuickSort.java
│   │   └── SortingAlgorithm.java
│   ├── model/             # Classe de modelo para encapsular os resultados
│   │   └── SortingResult.java
│   ├── util/              # Utilitários para leitura, medição e geração de relatórios
│   │   ├── CSVReader.java
│   │   ├── PerformanceMeasurer.java
│   │   ├── ReportExporter.java
│   │   └── ReportGenerator.java
│   └── Main.java          # Classe principal de execução
└── README.md              # Este arquivo
```

---

## 👥 Divisão de Tarefas

### Fernando Alonso Piroga da Silva

* Responsável por implementar o **QuickSort.java**
* O algoritmo foi implementado de forma recursiva, escolhendo o último elemento como pivô. Utiliza divisão e conquista para ordenar subarrays, otimizando desempenho para grandes volumes de dados.

### Jafte Carneiro Fagundes da Silva

* Responsável pelas implementações de:

  * **BubbleSort.java**
  * **InsertionSort.java**
  * Suporte adicional na geração de relatório, testes e revisão geral do projeto

### Renato Pestana de Gouveia

* Responsável pelas seguintes partes do projeto:

  * Leitura dos arquivos CSV (**CSVReader.java**)
  * Medidas de desempenho (**PerformanceMeasurer.java**)
  * Impressão e exportação de resultados (**ReportExporter.java**)
  * Geração do relatório final em texto (**ReportGenerator.java**)
  * Integração geral no **Main.java**

---

## 🚀 Como Executar

1. Compile os arquivos Java:

```bash
javac -d bin src/**/*.java
```

2. Execute a aplicação principal:

```bash
java -cp bin Main
```

3. Certifique-se de que os arquivos `.csv` estão na pasta `data/` e contenham uma lista de números inteiros (um por linha).

---

## ✨ Como funcionam os algoritmos

### O que é o *Bubble Sort*?

É um algoritmo simples de ordenação que funciona repetidamente percorrendo o array, comparando pares de elementos adjacentes e trocando-os caso estejam na ordem errada.

#### Por que “Bubble”?

> Porque os maiores valores vão “subindo” para o final do array, assim como bolhas sobem na água.

#### Complexidade:

> Pior caso: O(n²)
>
> Caso médio: O(n²)
>
> Melhor caso (com otimização swapped): O(n)

### Como funciona o *Insertion Sort*?

Ele divide o array em duas partes:

* Parte já ordenada (à esquerda)
* Parte não ordenada (à direita)

A cada iteração, ele:

* Pega o próximo elemento da parte não ordenada (key)
* Caminha para a esquerda enquanto encontrar valores maiores
* Desloca esses valores para a direita
* Insere `key` na posição correta

#### Por que funciona bem para arrays quase ordenados?

Porque o laço `while` faz poucas comparações quando o array já está quase certo. Nesse caso, a complexidade cai para O(n).

#### Complexidade típica:

> Pior caso: O(n²) (quando o array está em ordem decrescente)
>
> Caso médio: O(n²)
>
> Melhor caso: O(n)

### Como funciona o *Quick Sort*?

O Quick Sort é um algoritmo de ordenação eficiente que segue a estratégia de divisão e conquista:

1. Seleciona um elemento como **pivô** (neste caso, o último do subarray)
2. Reorganiza o array de forma que todos os elementos menores que o pivô fiquem antes dele, e os maiores depois — esse processo é chamado de **particionamento**
3. Recursivamente aplica o mesmo procedimento às subpartes esquerda e direita do pivô

#### Implementação (conforme `QuickSort.java`):

A classe `QuickSort` implementa a interface `SortingAlgorithm` e possui:

- Método `sort(int[] array)` que invoca a ordenação com recursão
- Método `quickSort(...)` com limites `low` e `high`
- Método `partition(...)` que:
  - Escolhe o último elemento como pivô
  - Move menores à esquerda e maiores à direita

#### Complexidade:

> Pior caso: O(n²) (quando o menor ou maior elemento é sempre escolhido como pivô)
>
> Caso médio: O(n log n)
>
> Melhor caso: O(n log n)

---

## 📄 Licença

Projeto acadêmico desenvolvido para a disciplina **Resolução de Problemas Estruturados em Computação** - PUCPR.

