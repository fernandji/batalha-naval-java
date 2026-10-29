import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Menu extends JFrame {

    JLabel batalhaNavallbl = new JLabel("BATALHA NAVAL");

    public Menu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setLayout(new BorderLayout(5, 5));
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        try {
            Font michroma = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("src/fonts/Michroma-Regular.ttf")
            );
            batalhaNavallbl.setFont(michroma.deriveFont(36f));
        } catch (Exception e) {
            System.out.println("Erro ao carregar fonte! Erro: " + e.getMessage());
        }


        batalhaNavallbl.setHorizontalAlignment(SwingConstants.CENTER);

        add(batalhaNavallbl, BorderLayout.CENTER);
    }
}
