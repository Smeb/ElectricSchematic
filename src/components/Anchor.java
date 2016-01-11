package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Anchor extends Circle {
    enum orientation {LEFT, RIGHT}
    orientation orient;

    public Anchor(Component parent, int i){
        super(3.0);
        this.setCenterY(parent.getY() + parent.getDefaultXY() / 2);
        if(i == 0) {
            orient = orientation.RIGHT;
            this.setCenterX(parent.getX() + parent.getDefaultXY());
        } else {
            orient = orientation.LEFT;
            this.setCenterX(parent.getX());
        }
        this.setFill(Color.BLACK);
        this.setStroke(Color.BLACK);
    }

    public void update(Component parent){
        this.setCenterY(parent.getY() + parent.getDefaultXY() / 2);
        if(orient == orientation.LEFT){
            this.setCenterX(parent.getX() + parent.getDefaultXY());
        }
        if(orient == orientation.RIGHT){
            this.setCenterX(parent.getX());
        }
    }
}
