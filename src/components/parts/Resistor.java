package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Created by carlahyenne on 14/01/2016.
 */
public class Resistor extends Component {
    public static final double width = 180.0;
    public static final double height = 30.0;
    public static final ImagePattern iconColor = new ImagePattern(new Image("file:img/resistor-colour.png"));
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/resistor-icon.png"));
    public static final String name = "Resistor";

    public Resistor () {
    }

    protected Resistor(boolean composite) {
        super(composite);
        this.resistance = 0.001;
    }

    protected Resistor(double resistance, boolean composite){
        super(composite);
        this.resistance = resistance;
    }

    public void setResistance(double resistance){
        this.resistance = resistance;
    }

}
