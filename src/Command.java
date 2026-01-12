// Interfejs Polecenie
public interface Command {
    void execute(Snake snake);
}

// Konkretne polecenia
class ChangeDirectionCommand implements Command {
    private final Direction newDir;

    public ChangeDirectionCommand(Direction newDir) {
        this.newDir = newDir;
    }

    @Override
    public void execute(Snake snake) {
        snake.setDirection(newDir);
    }
}

// Typ wyliczeniowy dla kierunków (pomocniczy)
enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public boolean isOpposite(Direction other) {
        return (this == UP && other == DOWN) || (this == DOWN && other == UP) ||
                (this == LEFT && other == RIGHT) || (this == RIGHT && other == LEFT);
    }
}