package merge;

public class QuickSort {
    public static void quicksort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posPivot = split(vetor, inicio, fim);
            quicksort(vetor, inicio, posPivot - 1);
            quicksort(vetor, posPivot + 1, fim);
        }
    }

    public static int split(int[] vetor, int inicio, int fim) {
        int pivot = vetor[inicio];
        int i = inicio + 1;
        int f = fim;
        while (i <= f) {
            if (vetor[i] <= pivot)
                i++;
            else if (pivot < vetor[f])
                f--;
            else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivot;
        return f;
    }
}