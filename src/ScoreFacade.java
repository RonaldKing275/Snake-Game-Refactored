import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreFacade {
    private static final String FILE_NAME = "scores.txt";

    public void saveScore(String user, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user + ": " + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllScores() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            // Ignoruj brak pliku
        }
        return list;
    }
}