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

    private void montarJogo(Jogo jogo) {
        getContentPane().removeAll();
        JPanel tabuleiroMatriz = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);   // fundo do painel

                int colunas = jogo.getMatriz().length + 1;
                Component primeira = getComponent(colunas + 1);            // casa A1
                Component ultima = getComponent(getComponentCount() - 1);  // última casa

                int x = primeira.getX();
                int y = primeira.getY();
                int larg = ultima.getX() + ultima.getWidth() - x;
                int alt = ultima.getY() + ultima.getHeight() - y;

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(ImageProvider.carregar("agua.png"), x, y, larg, alt, null);
                g2.dispose();
            }

            @Override
            protected void paintChildren(Graphics g) {
                super.paintChildren(g);

                int colunas = jogo.getMatriz().length + 1;

                for (Embarcacao embarcacao : jogo.getEmbarcacoes()) {
                    int indice = (embarcacao.getLinha() + 1) * colunas + (embarcacao.getColuna() + 1);
                    Component casa = getComponent(indice);
                    Image img = ImageProvider.carregar(embarcacao.getArquivoImagem());

                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                    if (embarcacao.isHorizontal() || embarcacao.getTamanho() == 1) {
                        int larguraImg = casa.getWidth() * embarcacao.getTamanho();
                        g2.drawImage(img, casa.getX(), casa.getY(), larguraImg, casa.getHeight(), null);
                    } else {
                        int comprimento = casa.getHeight() * embarcacao.getTamanho();
                        g2.translate(casa.getX() + casa.getWidth(), casa.getY());
                        g2.rotate(Math.PI / 2);
                        g2.drawImage(img, 0, 0, comprimento, casa.getWidth(), null);
                    }

                    g2.dispose();
                }
            }
        };

        int[][] matriz = jogo.getMatriz();
        int tamanhoMatriz = matriz.length;

        //painel do tabuleiro
        tabuleiroMatriz.setLayout(
                new GridLayout(tamanhoMatriz + 1, tamanhoMatriz + 1)
        );
        tabuleiroMatriz.setPreferredSize(new Dimension(450, 450));

        JLabel mensagemTiro = new JLabel();
        mensagemTiro.setFont(MICHROMA.deriveFont(22f));
        mensagemTiro.setAlignmentX(CENTER_ALIGNMENT);

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
                    tabuleiroMatriz.add(new JLabel(""));

                } else if (i == 0) {
                    JLabel letra = new JLabel(letras.get(j));
                    letra.setFont(MICHROMA.deriveFont(30f));
                    letra.setHorizontalAlignment(SwingConstants.CENTER);
                    tabuleiroMatriz.add(letra);

                } else if (j == 0) {
                    JLabel numero = new JLabel(String.valueOf(i));
                    numero.setFont(MICHROMA.deriveFont(30f));
                    numero.setHorizontalAlignment(SwingConstants.CENTER);
                    tabuleiroMatriz.add(numero);

                } else {
                    JButton posicao = new JButton();
                    posicao.setContentAreaFilled(false);
                    posicao.setOpaque(false);
                    posicao.setFocusPainted(false);
                    posicao.addChangeListener(e -> tabuleiroMatriz.repaint());

                    int linhaMatriz = i - 1;
                    int colunaMatriz = j - 1;


                    posicao.addActionListener(e -> {
                        int tiro = matriz[linhaMatriz][colunaMatriz];

                        if (tiro == -1) {
                            posicao.setText("Água");
                            mensagemTiro.setText("Você acertou a água no "+ letras.get(colunaMatriz+1)+(linhaMatriz +1)+
                                    "!");
                        } else {
                            posicao.setText("Acerto");
                            Embarcacao embarcacao = jogo.registrarAcerto(linhaMatriz, colunaMatriz);
                            mensagemTiro.setText("Você acertou o "+embarcacao.getNome()+" no "+ letras.get(colunaMatriz+1)+(linhaMatriz +1)+
                                    "!");
                        }

                        jogo.registrarTiro();
                        totalTirosInt.setText(String.valueOf(jogo.getTotalTirosRestantes()));
                        posicao.setEnabled(false);
                        tabuleiroMatriz.repaint();
                        resultado.setText(jogo.seGanhou());
                        if (jogo.seGanhou().equals("Você ganhou!") || jogo.seGanhou().equals("Você perdeu!")) {
                            for (Component c : tabuleiroMatriz.getComponents()) {
                                if (c instanceof JButton) {
                                    c.setEnabled(false);
                                }
                            }
                        }
                    });

                    tabuleiroMatriz.add(posicao);
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

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.add(mensagemTiro);

        JPanel painelMeio = new JPanel(new BorderLayout());
        painelMeio.add(tabuleiroMatriz, BorderLayout.NORTH);

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
            String casaTxt;
            if (embarcacao.getTamanho() == 1) casaTxt = " casa";
            else casaTxt = " casas";
            JLabel nome = new JLabel(embarcacao.getNome() + " - " + embarcacao.getTamanho() + casaTxt);

            Image img = ImageProvider.carregar(embarcacao.getArquivoImagem());
            int larguraImagem = embarcacao.getTamanho() * 40;
            nome.setIcon(new ImageIcon(img.getScaledInstance(larguraImagem, 40, Image.SCALE_SMOOTH)));

            nome.setFont(MICHROMA.deriveFont(20f));
            nome.setAlignmentX(Component.CENTER_ALIGNMENT);
            listaEmbarcacoes.add(nome);
        }

        conteudoEsquerda.add(embarcacoesTotais);
        conteudoEsquerda.add(listaEmbarcacoes);


        JPanel painelEsquerda = new JPanel(new BorderLayout());
        painelEsquerda.add(conteudoEsquerda, BorderLayout.NORTH);

        int alturaLinha = tabuleiroMatriz.getPreferredSize().height / (tamanhoMatriz + 1);

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
        linhaJogo.add(painelMeio, BorderLayout.CENTER);
        linhaJogo.add(painelDireita, BorderLayout.EAST);
        linhaJogo.add(painelInferior, BorderLayout.SOUTH);


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