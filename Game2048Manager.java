package kr.ac.snu.sbkim28.game.core;

import javafx.scene.input.KeyCode;
import kr.ac.snu.sbkim28.game.render.ICanvas;

public class Game2048Manager extends GameManager{

    public Game2048Manager(ICanvas canvas) {
        super(canvas);
    }

    @Override
    public void update() {
        int dir;

        // set dir.
        if(keyHolder.keyDown(KeyCode.RIGHT)){

        } else if(keyHolder.keyDown(KeyCode.LEFT)){

        } else if(keyHolder.keyDown(KeyCode.UP)){

        } else if(keyHolder.keyDown(KeyCode.DOWN)){

        }

        // call update method here.
    }
}
