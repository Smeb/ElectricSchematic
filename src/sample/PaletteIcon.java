package sample;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by minttu on 11/01/2016.
 */
public class PaletteIcon extends Rectangle {
    private Component tool;
    public PaletteIcon(Component c, int size) {
        this.tool = c;
        this.setFill(c.getColor());
        //this.setText(c.getName());
        this.setWidth(size);
        this.setHeight(size);
    }
}
