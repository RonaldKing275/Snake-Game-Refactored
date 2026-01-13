import java.awt.Graphics;

// Interfejs Stanu
interface GameState {
    void update();
    void render(Graphics g);
    void handleInput(int keyCode);
}