package merge;


import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        int qtValores = 100;
        int[] vetor = new int[qtValores];
        MergeArquivo.preencheVetor(vetor, vetor.length);
        MergeArquivo.criarArquivo(vetor);
        int[] resultVetor = MergeArquivo.lerArquivo(qtValores);
        int qtArquivos = MergeArquivo.callMiniFiles(resultVetor);
        MergeArquivo.lerMiniArquivos(qtArquivos);
        MergeArquivo.callMergeFiles(qtArquivos);
        System.out.println("Executado com sucesso, o Arquivo0.txt está ordenado contendo os dados de todos os mini arquivos");
    }
}