package kr.ac.snu.sbkim28.game.core;

import javafx.scene.input.KeyCode;

/**
 * 유저의 입력을 처리하는 클래스.
 * 모든 method는 {@link GameManager#update()}
 * 내에서 호출되어야 한다.
 */
public interface InputHolder {
    /**
     * 키를 누르고 있는지 여부를 반환.
     * 키를 누르고 있는 동안은 참.
     * @param code KeyCode
     */
    boolean keyPressed(KeyCode code);

    /**
     * 키를 눌렀는지 여부를 반환.
     * 키를 누른 시점의 프레임에서만 참.
     * 누르고 있더라도 그 다음 프레임에서는 거짓.
     * @param code KeyCode
     */
    boolean keyDown(KeyCode code);

    /**
     * 키를 땠는지 여부를 반환.
     * 키를 땐 시점의 프레임에서만 참.
     * 때고 있더라도 그 다음 프레임에서는 거짓.
     * @param code
     */
    boolean keyUp(KeyCode code);

    void setKeyDown(KeyCode code);
    void setKeyUp(KeyCode code);
    void flash();
}
