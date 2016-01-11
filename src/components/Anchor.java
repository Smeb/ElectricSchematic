package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Anchor extends Circle {
    private static final double ANCHOR_SIZE = 5.0;
    Orientation orientation;

    public Anchor(double posX, double posY){
        super(ANCHOR_SIZE);
        this.setCenterX(posX);
        this.setCenterY(posY);
        this.setFill(Color.BLACK);
        this.setStrokeWidth(3.0);
        this.setStroke(Color.TRANSPARENT);
    }
}
