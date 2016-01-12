package palette;

import components.ComponentFactory;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import components.Component;
import sample.ComponentColorMap;

/**
 * Created by minttu on 11/01/2016.
 */
public class PaletteIcon extends Rectangle {
    private Class componentType;

    public PaletteIcon(Class componentType, int size) {
        this.componentType = componentType;
        this.setFill(ComponentColorMap.getInstance().getColor(componentType));
        this.setWidth(size);
        this.setHeight(size);
        this.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(componentType, this.getLayoutX()+size/2, this.getLayoutY()+size/2));
    }
}
