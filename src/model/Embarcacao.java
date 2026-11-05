package model;

public class Embarcacao {
    private String nome;
    private int tamanho;
    private int partesAtingidas;
    private int linha;
    private int coluna;
    private boolean horizontal;

    public Embarcacao(String nome, int tamanho){
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public void registrarAcerto(){
        partesAtingidas += 1;
    }

    public boolean isAfundada(){
        return partesAtingidas == tamanho;
    }

    public String getArquivoImagem() {
        return switch (nome) {
            case "Submarino" -> "submarino.png";
            case "Destroier" -> "destroier.png";
            case "Cruzador" -> "cruzador.png";
            case "Encouraçado" -> "encouracado.png";
            case "Porta-aviões" -> "portavioes.png";
            default -> throw new IllegalStateException("Sem imagem para: " + nome);
        };
    }

    public void posicionar(int linha, int coluna, boolean horizontal) {
        this.linha = linha;
        this.coluna = coluna;
        this.horizontal = horizontal;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean isHorizontal() {
        return horizontal;
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
