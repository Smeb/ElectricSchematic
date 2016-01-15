package components.parts;

/**
 * Created by minttu on 14/01/2016.
 */
public class Voltmeter extends Component {
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
}
