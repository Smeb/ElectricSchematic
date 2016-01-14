package palette;

import components.parts.Component;
import components.parts.ComponentFactory;
import javafx.scene.shape.Rectangle;

public class PaletteIcon extends Rectangle {
    public static final double size = 30.0;
    private Component componentType;

    public PaletteIcon(Component componentType) {
        this.componentType = componentType;
        componentType.setIcon(this);
        componentType.fill();
        this.setWidth(size);
        this.setHeight(size);
        this.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(componentType.getClass(), this.getLayoutX()+size/2, this.getLayoutY()+size/2, false));
    }

    public Component getComponentType() {
        return componentType;
    }
}
