package palette;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Palette extends FlowPane {
    private ArrayList<PaletteIcon> contents;
    private int iconSize;

    public Palette(double x, double y, double height, int iconsPerRow, int iconSize, ArrayList<Class> tools) {
        int hgap = 5;
        this.iconSize = iconSize;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setWidth(iconsPerRow*(iconSize+hgap));
        this.setHeight(height);
        this.setPrefWrapLength(100);
        //this.setPrefWrapLength(iconsPerRow*(iconSize+hgap));
        this.setPadding(new Insets(20, 30, 20, 30));
        this.setVgap(8);
        this.setHgap(hgap);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(200,200,200), CornerRadii.EMPTY, Insets.EMPTY)));
        //this.setFill(Color.rgb(190,190,190));

        //this.setStroke(Color.BLACK);
        this.contents = new ArrayList<>();
        for (Class comp: tools) {
            contents.add(new PaletteIcon(comp));
        }
        arrangeTools();

    }

    private void arrangeTools() {
        for(PaletteIcon icon : contents) {
            this.getChildren().add(icon);
        }
    }

}
