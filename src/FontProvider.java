import java.awt.*;
import java.io.File;

public class FontProvider {

    public Font gerarMichroma(){

        Font michroma = null;
        try {
            michroma = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("src/fonts/Michroma-Regular.ttf")
            );

        } catch (Exception e) {
            System.out.println("Erro ao carregar fonte! Erro: " + e.getMessage());
        }

        return michroma;
    }
}
