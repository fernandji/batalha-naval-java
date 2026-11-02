package model;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tabuleiro extends JFrame {

    private final JPanel tabuleiroPanel = new JPanel();

    public Tabuleiro(Jogo jogo) {
        int[][] matriz = jogo.getMatriz();
        int tamanhoMatriz = matriz.length;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Font michroma = FontProvider.gerarMichroma();

        tabuleiroPanel.setLayout(
                new GridLayout(tamanhoMatriz + 1, tamanhoMatriz + 1)
        );
        tabuleiroPanel.setPreferredSize(new Dimension(500, 500));

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
                    letra.setFont(michroma.deriveFont(30f));
                    letra.setHorizontalAlignment(SwingConstants.CENTER);
                    tabuleiroPanel.add(letra);

                } else if (j == 0) {
                    JLabel numero = new JLabel(String.valueOf(i));
                    numero.setFont(michroma.deriveFont(30f));
                    numero.setHorizontalAlignment(SwingConstants.CENTER);
                    tabuleiroPanel.add(numero);

                } else {
                    JButton posicao = new JButton();
                    posicao.setBackground(new Color(121, 183, 216));

                    int linhaMatriz = i - 1;
                    int colunaMatriz = j - 1;

                    //temporário, apenas para mostrar navios durante o desenvolvimento
                    if (matriz[linhaMatriz][colunaMatriz] != -1) {
                        posicao.setText("N");
                    }

                    tabuleiroPanel.add(posicao);
                }
            }
        }

        JLabel totalTiros = new JLabel(
                "Total de tiros restantes: " + jogo.getTotalTirosRestantes()
        );

        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelDireita.add(totalTiros);

        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.add(tabuleiroPanel);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(michroma.deriveFont(20f));
        btnVoltar.setFocusPainted(false);

        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelEsquerda.add(btnVoltar);

        add(painelCentral, BorderLayout.CENTER);
        add(painelDireita, BorderLayout.EAST);
        add(painelEsquerda, BorderLayout.WEST);

        btnVoltar.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });
    }
}