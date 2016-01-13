package components.parts;

import components.infrastructure.ComponentGroup;
import javafx.scene.paint.Color;

public class Lamp extends Component {
    public static final double width = 110.0;
    public static final double height = 110.0;
    public static final Color iconColor = Color.YELLOW;
    protected Lamp(ComponentGroup group) {
        super(group);
        this.name = "Lamp";
    }
}
