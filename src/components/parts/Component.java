package components.parts;

import application.Globals;
import components.infrastructure.ComponentView;
import datastructures.ComponentValueMap;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public abstract class Component {

    public static final Color OUTLINE = Color.BLACK;
    public static final double PARALLELOFFSET = 15.0;
    public static final double OFFSET = 30.0;

    private static int id = 0;
    public final int thisId;
    public final boolean composite;
    protected double voltage = 0.0;
    protected double resistance;
    protected double current = 0.0;

    protected String name;
    protected Rectangle icon;
    protected ComponentView componentView;
    private LinkedList<Component> connectedComponents;

    public Component(){
        thisId = -1;
        composite = true;
    }

    public static void resetIDs()
    {
        id =0;
    }

    protected Component(boolean composite){
        thisId = id++;
        connectedComponents = new LinkedList<>();
        this.composite = composite;
    }

    public Group getGroup(){
        return componentView;
    }
    public void setComponentView(final ComponentView group){
        this.componentView = group;
    }
    public void addConnectedComponent(Component component){
        connectedComponents.add(component);
    }
    public LinkedList<Component> getConnectedComponents(){return connectedComponents;}

    public double getResistance(){return resistance;}
    public double getVoltage(){return voltage;}
    public double getCurrent(){return current;}
    public void setCurrent(double current){this.current = current;}


    @Override
    public String toString(){
        return ComponentValueMap.getInstance().get(this.getClass()).getName() + ": " + thisId;
    }

    public void setIcon(Rectangle icon) { this.icon = icon; }

    public void fill() {
        if (Globals.schematicIcons) {
            icon.setFill(ComponentValueMap.getInstance().get(this.getClass()).getSchematic());
        }
        else {
            icon.setFill(ComponentValueMap.getInstance().get(this.getClass()).getIconImage());
        }
    }
}
