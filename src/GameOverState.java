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
        g.drawString(msg, (int)(context.width*0.33), (int)(context.height*0.45));
        g.drawString("Press SPACE to restart", (int)(context.width*0.31), (int)(context.height*0.55));
    }

    @Override
    public void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            context.setState(new PlayingState(context));
        }
    }
}