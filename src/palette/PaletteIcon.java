package palette;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import components.Component;

/**
 * Created by minttu on 11/01/2016.
 */
public class PaletteIcon extends Rectangle {
    private Component tool;
    public PaletteIcon(Component c, int size) {
        this.tool = c;
        this.setFill(c.getColor());
        this.setWidth(size);
        this.setHeight(size);
    }
}
