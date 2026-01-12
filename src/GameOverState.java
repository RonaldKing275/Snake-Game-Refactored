import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState implements GameState {
    private final int score;
    private final Board context;
    private boolean saved = false;

    public GameOverState(Board context, int score) {
        this.context = context;
        this.score = score;
    }

    @Override
    public void update() {
        if (!saved) {
            // Użycie Fasady
            new ScoreFacade().saveScore(context.getUserName(), score);
            saved = true;
            System.out.println("Score saved.");
        }
    }

    @Override
    public void render(Graphics g) {
        String msg = "Game Over. Score: " + score;
        g.setColor(Color.white);
        g.drawString(msg, 100, 150);
        g.drawString("Press SPACE to restart", 90, 170);
    }

    @Override
    public void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            context.setState(new PlayingState(context));
        }
    }
}