# Comparativo de Algoritmos de Ordenação em Java

Este projeto tem como objetivo implementar e comparar o desempenho de três algoritmos de ordenação clássicos: **Bubble Sort**, **Insertion Sort** e **Quick Sort**.

As medições de tempo são realizadas com base em três tipos de conjuntos de dados (aleatório, crescente e decrescente), e os resultados são apresentados em forma de tabela e análise no relatório LaTeX.

---

## 📁 Estrutura do Projeto

```text
BubbleSort/
├── data/                  # Arquivos CSV com dados de teste
├── src/
│   ├── algorithms/
│   │   ├── BubbleSort.java
│   │   ├── InsertionSort.java
│   │   ├── QuickSort.java
│   │   └── SortingAlgorithm.java
│   └── Main.java          # Classe principal de execução
└── README.md              # Este arquivo
```

---

## 👥 Divisão de Tarefas

### Fernando Alonso Piroga da Silva

* Responsável por implementar o **QuickSort.java**
* *(Fernando deve completar esta seção com detalhes sobre sua implementação)*

### Jafte Carneiro Fagundes da Silva

* Responsável pelas implementações de:

    * **BubbleSort.java**
    * **InsertionSort.java**

### Renato Pestana de Gouveia

* Responsável pelas seguintes partes do projeto:

    * Leitura dos arquivos CSV
    * Medidas de desempenho
    * Impressão e exportação de resultados
    * Relatório LaTeX final
* *(Renato deve completar esta seção com detalhes sobre execução e relatório)*

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

---

## 📄 Licença

Projeto acadêmico desenvolvido para a disciplina **Resolução de Problemas Estruturados em Computação** - PUCPR.
