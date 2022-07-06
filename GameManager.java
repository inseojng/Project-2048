package kr.ac.snu.sbkim28.game.core;

import javafx.scene.canvas.GraphicsContext;
import kr.ac.snu.sbkim28.game.render.ICanvas;
import kr.ac.snu.sbkim28.game.render.RenderCompositeCollection;

/**
 * @author sbkim28
 * <p>GameManager 클래스를 사용하기 위해서
 * 이를 상속받고 update method를 implement하면 된다.
 * update method는 durationTime이 경과할 때마다 호출된다.
 * </p>
 */
public abstract class GameManager implements Runnable{

    /**
     * it means whether game is running.
     * true when game is running, false when game is not started or finished. <br>
     * 게임이 실행 중인지를 나타내는 변수. 게임이 실행 중이면 true, 게임이 시작되지 않았거나 종료되었으면 false.
     */
    private boolean run;
    /**
     * variable used to calculate the elapsedTime <br>
     * 경과한 시간을 계산하기 위해 사용되는 변수
     */
    private long prevFrame;
    /**
     * duration time.
     * Milliseconds.
     * <br>
     * 대기 시간.
     * 밀리초.
     */
    private long durationTime;

    /**
     * elapsed time between two frames.
     * Milliseconds. <br>
     * 프레임 사이 경과한 시간. 밀리초.
     */
    private long elapsedTime;

    /**
     * how many frames have been passed. <br>
     * 지금까지의 총 프레임 수
     */
    private long tick;

    /**
     * instance that holds the input of users.
     * we can use the one in GameManager. <br>
     * 유저의 입력을 담는 객체.
     * 유저의 입력을 사용하고 싶을 때, GameManager 객체의 것을 사용할 수 있다.
     */
    public InputHolder keyHolder;

    /**
     * canvas instance of this game. <br>
     * 이 게임의 canvas 객체.
     */
    private ICanvas canvas;

    /**
     * Collection of all objects to be rendered.
     * <br>
     * 렌더링되어야 하는 모든 객체의 Collection.
     */
    protected RenderCompositeCollection renderBody;

    /**
     * Set {@link GameManager#durationTime} to default 40.
     * <br>
     * {@link GameManager#durationTime}을 기본 40으로 설정한다.
     * @param canvas canvas of this game
     */
    public GameManager(ICanvas canvas) {
        this.canvas = canvas;
        run = false;
        durationTime = 40;
        renderBody = new RenderCompositeCollection();
    }

    /**
     * Execute the game.
     * After start, it repeatedly calls {@link GameManager#update()}
     * and {@link RenderCompositeCollection#render(GraphicsContext)} of {@link GameManager#renderBody}.
     * The interval of updating and rendering is determined by {@link GameManager#durationTime}.
     * If {@link GameManager#terminate()} is called, the game halts.
     * <br>
     * 게임을 실행한다.
     * 시작 후, {@link GameManager#update()}와 {@link GameManager#renderBody}의
     * {@link RenderCompositeCollection#render(GraphicsContext)}를 반복해서 호출한다. 이때 반복해서 호출하기까지의
     * 시간 간격은 {@link GameManager#durationTime}에 의해서 결정된다.
     * {@link GameManager#terminate()}가 호출되면 게임을 종료한다.
     */
    @Override
    public final void run() {
        run = true;
        prevFrame = Integer.MIN_VALUE;

        long time;
        while (run){
            time = System.currentTimeMillis();
            elapsedTime = time - prevFrame;
            if(elapsedTime >= durationTime){
                prevFrame = time;
                ++tick;
                update();
                renderBody.render(canvas.getGraphicsContext());
                keyHolder.flash();
            }
        }
    }

    /**
     * Method called every single frame of the game. <br>
     * 게임의 모든 프레임마다 호출되는 method.
     */
    public abstract void update();

    /**
     * Getter of {@link GameManager#durationTime}
     * @return {@link GameManager#durationTime}
     */
    public long getDurationTime() {
        return durationTime;
    }

    /**
     * Getter of {@link GameManager#elapsedTime}
     * @return {@link GameManager#elapsedTime}
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Getter of {@link GameManager#tick}
     * @return {@link GameManager#tick}
     */
    public long getTick() {
        return tick;
    }

    /**
     * Terminate the running game. <br>
     * 실행 중인 게임을 종료한다.
     */
    public void terminate(){
        run = false;
    }
}
