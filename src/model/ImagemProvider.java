package model;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImagemProvider {
    private static final Map<String, Image> cache = new HashMap<>();

    public static Image carregar(String arquivo) {
        return cache.computeIfAbsent(arquivo, a -> {
            URL url = ImagemProvider.class.getResource("/images/" + a);
            if (url == null) {
                throw new IllegalStateException("Imagem não encontrada: " + a);
            }
            return new ImageIcon(url).getImage();
        });
    }
}