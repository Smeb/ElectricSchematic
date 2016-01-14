package components.controls;

import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;

/**
 * Created by minttu on 14/01/2016.
 */
public class EditMenuItem extends CustomMenuItem {
    private Label desc;
    private Slider slider;
    public EditMenuItem(String desc, double min, double max, double value) {
        this.desc = new Label(desc);
        this.desc.setTextFill(Color.BLACK);
        this.slider = new Slider(min,max,value);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit((max-min)/10);
        slider.setBlockIncrement((max-min)/10);
        this.setContent(new EditMenuItemContent(this.desc,slider));

    }
    public Slider getSlider() { return slider; }
    public Label getDesc() { return desc; }
}
