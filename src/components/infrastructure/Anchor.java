package components.infrastructure;

import datastructures.ComponentGroup;
import datastructures.CoordinatePair;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tools.Wire;

public class Anchor extends Circle {
    private static final double ANCHOR_SIZE = 5.0;
    private Direction direction = Direction.unset;
    public int id;
    public enum Direction {parent, end, unset}
    private Wire wire;

    public Anchor(int id, double posX, double posY){
        super(ANCHOR_SIZE);
        this.id = id;
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

    public Direction getDirection(){return direction;}
}
