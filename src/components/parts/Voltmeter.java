package components.parts;

import javafx.scene.control.*;
import javafx.scene.control.Label;

import java.awt.*;

/**
 * Created by minttu on 14/01/2016.
 */
public class Voltmeter extends HasDisplay {
    String voltString;

    public Voltmeter(){
    }

    protected Voltmeter(boolean composite) {
        super(composite);
        this.voltage = 0.0;
        this.resistance = 1.1;
    }

    protected Voltmeter(double voltage, double resistance, boolean composite){
        super(composite);
        this.voltage = voltage;
        this.resistance = resistance;
    }

    public void setVoltDisplay(double v){
        voltString = String.valueOf(Math.round((v * 100) / 100.0));
        display.setText(voltString + " V");
    }

    public void setDisplayNode(Label label){
        this.display = label;
    }
}
