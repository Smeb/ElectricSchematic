package palette;

import components.parts.ComponentFactory;
import javafx.scene.shape.Rectangle;
import application.ComponentColorMap;

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
