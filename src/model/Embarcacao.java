package model;

public class Embarcacao {
    private String nome;
    private int tamanho;
    private int partesAtingidas;

    public void registrarAcerto(){
        partesAtingidas += 1;
    }

    public boolean isAfundada(){
        return partesAtingidas == tamanho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPartesAtingidas() {
        return partesAtingidas;
    }

    public void setPartesAtingidas(int partesAtingidas) {
        this.partesAtingidas = partesAtingidas;
    }
}
