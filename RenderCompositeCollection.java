import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class RenderCompositeCollection extends LinkedList<RenderComposite> implements RenderComposite{
    @Override
    public void render(GraphicsContext gc) {
        for (RenderComposite r : this)
            r.render(gc);
    }
}
