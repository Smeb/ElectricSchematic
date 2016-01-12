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
    private ComponentGroup componentGroup;
    private LinkedList<Component> componentsNext;
    private LinkedList<Component> componentsPrev;
    protected Component(ComponentGroup group){
        thisId = id++;
        componentGroup = group;
        group.setParentComponent(this);
    }

    public Group getGroup(){
        return componentGroup;
    }

    public void addNextComponent(Component component){
        if(componentsNext == null){
            componentsNext = new LinkedList<>();
        }
        componentsNext.add(component);
    }

    public void addPrevComponent(Component component){
        if(componentsPrev == null){
            componentsPrev = new LinkedList<>();
        }
        componentsPrev.add(component);
    }

}
