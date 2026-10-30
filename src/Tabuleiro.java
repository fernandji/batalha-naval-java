import javax.swing.*;
import java.awt.*;

public class Tabuleiro extends JFrame {

    JPanel tabuleiroPanel = new JPanel();

    public Tabuleiro(int linhas, int colunas){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setLayout(new BorderLayout());
        setSize(800,600);
        setBackground(new Color(225, 216, 216));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        final int LINHAS = linhas;
        final int COLUNAS = colunas;

        int[][] matriz = new int[LINHAS][COLUNAS];

        tabuleiroPanel.setLayout(new GridLayout(LINHAS, COLUNAS));

        for(int i = 0; i < LINHAS; i++){
            for(int j = 0; j < COLUNAS; j++){

                JButton posicao = new JButton();
                posicao.setBackground(new Color(121, 183, 216));

                tabuleiroPanel.add(posicao);
            }
        }

        add(tabuleiroPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
