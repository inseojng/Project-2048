import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game2048Manager extends GameManager{
    private Cell cell;
    private Score score;

    private String filePath;
    private File scoreFile;
    private boolean finished;

    public Game2048Manager(ICanvas canvas, String filePath) {
        super(canvas);
        finished = false;
        this.filePath = filePath;
        scoreFile = new File(filePath);
    }

    @Override
    public void initialize() {
        score = new Score();
        try {
            score = Score.loadScore(scoreFile);
        } catch (FileNotFoundException e){
            System.out.println("File not found. new");
        } catch (IOException e) {
            e.printStackTrace();
        }
        cell = new Cell(score, 4, 4);
        startGame();
        renderBody.add(new GameRenderer(score, GameApplication.WIDTH, GameApplication.HEIGHT));
        renderBody.add(cell);
    }

    private void startGame(){
        finished = false;
        score.setScore(0);
        cell.fillGrid();
        cell.createNewTile();
        cell.createNewTile();

    }

    private boolean isGameEnded(){
        boolean ret = false;
        if (cell.gameOver()) {
            System.out.println("WON");
            ret = true;
        } else if (!cell.canTilesMove()) {
            System.out.println("LOST");
            ret = true;
        }
        return ret;
    }
    @Override
    public void update() {
        if(finished) {
            if(keyHolder.keyDown(KeyCode.SPACE)) {
                startGame();
            }
            return;
        }

        int dir = -1;

        // set dir.
        if(keyHolder.keyDown(KeyCode.LEFT)){
            dir = 0;
        } else if(keyHolder.keyDown(KeyCode.RIGHT)){
            dir = 1;
        } else if(keyHolder.keyDown(KeyCode.DOWN)){
            dir = 3; // reversed.
        } else if(keyHolder.keyDown(KeyCode.UP)){
            dir = 2; // reversed.
        }

        if(dir != -1) {
            boolean moved = cell.update(dir);
            if(!moved)
                return;

            cell.createNewTile();

            //Main.drawConsole(cell);
        }
        boolean gameEnd = isGameEnded();
        if(gameEnd){
            finished = true;
            score.updateHighestScore();
        }
    }

    @Override
    public void terminate() {
        super.terminate();
        Score.saveScore(score, scoreFile);
    }
}

