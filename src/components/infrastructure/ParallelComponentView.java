package components.infrastructure;

import java.util.Vector;

public class ParallelComponentView extends ComponentView {
    public static final double STACKBUFFER = 50.0;
    Vector<ComponentView> componentViews;

    public ParallelComponentView(Vector<ComponentView> componentViews){
        this.componentViews = componentViews;
        for(ComponentView v : componentViews){
            this.getChildren().add(v);
        }

    }
}
