package components.parts;

import components.infrastructure.ComponentView;
import components.infrastructure.ComponentViewFactory;

import java.util.Vector;

public class ParallelComponentFactory {
    ParallelComponentFactory instance = new ParallelComponentFactory();
    private ParallelComponentFactory(){}

    public ParallelComponentFactory getInstance(){
        return instance;
    }

    public ParallelComponent newParallelComponent(final Class... classType) {
        ComponentFactory componentFactory = ComponentFactory.getInstance();
        ComponentViewFactory viewFactory = ComponentViewFactory.getInstance();
        Component current;
        ComponentView currentGroup;
        Vector<ComponentView> componentViews = new Vector<>();
        for (final Class c : classType) {
            if (!c.isAssignableFrom(Component.class)) {
                return null;
            }
            current = componentFactory.newComponent(c, 0.0, 0.0, true);
            currentGroup = viewFactory.buildComponentGroup(current);
            componentViews.add(currentGroup);
        }

        ParallelComponent parallelComponent = new ParallelComponent(new ParallelComponentView(componentViews), false);
        //TODO: Arrangement of ComponentViews within ParallelComponentView
        //TODO: Arrangement of wires connecting components
        //TODO: Attachment of anchors to ParallelComponentView
        //TODO: Registration of interaction events on child views

        return parallelComponent;
    }
}
