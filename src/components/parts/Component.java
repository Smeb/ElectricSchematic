package components.parts;

import application.Globals;
import components.infrastructure.ComponentView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public abstract class Component {
    public static final double width = 0.0;
    public static final double height = 0.0;

    public static final Color OUTLINE = Color.BLACK;
    public static final double OFFSET = 30.0;

    private static int id = 0;
    public final int thisId;
    public final boolean composite;
    protected double voltage = 0.0;
    protected double resistance;
    protected double current = 0.0;

    protected String name;
    protected Rectangle icon;
    private ComponentView componentView;
    private LinkedList<Component> connectedComponents;

    public Component(){
        thisId = -1;
        composite = true;
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
        return name + ": " + thisId;
    }

    public void setIcon(Rectangle icon) { this.icon = icon; }

    private ImagePattern getSchematic() {
        if (Lamp.class.isAssignableFrom(this.getClass())) {
            return Lamp.schematic;
        }
        if (Battery.class.isAssignableFrom(this.getClass())) {
            return Battery.schematic;
        }
        return null;
    }
    private Paint getColor() {
        if (Lamp.class.isAssignableFrom(this.getClass())) {
            return Lamp.iconColor;
        }
        if (Battery.class.isAssignableFrom(this.getClass())) {
            return Battery.iconColor;
        }
        return null;
    }
    public void fill() {
        if (Globals.schematicIcons) {
            icon.setFill(getSchematic());
        }
        else {
            icon.setFill(getColor());
        }
    }
}
