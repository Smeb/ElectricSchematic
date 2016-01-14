package palette;

import components.infrastructure.ParallelComponent;
import components.parts.*;
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
        if (componentType instanceof ParallelComponent) {
            this.setOnMouseClicked(event -> {
                ParallelComponentViewFactory.getInstance().newParallelComponent(Lamp.class, Lamp.class, Battery.class, Battery.class);
            System.out.println("Created new parallel component");
            });
        } else {
            this.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(componentType.getClass(), this.getLayoutX() + size / 2, this.getLayoutY() + size / 2, false));
        }
    }

    public Component getComponentType() {
        return componentType;
    }
}
