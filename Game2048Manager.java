import javafx.scene.input.KeyCode;

public class Game2048Manager extends GameManager{

    private Cell cell;
    private StartEnd startEnd;
    private boolean finished;

    public Game2048Manager(ICanvas canvas, Cell cell) {
        super(canvas);
        this.cell = cell;
        startEnd = new StartEnd(cell);
        finished = false;
    }

    @Override
    public void initialize() {
        cell.fillGrid();
        startEnd.startGame();
        Main.drawConsole(cell);
    }

    @Override
    public void update() {
        if(finished)
            return;

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
            cell.update(dir);
            boolean gameEnd = startEnd.checkGameEnd();
            if(gameEnd){
                finished = true;
            } else {
                cell.createNewTile();
            }
            Main.drawConsole(cell);
        }
    }
}

