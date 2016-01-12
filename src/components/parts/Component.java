package components.parts;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public abstract class Component {
    public static final Color OUTLINE = Color.BLACK;
    public static final double OFFSET = 30.0;
    public Color iconColor;
    private Group componentGroup;
    private Component[] componentsIn;
    private Component[] componentsOut;
    protected Component(Group group){
        componentGroup = group;
    }

    public Group getGroup(){
        return componentGroup;
    }

}
