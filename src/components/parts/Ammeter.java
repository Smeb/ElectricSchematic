package components.parts;

import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

/**
 * Created by minttu on 14/01/2016.
 */
public class Ammeter extends HasDisplay {
    String currentString;

    public Ammeter(){
    }

    protected Ammeter(boolean composite) {
        super(composite);
        this.voltage = 0.0;
        this.resistance = 1.1;
        this.currentString = String.valueOf(0.0);
    }

    @Override
    public void setCurrent(double current){
        this.current = current;
        this.currentString = String.valueOf(Math.round(current * 100) / 100.0);
        display.setText(currentString + " A");
    }

    protected Ammeter(double voltage, double resistance, boolean composite){
        super(composite);
        this.voltage = voltage;
        this.resistance = resistance;
    }

    public void setDisplayNode(Label label){
        this.display = label;
    }
}
