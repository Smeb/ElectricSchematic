package palette;

import application.ComponentColorMap;
import application.Globals;
import components.parts.Component;
import components.parts.ComponentFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PaletteIcon extends Rectangle {
    public static final double size = 30.0;
    private Component componentType;

    public PaletteIcon(Component componentType) {
        this.componentType = componentType;
        if (Globals.schematicIcons) {
            this.setFill(new ImagePattern(new Image("file:img/lamp-icon.png")));
        } else {
            this.setFill(ComponentColorMap.getInstance().getColor(componentType.getClass()));
        }
        this.setWidth(size);
        this.setHeight(size);
        this.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(componentType.getClass(), this.getLayoutX()+size/2, this.getLayoutY()+size/2));
    }
    public Component getComponentType() {
        return componentType;
    }
}
