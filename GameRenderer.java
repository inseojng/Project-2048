import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameRenderer implements RenderComposite{
    private int width, height;
    private Score score;

    private static final Color background = Color.rgb(251, 248, 239);
    private static final Color platePadding = Color.rgb(181, 161, 126);
    private static final Color cellBackground = Color.rgb(192, 176, 161);
    private static final Color logo2048 = Color.rgb(236, 204, 95);
    public static final Color scoreBackground = Color.rgb(61, 58, 51);
    public static final Color textColor = Color.rgb(252, 244, 242);

    public static final Font logoFont = new Font("HY견고딕", 50);
    public static final Font scoreFont = new Font("HY견고딕", 24);

    public static final int startX = 70;
    public static final int startY = 270;
    public static final int blockSize = 100;
    public static final int blockDiff = 120;

    public GameRenderer(Score score, int width, int height){
        this.width = width;
        this.height = height;
        this.score = score;
    }
    @Override
    public void render(GraphicsContext gc) {

        gc.setFill(background);
        gc.fillRect(0,0,width,height);

        gc.setFill(platePadding);
        gc.fillRoundRect(50, 250, 500, 500, 20, 20); //gameBoard
        gc.setFill(logo2048);
        gc.fillRoundRect(50, 50, 150, 150, 20, 20); //2048logo

        gc.setFill(textColor);
        gc.setFont(logoFont);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("2048", 125, 125);

        gc.setFill(scoreBackground);
        gc.fillRoundRect(225, 50, 150, 150, 20, 20); //score
        gc.fillRoundRect(400, 50, 150, 150, 20, 20); //highest

        gc.setFill(textColor);
        gc.setFont(scoreFont);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("SCORE", 300, 75);
        gc.fillText("BEST", 475, 75);

        gc.fillText("" + score.getScore(), 300, 125);
        gc.fillText("" + score.getHighestScore(), 475, 125);


        gc.setFill(cellBackground);
        int x = startX; //가로
        int y;
        for(int c = 0; c < 4; c++) { //block
            y = startY;
            for(int r = 0; r < 4; r++) {
                gc.fillRoundRect(x, y, blockSize, blockSize, 20, 20);
                y += blockDiff;
            }
            x += blockDiff;
        }
    }


}
