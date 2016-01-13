package components.parts;

import components.infrastructure.ComponentGroup;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public abstract class Component {
    public static final Color OUTLINE = Color.BLACK;
    public static final double OFFSET = 30.0;
    protected static int id = 0;
    public final int thisId;
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

    public LinkedList<Component> getNextNodes(){return componentsNext;}
    public LinkedList<Component> getPrevNodes(){return componentsPrev;}
    public void removeNextNode() { componentsNext.pop(); }
    public void removePrevNode() { componentsPrev.pop(); }

}
