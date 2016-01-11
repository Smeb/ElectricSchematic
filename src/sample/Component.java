package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by minttu on 11/01/2016.
 */
public class Component extends Rectangle {
    private Color iconColor;
    private String name;
    public Component(String name, Color iconColor) {
        super(15,25,10,10);
        this.name = name;
        this.iconColor = iconColor;
        //this.setFill(iconColor);


    }
    public String getName() {
        return this.name;
    }
    public Color getColor() { return this.iconColor; }
}
