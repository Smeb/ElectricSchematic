package components.parts;

import application.Globals;
import components.infrastructure.ComponentGroup;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public abstract class Component {
    public static final Color OUTLINE = Color.BLACK;
    public static final double OFFSET = 30.0;

    private static int id = 0;
    public final int thisId;

    protected double voltage = 0.0;
    protected double resistance;
    protected double current = 0.0;

    protected String name;
    protected Rectangle icon;

    private ComponentGroup componentGroup;
    private LinkedList<Component> connectedComponents;

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

    public double getResistance(){return resistance;}
    public double getVoltage(){return voltage;}
    public double getCurrent(){return current;}
    public void setCurrent(double current){this.current = current;}


    @Override
    public String toString(){
        return name + ": " + thisId;
    }

    public void setIcon(Rectangle icon) { this.icon = icon; }

    private void fillWithSchematic() {
        if (Lamp.class.isAssignableFrom(this.getClass())) {
            icon.setFill(Lamp.schematic);

        }
        if (Battery.class.isAssignableFrom(this.getClass())) {
            icon.setFill(Battery.schematic);
        }
        icon.setStroke(Color.TRANSPARENT);
    }
    private void fillWithColor() {
        if (Lamp.class.isAssignableFrom(this.getClass())) {
            icon.setFill(Lamp.iconColor);
        }
        if (Battery.class.isAssignableFrom(this.getClass())) {
            icon.setFill(Battery.iconColor);
        }
        icon.setStroke(Color.BLACK);
    }
    public void fill() {
        if (Globals.schematicIcons) {
            fillWithSchematic();
        }
        else {
            fillWithColor();
        }
    }
}
