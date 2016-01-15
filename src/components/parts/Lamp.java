package components.parts;

import datastructures.ComponentValueMap;
import datastructures.DefaultComponentValues;

import java.util.LinkedList;

public class Lamp extends Component {
    private static final DefaultComponentValues componentDefaults = ComponentValueMap.getInstance().get(Lamp.class);

    public Lamp(){
        connectedComponents = new LinkedList<>();
        this.voltage = 0.0;
        this.resistance = 1.1;
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
