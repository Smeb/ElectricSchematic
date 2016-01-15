package palette;

import application.Globals;
import components.infrastructure.ParallelComponent;
import components.parts.Component;
import components.parts.Lamp;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.Vector;

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
        int i = 0;
        for (PaletteIcon tool : contents) {

            if (tool.getComponentType() instanceof ParallelComponent) {

                //if (((ParallelComponent) tool.getComponentType()).getComponents().size() > 1) {
                    //if (((ParallelComponent) tool.getComponentType()).getComponents().get(0) instanceof Lamp) {
                        if (Globals.schematicIcons) {
                            switch(i) {
                                case 0: tool.setFill(new ImagePattern(new Image("file:img/2x-lamp-icon.png"))); break;
                            }
                            tool.setStroke(Color.TRANSPARENT);
                        } else {

                            switch (i) {
                                case 0: tool.setFill(new ImagePattern(new Image("file:img/2x-lamp-colour.png"))); break;
                            }
                            tool.setStroke(Color.BLACK);
                        }

                    //}
                //}
                i++;
            }
            else {
                tool.getComponentType().fill();
            }
        }
    }
}
