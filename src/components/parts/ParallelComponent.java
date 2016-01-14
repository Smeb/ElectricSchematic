package components.parts;

import datastructures.ComponentValueMap;
import datastructures.DefaultComponentValues;

import java.util.Vector;

public class ParallelComponent extends Component {
    private static final DefaultComponentValues componentDefaults = ComponentValueMap.getInstance().get(ParallelComponent.class);
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
