package components;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Component {
    private Group componentGroup;
    public Color iconColor;

    protected Component(Group group){
        componentGroup = group;
    }

    public Group getGroup(){
        return componentGroup;
    }

}
