package components;

import javafx.scene.Group;

public class Component {
    private Group componentGroup;

    protected Component(Group group){
        componentGroup = group;
    }

    public Group getGroup(){
        return componentGroup;
    }

}
