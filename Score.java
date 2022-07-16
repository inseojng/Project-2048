import java.io.*;

public class Score implements Serializable {
    private int score;
    private int highestScore;

    public Score() {
        this.score = 0;
        this.highestScore = 0;
    }

    public Score(int _score, int _highestScore) {
        this.score = _score;
        this.highestScore = _highestScore;
    }

    public int getScore() {
        return this.score;
    }

    public int getHighestScore() {
        return this.highestScore;
    }

    public void addScore(int val) {
        this.score += val;
    }

    public int setScore(int _score) {
        this.score = _score;
        return this.score;
    }

    public int setHighestScore(int _highestScore) {
        this.highestScore = _highestScore;
        return this.highestScore;
    }

    public void updateHighestScore() {
        if (score > highestScore) {
            highestScore = this.score;
        }
    }

    public static void saveScore(Score score, File file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("" + score.highestScore);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            //todo exception handling.
        }
    }

    public static Score loadScore(File file) throws IOException {
        BufferedReader br = null;
        Score s = new Score();
        br = new BufferedReader(new FileReader(file));
        s.highestScore = Integer.parseInt(br.readLine());
        return s;
    }
}
