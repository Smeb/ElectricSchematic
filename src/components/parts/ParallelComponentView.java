package components.parts;

import components.infrastructure.ComponentView;

import java.util.Vector;

public class ParallelComponentView extends ComponentView {
    public static final double STACKBUFFER = 50.0;
    Vector<ComponentView> componentViews;
    ParallelComponentView(Vector<ComponentView> componentViews){
        this.componentViews = componentViews;
    }
}
