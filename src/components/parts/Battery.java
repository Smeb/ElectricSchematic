package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Battery extends Component {
    public static final double width = 80.0;
    public static final double height = 50.0;
    public static final ImagePattern iconColor = new ImagePattern(new Image("file:img/battery-colour.png"));
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/battery-icon.png"));
    public static final String name = "Battery";

    public Battery() {
        this.voltage = 9.0;
        this.resistance = 0.001;
    }

    protected Battery(double voltage, double resistance){
        this.voltage = voltage;
        this.resistance = resistance;
    }

    public void setVoltage(double voltage){
        this.voltage = voltage;
    }
}
