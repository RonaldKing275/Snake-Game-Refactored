import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private GameState state; // Aktualny STAN
    private final String userName;
    public final int width = 300;
    public final int height = 300; // Rozmiar mapy

    public Board(String userName) {
        this.userName = userName;
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        addKeyListener(new InputAdapter());

        // Stan początkowy
        this.state = new PlayingState(this);

        // Pętla gry (Obserwator czasu)
        Timer timer = new Timer(140, this);
        timer.start();
    }

    // Metoda zmiany stanu (używana przez stany)
    public void setState(GameState newState) {
        this.state = newState;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        state.render(g); // Delegacja do aktualnego stanu
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        state.update(); // Delegacja logiki
        repaint();
    }

    private class InputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            state.handleInput(e.getKeyCode()); // Delegacja sterowania
        }
    }
}