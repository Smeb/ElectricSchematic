package palette;

import application.ComponentColorMap;
import components.parts.ComponentFactory;
import javafx.scene.shape.Rectangle;

public class PaletteIcon extends Rectangle {
    public static final double size = 30.0;
    private Class componentType;

    public PaletteIcon(Class componentType) {
        this.componentType = componentType;
        this.setFill(ComponentColorMap.getInstance().getColor(componentType));
        this.setWidth(size);
        this.setHeight(size);
        this.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(componentType, this.getLayoutX()+size/2, this.getLayoutY()+size/2, false));
    }
}
