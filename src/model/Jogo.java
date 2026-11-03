package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Jogo {

    private int totalTirosRestantes;
    private int acertos;
    private int[][] matriz;
    private List<Embarcacao> embarcacoes = new LinkedList<>();
    private Random random = new Random();

    public Jogo(int tamanho, int totalTiros, boolean dificuldade) {
        this.matriz = new int[tamanho][tamanho];
        this.totalTirosRestantes = totalTiros;

        if (dificuldade) {
            adicionar(4, "Submarino", 1);
            adicionar(2, "Destroier", 2);
            adicionar(1, "Cruzador", 3);
            adicionar(1, "Encouraçado", 4);
            adicionar(1, "Porta-aviões", 5);
        } else {
            adicionar(6, "Submarino", 1);
            adicionar(2, "Destroier", 2);
            adicionar(2, "Cruzador", 3);
            adicionar(2, "Encouraçado", 4);
            adicionar(1, "Porta-aviões", 5);
        }

        posicionarEmbarcacoes();
    }

    private void adicionar(int quantidade, String nome, int tamanho) {
        for (int i = 0; i < quantidade; i++) {
            embarcacoes.add(new Embarcacao(nome, tamanho));
        }
    }

    private void posicionarEmbarcacoes() {
        int tentativasMaximas = 1_000;

        for (int tentativa = 0; tentativa < tentativasMaximas; tentativa++) {
            for (int[] linha : matriz) {
                Arrays.fill(linha, -1);
            }

            boolean todosPosicionados = true;

            for (int i = 0; i < embarcacoes.size(); i++) {
                int tamanhoNavio = embarcacoes.get(i).getTamanho();
                boolean posicionado = false;

                for (int tentativaNavio = 0;
                     tentativaNavio < 500 && !posicionado;
                     tentativaNavio++) {

                    boolean horizontal = random.nextBoolean();
                    int linha = random.nextInt(matriz.length);
                    int coluna = random.nextInt(matriz.length);

                    boolean livre = true;

                    for (int parte = 0; parte < tamanhoNavio; parte++) {
                        int linhaAtual = horizontal ? linha : linha + parte;
                        int colunaAtual = horizontal ? coluna + parte : coluna;

                        if (linhaAtual >= matriz.length
                                || colunaAtual >= matriz.length
                                || matriz[linhaAtual][colunaAtual] != -1) {
                            livre = false;
                            break;
                        }
                    }

                    if (livre) {
                        for (int parte = 0; parte < tamanhoNavio; parte++) {
                            int linhaAtual = horizontal ? linha : linha + parte;
                            int colunaAtual = horizontal ? coluna + parte : coluna;

                            matriz[linhaAtual][colunaAtual] = i;
                        }

                        posicionado = true;
                    }
                }

                if (!posicionado) {
                    todosPosicionados = false;
                    break;
                }
            }

            if (todosPosicionados) {
                return;
            }
        }

        throw new IllegalStateException(
                "Não foi possível posicionar todos os navios."
        );
    }

    public boolean registrarTiro(){
        if(totalTirosRestantes == 0){
            return true;
        }
        totalTirosRestantes -= 1;
        return false;

    }

    public void registrarAcerto(){
        acertos += 1;
    }

    public String seGanhou(){
        if(totalTirosRestantes >= 0 && acertos == 20){
            return "Você ganhou!";
        } else if(totalTirosRestantes == 0 && acertos < 20){
            return("Você perdeu!");
        }
        return null;
    }



    public int getTotalTirosRestantes() {
        return totalTirosRestantes;
    }

    public void setTotalTirosRestantes(int totalTirosRestantes) {
        this.totalTirosRestantes = totalTirosRestantes;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public List<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public void setEmbarcacoes(List<Embarcacao> embarcacoes) {
        this.embarcacoes = embarcacoes;
    }
}