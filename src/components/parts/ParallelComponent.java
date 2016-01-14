package components.parts;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Vector;

public class ParallelComponent extends Component {
    public static final ImagePattern iconColor = new ImagePattern(new Image("file:img/battery-colour.png"));
    public static final ImagePattern schematic = new ImagePattern(new Image("file:img/battery-icon.png"));
    public static final String name = "ParallelComponent";
    private double width = 80.0;
    private double height = 50.0;
    private Vector<Component> components;

    public ParallelComponent(){
        super(false);
    }

    protected ParallelComponent(Vector<Component>  components, ParallelComponentView componentView, boolean composite){
        super(composite);
        this.components = components;
        this.componentView = componentView;
    }
}
