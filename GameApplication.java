import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application implements ICanvas {

    private GameManager manager;

    public static final String filePath = "data.txt";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    private Canvas canvas;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("2048");
        canvas = new Canvas(WIDTH, HEIGHT);
        Group group = new Group(canvas);
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
        manager = new Game2048Manager(this, filePath);

        Thread t = new Thread(manager);
        t.start();

        scene.setOnKeyPressed(keyEvent -> manager.keyHolder.setKeyDown(keyEvent.getCode()));
        scene.setOnKeyReleased(keyEvent -> manager.keyHolder.setKeyUp(keyEvent.getCode()));
    }

    @Override
    public void stop() throws Exception {
        manager.terminate();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }
}