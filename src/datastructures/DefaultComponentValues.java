package datastructures;

import javafx.scene.paint.ImagePattern;

/**
 * Created by ben on 13/01/16.
 */
public class DefaultComponentValues {
    private final ImagePattern iconImage;
    private final ImagePattern schematic;
    private final String name;
    private double height;
    private double width;

    public DefaultComponentValues(double height, double width, ImagePattern iconImage, ImagePattern schematic, String name){
        this.height = height;
        this.width = width;
        this.iconImage = iconImage;
        this.schematic = schematic;
        this.name = name;
    }

    public double getHeight(){return height;}
    public double getWidth(){return width;}
    public ImagePattern getIconImage(){return iconImage;}
    public ImagePattern getSchematic(){return schematic;}
    public String getName(){return name;}
}
