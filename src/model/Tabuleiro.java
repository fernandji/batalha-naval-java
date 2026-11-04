package model;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tabuleiro extends JFrame {


    private final Font MICHROMA = FontProvider.gerarMichroma();

    public Tabuleiro(Jogo jogo) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        montarJogo(jogo);
    }

    private void montarJogo(Jogo jogo){
        getContentPane().removeAll();
        JPanel tabuleiroPanel = new JPanel();

        int[][] matriz = jogo.getMatriz();
        int tamanhoMatriz = matriz.length;

        //painel do tabuleiro
        tabuleiroPanel.setLayout(
                new GridLayout(tamanhoMatriz + 1, tamanhoMatriz + 1)
        );
        tabuleiroPanel.setPreferredSize(new Dimension(450, 450));

        JLabel totalTirosTxt = new JLabel("Total de tiros restantes: ");
        totalTirosTxt.setFont(MICHROMA.deriveFont(22f));

        JLabel totalTirosInt = new JLabel(String.valueOf(jogo.getTotalTirosRestantes()));
        totalTirosInt.setFont(MICHROMA.deriveFont(22f));

        JLabel resultado = new JLabel();
        resultado.setFont(MICHROMA.deriveFont(30f));


        Map<Integer, String> letras = new LinkedHashMap<>();
        letras.put(1, "A");
        letras.put(2, "B");
        letras.put(3, "C");
        letras.put(4, "D");
        letras.put(5, "E");
        letras.put(6, "F");
        letras.put(7, "G");
        letras.put(8, "H");

        for (int i = 0; i <= tamanhoMatriz; i++) {
            for (int j = 0; j <= tamanhoMatriz; j++) {

                if (i == 0 && j == 0) {
                    tabuleiroPanel.add(new JLabel(""));

                } else if (i == 0) {
                    JLabel letra = new JLabel(letras.get(j));
                    letra.setFont(MICHROMA.deriveFont(30f));
                    letra.setHorizontalAlignment(SwingConstants.CENTER);
                    tabuleiroPanel.add(letra);

                } else if (j == 0) {
                    JLabel numero = new JLabel(String.valueOf(i));
                    numero.setFont(MICHROMA.deriveFont(30f));
                    numero.setHorizontalAlignment(SwingConstants.CENTER);
                    tabuleiroPanel.add(numero);

                } else {
                    JButton posicao = new JButton();
                    posicao.setBackground(new Color(121, 183, 216));
                    posicao.setFocusPainted(false);

                    int linhaMatriz = i - 1;
                    int colunaMatriz = j - 1;

                    //temporário, apenas para mostrar navios durante o desenvolvimento
                    if (matriz[linhaMatriz][colunaMatriz] != -1) {
                        posicao.setText("N");
                    }

                    posicao.addActionListener(e -> {
                        int embarcacao = matriz[linhaMatriz][colunaMatriz];

                        if (embarcacao == -1) {
                            posicao.setText("Água");
                        } else {
                            posicao.setText("Acerto");
                            jogo.registrarAcerto();
                        }

                        jogo.registrarTiro();
                        totalTirosInt.setText(String.valueOf(jogo.getTotalTirosRestantes()));
                        resultado.setText(jogo.seGanhou());
                        posicao.setEnabled(false);
                    });

                    tabuleiroPanel.add(posicao);
                }
            }
        }


        JPanel conteudoDireita = new JPanel();
        conteudoDireita.setLayout(new BoxLayout(conteudoDireita, BoxLayout.Y_AXIS));

        totalTirosTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalTirosInt.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultado.setAlignmentX(Component.CENTER_ALIGNMENT);

        conteudoDireita.add(totalTirosTxt);
        conteudoDireita.add(totalTirosInt);
        conteudoDireita.add(Box.createVerticalStrut(15));
        conteudoDireita.add(resultado);

        JPanel painelDireita = new JPanel(new BorderLayout());
        painelDireita.add(conteudoDireita, BorderLayout.NORTH);

        JPanel conteudoEsquerda = new JPanel();
        conteudoEsquerda.setLayout(new BoxLayout(conteudoEsquerda, BoxLayout.Y_AXIS));

        JLabel embarcacoesTotais = new JLabel("Embarcações restantes:");
        embarcacoesTotais.setFont(MICHROMA.deriveFont(22f));
        embarcacoesTotais.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel listaEmbarcacoes = new JPanel();
        listaEmbarcacoes.setLayout(new BoxLayout(listaEmbarcacoes, BoxLayout.Y_AXIS));

        for (Embarcacao embarcacao : jogo.getEmbarcacoes()) {
            JLabel nome = new JLabel(embarcacao.getNome());
            nome.setFont(MICHROMA.deriveFont(20f));
            nome.setAlignmentX(Component.CENTER_ALIGNMENT);
            listaEmbarcacoes.add(nome);
        }

        conteudoEsquerda.add(embarcacoesTotais);
        conteudoEsquerda.add(listaEmbarcacoes);


        JPanel painelEsquerda = new JPanel(new BorderLayout());
        painelEsquerda.add(conteudoEsquerda, BorderLayout.NORTH);

        int alturaLinha = tabuleiroPanel.getPreferredSize().height / (tamanhoMatriz + 1);

        painelDireita.setBorder(BorderFactory.createEmptyBorder(alturaLinha, 0, 0, 0));
        painelEsquerda.setBorder(BorderFactory.createEmptyBorder(alturaLinha, 0, 0, 0));

        int largura = Math.max(
                painelEsquerda.getPreferredSize().width,
                painelDireita.getPreferredSize().width
        );

        largura = Math.max(largura, 350);
        painelEsquerda.setPreferredSize(new Dimension(largura, 0));
        painelDireita.setPreferredSize(new Dimension(largura, 0));


        JPanel linhaJogo = new JPanel(new BorderLayout(30, 0));
        linhaJogo.add(painelEsquerda, BorderLayout.WEST);
        linhaJogo.add(tabuleiroPanel, BorderLayout.CENTER);
        linhaJogo.add(painelDireita, BorderLayout.EAST);


        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.add(linhaJogo);


        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setFont(MICHROMA.deriveFont(20f));
        btnReiniciar.setFocusPainted(false);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(MICHROMA.deriveFont(20f));
        btnVoltar.setFocusPainted(false);

        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelSuperior.add(btnVoltar);
        painelSuperior.add(btnReiniciar);

        add(painelSuperior, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);

        revalidate();
        repaint();

        btnVoltar.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });

        btnReiniciar.addActionListener(e -> {
            montarJogo(jogo.reiniciar());
        });
    }
}