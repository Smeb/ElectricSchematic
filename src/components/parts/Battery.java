package components.parts;

import components.infrastructure.ComponentGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Battery extends Component {
    public static final double width = 80.0;
    public static final double height = 50.0;
    public static final Color iconColor = Color.GREEN;
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/battery-icon.png"));


    protected Battery(ComponentGroup group) {
        super(group);
        this.name = "Battery";
    }
}
