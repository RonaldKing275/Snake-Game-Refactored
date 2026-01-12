import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GameAssets {
    // Singleton: jedna instancja w całej aplikacji
    private static GameAssets instance;
    private Map<String, Image> images = new HashMap<>();

    private GameAssets() {}

    public static synchronized GameAssets getInstance() {
        if (instance == null) {
            instance = new GameAssets();
        }
        return instance;
    }

    public Image getImage(String name) {
        if (!images.containsKey(name)) {
            URL url = getClass().getResource("/resources/" + name + ".png");
            if (url == null) throw new RuntimeException("Image not found: " + name);
            images.put(name, new ImageIcon(url).getImage());
        }
        return images.get(name);
    }
}