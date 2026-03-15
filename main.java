import java.util.Scanner;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Random gerador = new Random();

        int arvore = gerador.nextInt(100) + 1;
        int tentativa = 0;
        int qntTentativas = 0;
        
        int limiteMaximo = 7;
        boolean venceu = false;

        System.out.println("\nO marciano se escondeu em uma árvore de 1 a 100.");
        System.out.println("Você tem apenas " + limiteMaximo + " tentativas para encontrá-lo!");

        while (tentativa != arvore && qntTentativas < limiteMaximo) {
            System.out.print("\nTentativa " + (qntTentativas + 1) + "/" + limiteMaximo + " - Digite a árvore: ");
            
            if (leitor.hasNextInt()) {
                tentativa = leitor.nextInt();
                qntTentativas++;

                if (tentativa < arvore) {
                    System.out.println("O marciano está em uma árvore MAIOR.");
                } else if (tentativa > arvore) {
                    System.out.println("O marciano está em uma árvore MENOR.");
                } else {
                    venceu = true;
                }
            } else {
                System.out.println("Por favor, digite apenas números inteiros.");
                leitor.next();
            }
        }

        if (venceu) {
            System.out.println("\nPARABÉNS! Você encontrou o marciano na árvore " + arvore + "!");
            System.out.println("Total de tentativas: " + qntTentativas);
        } else {
            System.out.println("\nGAME OVER! O marciano fugiu para outro planeta.");
            System.out.println("A árvore correta era a " + arvore + ".");
        }

        leitor.close();
    }
}