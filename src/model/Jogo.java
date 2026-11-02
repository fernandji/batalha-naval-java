package model;

import java.util.LinkedList;
import java.util.List;

public class Jogo {

    private int totalTirosRestantes;
    private int [][] matriz;
    private List<Embarcacao> embarcacoes = new LinkedList<>();

    public Jogo(int tamanho, int totalTiros, boolean dificuldade){
        this.matriz = new int [tamanho][tamanho];
        this.totalTirosRestantes = totalTiros;

        if(dificuldade){
            adicionar(4, "Submarino", 1);
            adicionar(2, "Destroier", 2);
        }
    }

    private void adicionar(int quantidade, String nome, int tamanho){
        for(int i = 0; i < quantidade; i++){
            embarcacoes.add(new Embarcacao(nome, tamanho));
        }
    }
}
