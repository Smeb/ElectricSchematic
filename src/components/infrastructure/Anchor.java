package components.infrastructure;

import components.parts.Component;
import controllers.WireController;
import datastructures.CoordinatePair;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tools.Wire;

public class Anchor extends Circle {
    private static final double ANCHOR_SIZE = 5.0;
    private static int id = 0;
    private Direction direction = Direction.unset;
    private Wire wire;
    private Component parentComponent;
    private int thisId;

    protected Anchor(Component component, double posX, double posY){
        super(ANCHOR_SIZE);
        thisId = id++;
        parentComponent = component;
        this.setCenterX(posX);
        this.setCenterY(posY);
        this.setFill(Color.BLACK);
        this.setStrokeWidth(3.0);
        this.setStroke(Color.TRANSPARENT);
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

    public void addWire(Wire wire, Direction direction){
        this.wire = wire;
        this.direction = direction;
    }

    public void removeWire(){
        System.out.println("Removing wire...");
        this.wire = null;
        this.direction = Anchor.Direction.unset;
    }

    public void updateWire(){
        if(wire != null){
            wire.update(this);
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

    public enum Direction {start, end, unset}
}
