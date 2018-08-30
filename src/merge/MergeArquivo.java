package merge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MergeArquivo {

    public static void preencheVetor(int vetor[], int tamanho){
        for (int i = 0; i < tamanho; i++){
            Random random = new Random();
            vetor[i] = random.nextInt(100000);
        }
    }

    public static void mostraVetor(int vetor[]){
        for (int i = 0; i < vetor.length-1; i++)
            System.out.println(vetor[i]);
    }

    public static void criarArquivo(int vetor[]){
        File arquivo = new File("PrimeiroArquivo.txt");
        try(FileWriter fw = new FileWriter(arquivo)){
            for (int i = 0; i < vetor.length; i++){
                fw.write(Integer.toString(vetor[i])+" ");
            }
            fw.flush();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static int[] lerArquivo(int qtValores) {
        int[] vetor = new int[qtValores];
        try {
            File f = new File("PrimeiroArquivo.txt");
            Scanner scanner = new Scanner(f);
            for (int i = 0; i < vetor.length; i++){
                vetor[i] = scanner.nextInt();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return vetor;
    }


    public static int callMiniFiles(int[] vetor){
        int tamanhoVetor = vetor.length;
        int divisao = tamanhoVetor/10;
        for (int cont = 0; cont < divisao; cont++){
            miniFiles(vetor, 10, cont);
        }
        return divisao;
    }

    public static void miniFiles(int[] vetor, int fixedValor, int cont){
        try {
            File arquivo = new File("arquivo"+cont+".txt");
            FileWriter fw = new FileWriter(arquivo);
            for (int i = 0; i < 10; i++){
                fw.write(Integer.toString(vetor[cont*fixedValor+i])+" ");
            }
            fw.flush();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void lerMiniArquivos(int qtArquivos){
        int[] vetor = new int[10];
        try {
            for (int i = 0; i < qtArquivos; i++){
                File f = new File("arquivo"+i+".txt");
                Scanner scanner = new Scanner(f);
                for (int j = 0; j < vetor.length; j++){
                    vetor[j] = scanner.nextInt();
                }
                QuickSort.quicksort(vetor, 0, vetor.length-1);
                try(FileWriter fw = new FileWriter(f)) {
                    for (int k = 0; k < vetor.length; k++){
                        fw.write(Integer.toString(vetor[k])+" ");
                    }
                    fw.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static void callMergeFiles(int quantidadeArquivos){
        int qtArquivos = quantidadeArquivos;
        int tamArquivo = 10;
        do {
            tamArquivo *= 2;
            if (tamArquivo > quantidadeArquivos*10)
                tamArquivo = qtArquivos*10;
            mergeFiles(qtArquivos, tamArquivo);
            qtArquivos /= 2;
        } while (qtArquivos != 1);
    }

    public static void mergeFiles(int qtArquivos, int tamArquivo){
        int nomeador = 0;
        try {
            for (int i = 0; i < qtArquivos; i+=2){
                int[] vetAux0 = new int[tamArquivo];
                int[] vetAux1 = new int[tamArquivo/2];
                int[] vetAux2 = new int[tamArquivo/2];
                File arquivoImpar = new File("arquivo"+i+".txt");
                if (i + 1 != qtArquivos){
                    File arquivoPar = new File("arquivo"+(i+1)+".txt");
                    Scanner scannerPar = new Scanner(arquivoPar);
                    for (int k = 0; k < vetAux2.length; k++){
                        vetAux2[k] = scannerPar.nextInt();
                    }
                    scannerPar.close();
                }
                Scanner scannerImpar = new Scanner(arquivoImpar);
                for (int j= 0; j < vetAux1.length; j++){
                    vetAux1[j] = scannerImpar.nextInt();
                }
                for (int l = 0; l < vetAux0.length/2; l++){
                    vetAux0[l] = vetAux1[l];
                    int posInsert = (vetAux0.length-1)-l;
                    vetAux0[posInsert] = vetAux2[l];
                }
                scannerImpar.close();
                QuickSort.quicksort(vetAux0, 0, vetAux0.length-1);
                try (FileWriter fw = new FileWriter("arquivo"+nomeador+".txt")){
                    for (int m = 0; m < vetAux0.length; m++){
                        fw.write(Integer.toString(vetAux0[m])+" ");
                    }
                    fw.flush();
                    nomeador++;
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }


}