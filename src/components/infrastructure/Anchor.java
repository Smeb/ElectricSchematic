package components.infrastructure;

import components.parts.Component;
import components.wires.ToolWire;
import components.wires.WireController;
import datastructures.CoordinatePair;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import components.wires.Wire;

import java.util.LinkedList;

public class Anchor extends Circle {
    private static final double ANCHOR_SIZE = 5.0;
    private static int id = 0;
    private Direction direction = Direction.unset;
    private Wire wire;
    private LinkedList<ToolWire> toolWires;
    private Component parentComponent;
    private int thisId;

    protected Anchor(Component component, CoordinatePair pair){
        super(ANCHOR_SIZE);
        thisId = id++;
        parentComponent = component;
        this.setCenterX(pair.getX());
        this.setCenterY(pair.getY());
        this.setFill(Color.BLACK);
        this.setStrokeWidth(3.0);
        this.setStroke(Color.TRANSPARENT);
        toolWires = new LinkedList<>();
    }

    public int getAnchorId(){return thisId;}

    public Component getParentComponent(){
        return parentComponent;
    }

    public CoordinatePair getPosition(){
        double anchorX, anchorY;
        anchorX = this.getParent().getLayoutX() + this.getCenterX();
        anchorY = this.getParent().getLayoutY() + this.getCenterY();
        return new CoordinatePair(anchorX, anchorY);
    }

    public Wire getWire() {
        return this.wire;
    }

    public void addWire(Wire wire){
        this.wire = wire;
        this.direction = Direction.set;
    }

    public void removeWire(){
        this.wire = null;
        this.direction = Anchor.Direction.unset;
    }

    public void addToolWire(ToolWire wire){
        toolWires.add(wire);
    }

    public void removeToolWire(ToolWire wire){
        toolWires.remove(wire);
    }

    public void updateWires(){
        if(wire != null){
            wire.update(this);
        }
        for(ToolWire w : toolWires){
            w.update(this);
        }
    }

    public void updateDirection(Direction direction) {
        this.direction = direction;
    }

    public void clearWire() {
        if (wire != null) {
            Wire toBeDeleted = wire;
            WireController.getInstance().eraseWire(wire);
            this.direction = Direction.unset;
            wire = null;
            if (toBeDeleted.getOtherEnd(this).getWire() != null) {
                toBeDeleted.getOtherEnd(this).clearWire();
            }
        }
    }

    public Direction getDirection(){return direction;}

    public enum Direction {set, unset}
}
