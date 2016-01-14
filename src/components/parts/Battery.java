package components.parts;

import datastructures.ComponentValueMap;
import datastructures.DefaultComponentValues;

public class Battery extends Component {
    private static final DefaultComponentValues componentDefaults = ComponentValueMap.getInstance().get(Battery.class);

    public Battery(){

    }

    protected Battery(boolean composite) {
        super(composite);
        this.voltage = 9.0;
        this.resistance = 0.001;
    }

    protected Battery(double voltage, double resistance, boolean composite){
        super(composite);
        this.voltage = voltage;
        this.resistance = resistance;
    }

    public void setVoltage(double voltage){
        this.voltage = voltage;
    }
}
