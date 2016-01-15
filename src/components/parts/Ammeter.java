package components.parts;

/**
 * Created by minttu on 14/01/2016.
 */
public class Ammeter extends Component {
    public Ammeter(){
    }

    protected Ammeter(boolean composite) {
        super(composite);
        this.voltage = 0.0;
        this.resistance = 1.1;
    }

    protected Ammeter(double voltage, double resistance, boolean composite){
        super(composite);
        this.voltage = voltage;
        this.resistance = resistance;
    }
}
