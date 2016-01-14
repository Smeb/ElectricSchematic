package components.controls;

import components.parts.Component;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

/**
 * Created by minttu on 14/01/2016.
 */
public class EditMenuItem extends CustomMenuItem {
    private Label desc;
    private Label value;
    private Slider slider;

    public EditMenuItem(Component clickedComponent, String desc, double min, double max, double value) {
        this.desc = new Label(desc);
        this.desc.setTextFill(Color.BLACK);
        slider = new Slider(min,max,value);
        this.value = new Label(Double.toString(value));
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit((max-min)/5);
        slider.setBlockIncrement((max-min)/5);
        this.setContent(new EditMenuItemContent(this.desc,this.value,slider));

        slider.valueProperty().addListener(event -> {
            System.out.println(slider.getValue());
            clickedComponent.setVariable(slider.getValue());
            this.value.setText(Integer.toString((int)slider.getValue()));
        });

    }
    public Slider getSlider() { return slider; }
    public Label getDesc() { return desc; }
}
