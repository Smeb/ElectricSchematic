package components.parts;

import components.infrastructure.ComponentGroup;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public abstract class Component {
    public static final Color OUTLINE = Color.BLACK;
    public static final double OFFSET = 30.0;

    private static int id = 0;
    public final int thisId;

    protected double voltage = 0.0;
    protected double resistance;

    protected String name;
    protected Rectangle icon;

    private ComponentGroup componentGroup;
    private LinkedList<Component> connectedComponents;
    protected static Color iconColor;
    protected static ImagePattern schematic;

    protected Component(){
        thisId = id++;
        connectedComponents = new LinkedList<>();
    }


    public Group getGroup(){
        return componentGroup;
    }

    public void setComponentGroup(final ComponentGroup group){
        this.componentGroup = group;
    }

    public void addConnectedComponent(Component component){
        connectedComponents.add(component);
    }

    public LinkedList<Component> getConnectedComponents(){return connectedComponents;}

    @Override
    public String toString(){
        return name + ": " + thisId;
    }

    public void setIcon(Rectangle icon) { this.icon = icon; }
}
