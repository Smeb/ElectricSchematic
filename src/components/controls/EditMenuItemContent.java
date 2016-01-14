package components.controls;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Created by minttu on 14/01/2016.
 */
public class EditMenuItemContent extends FlowPane {
    public EditMenuItemContent(Label desc, Slider slider) {
        this.setVgap(10);
        this.setPrefWrapLength(150);
        this.getChildren().addAll(desc,slider);
    }
}
