package components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Component extends Rectangle {
    Anchor[] anchors;
    private static final double DEFAULT_XY = 90.0;
    private Color iconColor;

    public Component(double posX, double posY, Color iconColor){
        super(posX, posY, DEFAULT_XY, DEFAULT_XY);
        this.setFill(iconColor);
        this.setStroke(Color.BLACK);
        anchors = new Anchor[2];
    }
    public Component(double posX, double posY){
        this(posX,posY, Color.TRANSPARENT);
    }

    public void addAnchor(int i, Anchor anchor){anchors[i] = anchor;}
    public void updateAnchors(){
        for(Anchor a : anchors){
            a.update(this);
        }
    }
    public double getDefaultXY(){
        return DEFAULT_XY;
    }
    public Color getColor() { return iconColor; }



}
