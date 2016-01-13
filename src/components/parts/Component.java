package components.parts;

import components.infrastructure.ComponentGroup;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public abstract class Component {
    public static final Color OUTLINE = Color.BLACK;
    public static final double OFFSET = 30.0;
    protected static int id = 0;
    private final int thisId;
    protected String name;
    private ComponentGroup componentGroup;
    private LinkedList<Component> connectedComponents;
    protected Component(ComponentGroup group){
        thisId = id++;
        componentGroup = group;
        connectedComponents = new LinkedList<>();
        group.setParentComponent(this);
    }

    public Group getGroup(){
        return componentGroup;
    }

    public void addConnectedComponent(Component component){
        connectedComponents.add(component);
    }

    public LinkedList<Component> getConnectedComponents(){return connectedComponents;}

    @Override
    public String toString(){
        return name + ": " + thisId;
    }

}
