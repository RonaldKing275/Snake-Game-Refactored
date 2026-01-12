import java.awt.Graphics;
import java.awt.event.KeyEvent;

// Interfejs Stanu
interface GameState {
    void update();
    void render(Graphics g);
    void handleInput(int keyCode);
}