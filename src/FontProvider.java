import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontProvider {

    public static Font gerarMichroma() {


        try (InputStream is = FontProvider.class.getResourceAsStream("/fonts/Michroma-Regular.ttf")) {
            if (is == null) {
                throw new IOException("Arquivo da fonte não encontrado");
            }
            return Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException | IOException e) {
            System.out.println("Erro ao carregar fonte! Erro: " + e.getMessage());
            return new Font(Font.SANS_SERIF, Font.BOLD, 12);
        }
    }
}
