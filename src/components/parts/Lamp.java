package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Lamp extends Component {
    public static final double width = 80.0;
    public static final double height = 80.0;
    public static final ImagePattern iconColor = new ImagePattern(new Image("file:img/lamp-colour.png"));
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/lamp-icon.png"));
    public static final String name = "Lamp";

    public Lamp() {
        voltage = 0.0;
        resistance = 1.0;
    }

    protected Lamp(double voltage, double resistance){
        this.voltage = voltage;
        this.resistance = resistance;
    }
}
