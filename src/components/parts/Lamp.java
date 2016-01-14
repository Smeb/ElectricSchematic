package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Lamp extends Component {
    public static final double width = 80.0;
    public static final double height = 80.0;
    public static final ImagePattern iconColor = new ImagePattern(new Image("file:img/lamp-colour.png"));
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/lamp-icon.png"));
    public static final String name = "Lamp";

    public Lamp(){
    }

    protected Lamp(boolean composite) {
        super(composite);
        this.voltage = 0.0;
        this.resistance = 1.1;
    }

    protected Lamp(double voltage, double resistance, boolean composite){
        super(composite);
        this.voltage = voltage;
        this.resistance = resistance;
    }
}
