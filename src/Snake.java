import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private final LinkedList<Point> body = new LinkedList<>();
    private Direction direction = Direction.RIGHT;
    private boolean canMove = true; // Flaga blokująca

    public Snake(int startLength) {
        for (int i = 0; i < startLength; i++) {
            body.add(new Point(50 - i * 10, 50));
        }
    }

    public void setDirection(Direction newDir) {
        if (canMove && !direction.isOpposite(newDir)) {
            this.direction = newDir;
            canMove = false; // Zablokuj kolejne zmiany w tej turze
        }
    }

    public void move(boolean grow) {
        Point head = body.getFirst();
        Point newHead = new Point(head);

        switch (direction) {
            case UP -> newHead.y -= 10;
            case DOWN -> newHead.y += 10;
            case LEFT -> newHead.x -= 10;
            case RIGHT -> newHead.x += 10;
        }

        body.addFirst(newHead);
        if (!grow) body.removeLast();

        canMove = true; // Odblokuj sterowanie po wykonaniu ruchu
    }

    public LinkedList<Point> getBody() { return body; }
    public Direction getDirection() { return direction; }
}

// Prosta Fabryka
class GameFactory {
    public static Snake createSnake() {
        return new Snake(3);
    }
}