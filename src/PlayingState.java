import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PlayingState implements GameState {
    private final Board context;
    private final Snake snake;
    private Point apple;
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final Random rand = new Random();

    public PlayingState(Board context) {
        this.context = context;
        this.snake = GameFactory.createSnake(); // Użycie Fabryki
        locateApple();
    }

    @Override
    public void update() {
        // Obliczanie przyszłej pozycji głowy (nextHead)
        Point head = snake.getBody().getFirst();
        Point nextHead = new Point(head);

        switch (snake.getDirection()) {
            case UP -> nextHead.y -= 10;
            case DOWN -> nextHead.y += 10;
            case LEFT -> nextHead.x -= 10;
            case RIGHT -> nextHead.x += 10;
        }

        // Sprawdzanie kolizji ZANIM wąż się ruszy (Strict Collision)
        // Jeśli wąż uderzy w ścianę LUB w swoje ciało (w tym ogon) -> Game Over
        if (checkWallCollision(nextHead) || checkBodyCollision(nextHead)) {
            context.setState(new GameOverState(context, snake.getBody().size() - 3));
            return; // Kończymy update, nie ruszamy węża dalej
        }

        // Sprawdzanie jabłka i wykonanie ruchu
        boolean grow = false;
        if (nextHead.equals(apple)) {
            grow = true;
            locateApple();
        }

        snake.move(grow);
    }

    // Pomocnicza metoda do sprawdzania ścian
    private boolean checkWallCollision(Point p) {
        return p.x < 0 || p.x >= B_WIDTH || p.y < 0 || p.y >= B_HEIGHT;
    }

    // Pomocnicza metoda do sprawdzania ciała
    private boolean checkBodyCollision(Point p) {
        return snake.getBody().contains(p);
    }

    @Override
    public void render(Graphics g) {
        GameAssets assets = GameAssets.getInstance(); // Użycie Singletona

        // Rysuj jabłko
        g.drawImage(assets.getImage("apple"), apple.x, apple.y, null);

        // Rysuj węża
        int i = 0;
        for (Point p : snake.getBody()) {
            if (i == 0) {
                String headImg = switch(snake.getDirection()) {
                    case UP -> "headUp";
                    case DOWN -> "headDown";
                    case LEFT -> "headLeft";
                    case RIGHT -> "headRight";
                };
                g.drawImage(assets.getImage(headImg), p.x, p.y, null);
            } else {
                g.drawImage(assets.getImage("body"), p.x, p.y, null);
            }
            i++;
        }
    }

    @Override
    public void handleInput(int key) {
        // Użycie wzorca Polecenie
        Command command = null;
        switch (key) {
            case KeyEvent.VK_LEFT -> command = new ChangeDirectionCommand(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> command = new ChangeDirectionCommand(Direction.RIGHT);
            case KeyEvent.VK_UP -> command = new ChangeDirectionCommand(Direction.UP);
            case KeyEvent.VK_DOWN -> command = new ChangeDirectionCommand(Direction.DOWN);
        }
        if (command != null) {
            command.execute(snake);
        }
    }

    private void locateApple() {
        int r = rand.nextInt(29);
        apple = new Point(r * 10, rand.nextInt(29) * 10);
    }
}