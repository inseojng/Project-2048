import javafx.scene.input.KeyCode;

public class Game2048Manager extends GameManager{

    public Game2048Manager(ICanvas canvas) {
        super(canvas);
    }

    @Override
    public void update() {
        int dir = -1;

        // set dir.
        if(keyHolder.keyDown(KeyCode.LEFT)){
            dir = 0;
        } else if(keyHolder.keyDown(KeyCode.RIGHT)){
            dir = 1;
        } else if(keyHolder.keyDown(KeyCode.DOWN)){
            dir = 2;
        } else if(keyHolder.keyDown(KeyCode.UP)){
            dir = 3;
        }

        if(dir != -1) {
            // cell.update(dir);
        }
    }
}
