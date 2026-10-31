import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tabuleiro extends JFrame {

    JPanel tabuleiroPanel = new JPanel();


    public Tabuleiro(int linhas, int colunas) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setBackground(new Color(225, 216, 216));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        final int LINHAS = linhas;
        final int COLUNAS = colunas;

        int[][] matriz = new int[LINHAS][COLUNAS];

        tabuleiroPanel.setLayout(new GridLayout(LINHAS + 1, COLUNAS + 1));
        tabuleiroPanel.setPreferredSize(new Dimension(500, 500));

        for (int i = 0; i < LINHAS + 1; i++) {
            for (int j = 0; j < COLUNAS + 1; j++) {

                if (i == 0 && j == 0) {
                    tabuleiroPanel.add(new JLabel(""));

                } else if (i == 0) {
                    Map<Integer, String> letras = new LinkedHashMap<>();
                    letras.put(1, "A");
                    letras.put(2, "B");
                    letras.put(3, "C");
                    letras.put(4, "D");
                    letras.put(5, "E");
                    letras.put(6, "F");
                    letras.put(7, "G");
                    letras.put(8, "H");

                    JLabel letra = new JLabel(letras.get(j));
                    letra.setFont(new Font("Arial", Font.BOLD, 30));
                    JPanel campo = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    campo.add(letra);
                    tabuleiroPanel.add(campo);

                } else if (j == 0) {
                    JLabel numero = new JLabel(String.valueOf(i));
                    numero.setFont(new Font("Arial", Font.BOLD, 30));
                    tabuleiroPanel.add(numero);

                } else {
                    JButton posicao = new JButton();
                    posicao.setBackground(new Color(121, 183, 216));
                    tabuleiroPanel.add(posicao);
                }

            }
        }

        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.add(tabuleiroPanel);

        add(painelCentral, BorderLayout.CENTER);


    }
}
