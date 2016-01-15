package components.parts;

import application.Globals;
import components.infrastructure.Anchor;
import datastructures.ComponentValueMap;
import datastructures.DefaultComponentValues;
import javafx.util.Pair;

import java.util.LinkedList;

public class Battery extends Component {
    private static final DefaultComponentValues componentDefaults = ComponentValueMap.getInstance().get(Battery.class);
    private Component negative;
    private Component positive;

    public Battery(){
        connectedComponents = new LinkedList<>();
        this.voltage = 9.0;
        this.resistance = 0.001;
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
    public void removeConnectedComponent(Component component){
        if(positive == component){
            positive = null;
        } else {
            negative = null;
        }
        connectedComponents.removeFirstOccurrence(component);
    }

    public void addPositiveComponent(Component component){
        positive = component;
        addConnectedComponent(component);
    }

    public void addNegativeComponent(Component component){
        negative = component;
        addConnectedComponent(component);
    }

    @Override
    public void addConnectedComponent(Component component){
        connectedComponents.add(component);
        if(!Globals.testMode) {
            Pair<Anchor, Anchor> anchors = getLeftRightAnchors();
            // Key is left Anchor
            if (anchors.getKey().getWire() == null) {
                positive = component;
            } else if (anchors.getValue().getWire() == null) {
                negative = component;
            } else if (anchors.getKey().getWire().getOtherEnd(anchors.getKey()).getParentComponent() == component &&
                    negative == null) {
                negative = component;
            } else {
                positive = component;
            }
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
