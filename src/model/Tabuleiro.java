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
        tabuleiroPanel.setPreferredSize(new Dimension(450, 450));

        JLabel totalTirosTxt = new JLabel("Total de tiros restantes: ");
        totalTirosTxt.setFont(michroma.deriveFont(22f));

        JLabel totalTirosInt = new JLabel(String.valueOf(jogo.getTotalTirosRestantes()));
        totalTirosInt.setFont(michroma.deriveFont(22f));

        JLabel resultado = new JLabel();
        resultado.setFont(michroma.deriveFont(30f));


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


        JPanel painelDireita = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;

        gbc.gridy = 0;
        painelDireita.add(totalTirosTxt, gbc);

        gbc.gridy = 1;
        painelDireita.add(totalTirosInt, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        painelDireita.add(resultado, gbc);


        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.add(tabuleiroPanel);

        JButton btnJogarNovamente = new JButton("Jogar novamente");
        btnJogarNovamente.setFont(michroma.deriveFont(20f));
        btnJogarNovamente.setFocusPainted(false);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(michroma.deriveFont(20f));
        btnVoltar.setFocusPainted(false);

        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelEsquerda.add(btnVoltar);
        painelEsquerda.add(btnJogarNovamente);

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