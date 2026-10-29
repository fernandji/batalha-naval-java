import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    JLabel batalhaNavallbl = new JLabel("BATALHA NAVAL");

    public Menu(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Batalha Naval");
        setLayout(new BorderLayout(5,5));
        setSize(800,600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(batalhaNavallbl, BorderLayout.NORTH);
    }
}
