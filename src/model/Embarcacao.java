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
}
