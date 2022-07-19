import javafx.scene.canvas.GraphicsContext;

public interface RenderComposite {
    void render(GraphicsContext gc);
    void render(GraphicsContext gc, long tick);
}
