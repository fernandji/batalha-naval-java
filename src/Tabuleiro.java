import javax.swing.*;
import java.awt.*;

public class Tabuleiro extends JFrame {

    public Tabuleiro(int linhas, int colunas){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setLayout(new BorderLayout());
        setSize(800,600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
