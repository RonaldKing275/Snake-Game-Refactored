// Konkretne polecenia
public class ChangeDirectionCommand implements Command {
    private final Direction newDir;

    public ChangeDirectionCommand(Direction newDir) {
        this.newDir = newDir;
    }

    @Override
    public void execute(Snake snake) {
        snake.setDirection(newDir);
    }
}
