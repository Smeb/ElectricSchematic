package components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Component extends Rectangle {
    private static final double DEFAULT_XY = 90.0;
    public Component(double posX, double posY){
        super(posX, posY, DEFAULT_XY, DEFAULT_XY);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
    }

    public double getDefaultXY(){
        return DEFAULT_XY;
    }



}
