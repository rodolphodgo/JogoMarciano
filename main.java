import java.util.Scanner;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Random gerador = new Random();

        // O marciano escolhe uma árvore entre 1 e 100
        int arvore = gerador.nextInt(100) + 1;
        int tentativa = 0;
        int qntTentativas = 0;

        System.out.println("\nO marciano se escondeu em uma árvore de 1 a 100.\nTente um palpite para encontrá-lo!");

        while (tentativa != arvore) {
            System.out.print("\nDigite o número da árvore: ");
            
            if (leitor.hasNextInt()) {
                tentativa = leitor.nextInt();
                qntTentativas++;

                if (tentativa < arvore) {
                    System.out.println("O marciano está em uma árvore MAIOR.");
                } else if (tentativa > arvore) {
                    System.out.println("O marciano está em uma árvore MENOR.");
                } else {
                    System.out.println("PARABÉNS! Você encontrou o marciano na árvore " + arvore + "!");
                    System.out.println("Total de tentativas: " + qntTentativas);
                }
            } else {
                System.out.println("Por favor, digite apenas números inteiros.");
                leitor.next();
            }
        }

        leitor.close();
    }
}