package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Created by carlahyenne on 14/01/2016.
 */
public class Resistor extends Component {

    public Resistor () {
    }

    protected Resistor(boolean composite) {
        super(composite);
        this.resistance = 5.00;
    }

    protected Resistor(double resistance, boolean composite){
        super(composite);
        this.resistance = resistance;
    }

    public void setResistance(double resistance){
        this.resistance = resistance;
    }
}
