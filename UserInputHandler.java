package kr.ac.snu.sbkim28.game.core;

import javafx.scene.input.KeyCode;

public class UserInputHandler implements InputHolder{

    private boolean[] pressedMap;
    private boolean[] upMap;
    private boolean[] downMap;

    public UserInputHandler() {
        pressedMap = new boolean[591];
        downMap = new boolean[591];
        upMap = new boolean[591];
    }

    @Override
    public boolean keyPressed(KeyCode code) {
        return pressedMap[
                getHash(code.getCode())
                ];
    }

    @Override
    public boolean keyDown(KeyCode code) {
        return downMap[
                getHash(code.getCode())
                ];
    }

    @Override
    public boolean keyUp(KeyCode code) {
        return upMap[
                getHash(code.getCode())
                ];
    }

    @Override
    public void setKeyDown(KeyCode code) {
        pressedMap[getHash(code.getCode())] = true;
        downMap[getHash(code.getCode())] = true;
    }

    @Override
    public void setKeyUp(KeyCode code) {
        pressedMap[getHash(code.getCode())] = false;
        upMap[getHash(code.getCode())] = true;
    }

    @Override
    public void flash() {
        for (int i = 0; i < 591; ++i){
            upMap[i] = false;
            downMap[i] = false;
        }
    }

    private static int getHash(int keycode){
        return keycode % 591 + 1;
    }
}
