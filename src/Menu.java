import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JFrame {

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
        setLayout(new BorderLayout(5, 5));
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        Font michroma = null;
        try {
            michroma = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("src/fonts/Michroma-Regular.ttf")
            );
            batalhaNavallbl.setFont(michroma.deriveFont(40f));
            apresentacaolbl.setFont(michroma.deriveFont(22f));
            apresentacaolbl2.setFont(michroma.deriveFont(22f));
            dificuldadelbl.setFont(michroma.deriveFont(22f));
        } catch (Exception e) {
            System.out.println("Erro ao carregar fonte! Erro: " + e.getMessage());
        }


        conteudoCentral.setLayout(new GridBagLayout());
        conteudoCentral.setBackground(new Color(225, 216, 216));

        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        botoesPanel.setBackground(new Color(225, 216, 216));


        btnFacil.setBackground(new Color(255, 255, 255));
        btnFacil.setFont(michroma.deriveFont(22f));
        btnFacil.setFocusPainted(false);
        btnFacil.setPreferredSize(new Dimension(180, 50));

        btnDificil.setBackground(new Color(255, 255, 255));
        btnDificil.setFont(michroma.deriveFont(22f));
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
            Tabuleiro tabuleiro = new Tabuleiro(6,6);
            tabuleiro.setVisible(true);
            dispose();
        });

        btnDificil.addActionListener(e -> {
            Tabuleiro tabuleiro = new Tabuleiro(8,8);
            tabuleiro.setVisible(true);
            dispose();
        });
    }
}
