package components.infrastructure;

import components.parts.Component;
import datastructures.ComponentValueMap;
import datastructures.DefaultComponentValues;

import java.util.Vector;

public class ParallelComponent extends Component {
    public static final int MINCOMPONENTS = 2;
    public static final int MAXCOMPONENTS = 5;
    private static final DefaultComponentValues componentDefaults = ComponentValueMap.getInstance().get(ParallelComponent.class);
    private Vector<Component> components;

    public ParallelComponent(){
        super(false);
    }


    // TODO : Currently public, probably shouldn't be
    public ParallelComponent(Vector<Component>  components, ParallelComponentView componentView, boolean composite){
        super(composite);
        this.components = components;
        this.componentView = componentView;
    }
}
