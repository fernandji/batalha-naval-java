package view;

import model.Jogo;
import util.FontProvider;
import util.ImageProvider;
import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private final Font MICHROMA = FontProvider.gerarMichroma();

    JLabel batalhaNavallbl = new JLabel("BATALHA NAVAL");
    JLabel apresentacaolbl = new JLabel("Seja bem-vindo ao clássico jogo de Tabuleiro!");
    JLabel apresentacaolbl2 = new JLabel("Nível Fácil - Tabuleiro 6x6 | Nível Difícil - Tabuleiro 8x8");
    JLabel dificuldadelbl = new JLabel("Selecione a dificuldade:");
    JButton btnFacil = new JButton("Fácil");
    JButton btnDificil = new JButton("Difícil");
    JPanel conteudoCentral = new JPanel();
    JPanel botoesPanel = new JPanel();

    public Menu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setIconImage(ImageProvider.carregar("icone.png"));
        setLayout(new BorderLayout(5, 5));
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image img = ImageProvider.carregar("Tela-Inicial.png");
                int iw = img.getWidth(null);
                int ih = img.getHeight(null);

                double escala = Math.max((double) getWidth() / iw, (double) getHeight() / ih);
                int w = (int) (iw * escala);
                int h = (int) (ih * escala);
                int x = (getWidth() - w) / 2;
                int y = (getHeight() - h) / 2;

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(img, x, y, w, h, this);
                g2.dispose();
            }
        });

        batalhaNavallbl.setFont(MICHROMA.deriveFont(40f));
        apresentacaolbl.setFont(MICHROMA.deriveFont(22f));
        apresentacaolbl2.setFont(MICHROMA.deriveFont(22f));
        dificuldadelbl.setFont(MICHROMA.deriveFont(22f));
        batalhaNavallbl.setForeground(Color.WHITE);
        apresentacaolbl.setForeground(Color.WHITE);
        apresentacaolbl2.setForeground(Color.WHITE);
        dificuldadelbl.setForeground(Color.WHITE);


        conteudoCentral.setLayout(new GridBagLayout());
        conteudoCentral.setOpaque(false);

        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        botoesPanel.setOpaque(false);


        btnFacil.setBackground(new Color(255, 255, 255));
        btnFacil.setFont(MICHROMA.deriveFont(22f));
        btnFacil.setFocusPainted(false);
        btnFacil.setPreferredSize(new Dimension(180, 50));

        btnDificil.setBackground(new Color(255, 255, 255));
        btnDificil.setFont(MICHROMA.deriveFont(22f));
        btnDificil.setFocusPainted(false);
        btnDificil.setPreferredSize(new Dimension(180, 50));

        botoesPanel.add(btnFacil);
        botoesPanel.add(btnDificil);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;


        gbc.gridy = 0;
        gbc.weighty = 1.0;
        conteudoCentral.add(Box.createVerticalGlue(), gbc);


        gbc.gridy = 1;
        gbc.weighty = 0;
        conteudoCentral.add(batalhaNavallbl, gbc);


        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        conteudoCentral.add(apresentacaolbl, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 20, 0);
        conteudoCentral.add(apresentacaolbl2, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        conteudoCentral.add(dificuldadelbl, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 0, 0);
        conteudoCentral.add(botoesPanel, gbc);

        gbc.gridy = 6;
        gbc.weighty = 1.0;
        conteudoCentral.add(Box.createVerticalGlue(), gbc);


        add(conteudoCentral, BorderLayout.CENTER);

        btnFacil.addActionListener(e -> {
            Jogo jogo = new Jogo(6,30, true);
            Tabuleiro tabuleiro = new Tabuleiro(jogo);
            tabuleiro.setVisible(true);
            dispose();
        });

        btnDificil.addActionListener(e -> {
            Jogo jogo = new Jogo(8, 40, false);
            Tabuleiro tabuleiro = new Tabuleiro(jogo);
            tabuleiro.setVisible(true);
            dispose();
        });
    }
}
