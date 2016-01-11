package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Anchor extends Circle {
    private static final double ANCHOR_SIZE = 5.0;
    enum Orientation {LEFT, RIGHT, FINAL}
    Orientation orientation;
    private final Component parent;

    public Anchor(Component parent, int i){
        super(ANCHOR_SIZE);
        this.parent = parent;
        this.setCenterY(parent.getY() + parent.getDefaultXY() / 2);
        if(i == 0) {
            orientation = Orientation.RIGHT;
            this.setCenterX(parent.getX() + parent.getDefaultXY());
        } else {
            orientation = Orientation.LEFT;
            this.setCenterX(parent.getX());
        }
        this.setFill(Color.BLACK);
        this.setStrokeWidth(3.0);
        this.setStroke(Color.TRANSPARENT);
    }

    public void update(){
        this.setCenterY(parent.getY() + parent.getDefaultXY() / 2);
        if(orientation == Orientation.LEFT){
            this.setCenterX(parent.getX() + parent.getDefaultXY());
        }
        if(orientation == Orientation.RIGHT){
            this.setCenterX(parent.getX());
        }
        this.toFront();
    }
}
