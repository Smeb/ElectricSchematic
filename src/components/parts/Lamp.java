package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Lamp extends Component {
    public static final double width = 80.0;
    public static final double height = 80.0;
    public static final Color iconColor = Color.YELLOW;
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/lamp-icon.png"));


    protected Lamp() {
        this.name = "Lamp";
    }
}
