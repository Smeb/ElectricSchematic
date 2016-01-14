package components.parts;

import components.infrastructure.Anchor;
import datastructures.ComponentValueMap;
import datastructures.DefaultComponentValues;
import javafx.util.Pair;

public class Battery extends Component {
    private static final DefaultComponentValues componentDefaults = ComponentValueMap.getInstance().get(Battery.class);
    Component negative;
    Component positive;

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

    @Override
    public void addConnectedComponent(Component component){
        connectedComponents.add(component);
        Pair<Anchor, Anchor> anchors = getLeftRightAnchors();
        // Key is left Anchor
        if(anchors.getKey().getWire() == null) {
            positive = component;
        }
        else if(anchors.getValue().getWire() == null) {
            negative = component;
        }
        else if(anchors.getKey().getWire().getOtherEnd(anchors.getKey()).getParentComponent() == component &&
                negative == null){
            negative = component;
        } else {
            positive = component;
        }
    }

    public Component getNegativeConnection(){
        return negative;
    }

    public Component getPositiveConnection(){
        return positive;
    }

    public void setVoltage(double voltage){
        this.voltage = voltage;
    }
    public void setVariable(double voltage) { this.setVoltage(voltage);}
}
