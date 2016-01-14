package components.parts;

import datastructures.ComponentValueMap;
import datastructures.DefaultComponentValues;

public class Lamp extends Component {
    private static final DefaultComponentValues componentDefaults = ComponentValueMap.getInstance().get(Lamp.class);

    public Lamp(){
    }

    protected Lamp(boolean composite) {
        super(composite);
        this.voltage = 0.0;
        this.resistance = 1.1;
    }

    protected Lamp(double voltage, double resistance, boolean composite){
        super(composite);
        this.voltage = voltage;
        this.resistance = resistance;
    }
}
