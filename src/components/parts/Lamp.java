package components.parts;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Lamp extends Component {
    public static final double width = 110.0;
    public static final double height = 110.0;
    public static final Color iconColor = Color.YELLOW;

    protected Lamp(Group group) {
        super(group);
    }

}
