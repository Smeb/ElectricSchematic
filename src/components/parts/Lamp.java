package components.parts;

import components.infrastructure.ComponentGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Lamp extends Component {
    public static final double width = 80.0;
    public static final double height = 80.0;
    public static final Color iconColor = Color.YELLOW;
    protected Lamp() {
        this.name = "Lamp";
    }
}
