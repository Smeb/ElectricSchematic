package components.infrastructure;

import datastructures.ComponentGroup;
import datastructures.CoordinatePair;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tools.Wire;

public class Anchor extends Circle {
    private static final double ANCHOR_SIZE = 5.0;
    private Direction direction = Direction.unset;
    public enum Direction {send, recv, unset}
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
        ComponentGroup group = (ComponentGroup)this.getParent();
        this.wire = wire;
        group.addWire(wire);
        this.direction = direction;
    }

    public void updateWire(){
        if(wire != null){
            wire.update(this);
        }
    }

    public Direction getDirection(){return direction;}
}
