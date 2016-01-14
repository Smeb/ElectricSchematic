package palette;

import components.parts.Component;
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

    public Palette(double x, double y, int iconSize, int iconsPerRow, ArrayList<Component> tools) {
        int hgap = 5;
        this.iconSize = iconSize;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setWidth(iconsPerRow*(iconSize+hgap));
        this.setPrefWrapLength(100);
        this.setPadding(new Insets(20, 30, 20, 30));
        this.setVgap(8);
        this.setHgap(hgap);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(200,200,200), CornerRadii.EMPTY, Insets.EMPTY)));

        //this.setStroke(Color.BLACK);
        this.contents = new ArrayList<>();
        for (Component comp: tools) {
            contents.add(new PaletteIcon(comp));
        }
        arrangeTools();

    }

    private void arrangeTools() {
        for(PaletteIcon icon : contents) {
            this.getChildren().add(icon);
        }
    }
    public void changeIcons() {
        for (PaletteIcon tool : contents) {
            tool.getComponentType().fill();
        }
    }
}
