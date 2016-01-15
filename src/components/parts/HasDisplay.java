package components.parts;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Created by archie on 15/01/16.
 */
public abstract class HasDisplay extends Component {
    protected Label display;
    public abstract void setDisplayNode(Label label);

    public HasDisplay(){

    }

    public HasDisplay(boolean composite){
        super(composite);
    }
}
