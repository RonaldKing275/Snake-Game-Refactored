import javax.swing.*;

public class StartMenu extends JFrame {
    public StartMenu() {
        initUI();
    }

    private void initUI() {
        setTitle("Snake - Refactored");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 120);
        setLocationRelativeTo(null); // Okienko centralnie na środku ekranu

        JPanel panel = new JPanel();
        JTextField nameField = new JTextField(10);
        JButton startBtn = new JButton("Start");
        JButton scoresBtn = new JButton("Scores");

        startBtn.addActionListener(_ -> {
            String name = nameField.getText();
            if (!name.isBlank()) {
                dispose(); // zamknij okienko menu
                JFrame gameFrame = new JFrame("Snake");
                gameFrame.add(new Board(name));
                gameFrame.pack(); // dopasowanie rozmiaru
                gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
            }
        });

        scoresBtn.addActionListener(_ -> {
            // Użycie Fasady
            var scores = new ScoreFacade().getAllScores();
            JOptionPane.showMessageDialog(this, String.join("\n", scores));
        });

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(startBtn);
        panel.add(scoresBtn);
        add(panel);
    }

    static void main() {
        SwingUtilities.invokeLater(() -> new StartMenu().setVisible(true));
    }
}