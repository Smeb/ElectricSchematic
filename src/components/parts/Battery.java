package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Battery extends Component {
    public static final double width = 80.0;
    public static final double height = 50.0;
    public static final Color iconColor = Color.GREEN;
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/battery-icon.png"));

    public Battery() {
        this.name = "Battery";
        this.voltage = 9.0;
    }

    public void setVoltage(double voltage){
        this.voltage = voltage;
    }
    public void changeIcon(boolean schematic) {
        if (schematic) {
            this.icon.setFill(this.schematic);
        } else {
            this.icon.setFill(this.iconColor);
        }
    }
}
