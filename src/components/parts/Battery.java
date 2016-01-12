package components.parts;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Battery extends Component {
    public static final double width = 110.0;
    public static final double height = 30.0;
    public static final Color iconColor = Color.GREEN;

    protected Battery(Group group) {
        super(group);
    }
}
