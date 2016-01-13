package components.infrastructure;

import controllers.WireController;
import datastructures.CoordinatePair;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tools.Wire;

public class Anchor extends Circle {
    private static final double ANCHOR_SIZE = 5.0;
    private Direction direction = Direction.unset;
    private Wire wire;
    public Anchor(double posX, double posY){
        super(ANCHOR_SIZE);
        this.setCenterX(posX);
        this.setCenterY(posY);
        this.setFill(Color.BLACK);
        this.setStrokeWidth(3.0);
        this.setStroke(Color.TRANSPARENT);
    }

    public CoordinatePair getPosition(){
        double anchorX, anchorY;
        anchorX = this.getParent().getLayoutX() + this.getCenterX();
        anchorY = this.getParent().getLayoutY() + this.getCenterY();
        return new CoordinatePair(anchorX, anchorY);
    }

    public void addWire(Wire wire, Direction direction){
        this.wire = wire;
        this.direction = direction;
    }

    public void updateWire(){
        if(wire != null){
            wire.update(this);
        }
    }
    public Wire getWire() { return wire; }

    public void clearWire() {
        if (wire != null) {
            Wire toBeDeleted = wire;
            WireController.getInstance().eraseWire(wire);
            wire = null;
            if (toBeDeleted.getOtherEnd(this).getWire() != null) {
                toBeDeleted.getOtherEnd(this).clearWire();
            }
        }
    }

    public Direction getDirection(){return direction;}

    public enum Direction {send, recv, unset}
}
