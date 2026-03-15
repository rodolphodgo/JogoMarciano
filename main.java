import java.util.*;
import java.io.*;

public class Main {

    static Scanner leitor = new Scanner(System.in);
    static Random gerador = new Random();
    static final String ARQUIVO = "ranking.txt";

    public static void main(String[] args) {

        boolean executando = true;

        mostrarHistoria();

        while (executando) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Jogar");
            System.out.println("2 - Ver Ranking");
            System.out.println("3 - Sair");

            System.out.print("Escolha: ");
            int opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {

                case 1:
                    jogar();
                    break;

                case 2:
                    mostrarRanking();
                    break;

                case 3:
                    executando = false;
                    System.out.println("Encerrando jogo...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    static void mostrarHistoria() {

        System.out.println("=================================");
        System.out.println("        CAÇA AO MARCIANO ");
        System.out.println("=================================");
        System.out.println("Um marciano pousou na floresta...");
        System.out.println("Ele se escondeu em uma árvore.");
        System.out.println("Sua missão é encontrá-lo antes que fuja!");
    }

    static void jogar() {

        System.out.print("\nDigite seu nome: ");
        String nome = leitor.nextLine();

        System.out.println("\nEscolha dificuldade:");
        System.out.println("1 - Fácil (1 a 100 / 10 tentativas)");
        System.out.println("2 - Médio (1 a 100 / 7 tentativas)");
        System.out.println("3 - Difícil (1 a 200 / 7 tentativas)");

        int escolha = leitor.nextInt();

       int limiteNumero = 100;
        int limiteTentativas = 7;

        if (escolha == 1) {
            limiteTentativas = 10;
        }
        else if (escolha == 2) {
            limiteTentativas = 7;
        }
        else if (escolha == 3) {
            limiteNumero = 200;
            limiteTentativas = 7;
        }

        int numero = gerador.nextInt(limiteNumero) + 1;

        int tentativa;
        int tentativas = 0;
        boolean venceu = false;

        while (tentativas < limiteTentativas) {

            System.out.print("\nTentativa " + (tentativas + 1) + "/" + limiteTentativas + ": ");
            tentativa = leitor.nextInt();
            tentativas++;

            if (tentativa < numero) {
                System.out.println("O marciano está em uma árvore MAIOR.");
            }
            else if (tentativa > numero) {
                System.out.println("O marciano está em uma árvore MENOR.");
            }
            else {
                venceu = true;
                break;
            }
        }

        if (venceu) {

            int pontuacao = (limiteTentativas - tentativas + 1) * 10;

            System.out.println("\n Você venceu!");
            System.out.println("Pontuação: " + pontuacao);

            salvarJogador(new Jogador(nome, pontuacao));
        }
        else {

            System.out.println("\n Você perdeu!");
            System.out.println("A árvore correta era: " + numero);
        }
    }

    static void salvarJogador(Jogador jogador) {

        try {

            FileWriter writer = new FileWriter(ARQUIVO, true);
            writer.write(jogador.nome + ";" + jogador.pontuacao + "\n");
            writer.close();

        } catch (Exception e) {

            System.out.println("Erro ao salvar ranking");
        }
    }

    static void mostrarRanking() {

        ArrayList<Jogador> ranking = new ArrayList<>();

        try {

            File arquivo = new File(ARQUIVO);

            if (!arquivo.exists()) {
                System.out.println("Nenhum ranking ainda.");
                return;
            }

            Scanner leitorArquivo = new Scanner(arquivo);

            while (leitorArquivo.hasNextLine()) {

                String linha = leitorArquivo.nextLine();
                String[] partes = linha.split(";");

                String nome = partes[0];
                int pontuacao = Integer.parseInt(partes[1]);

                ranking.add(new Jogador(nome, pontuacao));
            }

            leitorArquivo.close();

        } catch (Exception e) {

            System.out.println("Erro ao ler ranking");
        }

        ranking.sort((a, b) -> b.pontuacao - a.pontuacao);

        System.out.println("\n TOP 10");

        for (int i = 0; i < ranking.size() && i < 10; i++) {

            Jogador j = ranking.get(i);

            System.out.println((i + 1) + "º - " + j.nome + " | " + j.pontuacao + " pontos");
        }
    }
}