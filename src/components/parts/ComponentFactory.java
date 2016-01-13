package components.parts;

import components.infrastructure.ComponentRegistry;
import components.infrastructure.ComponentView;
import components.infrastructure.ComponentViewFactory;
import javafx.scene.Group;

import java.util.Vector;

public class ComponentFactory {
    private static final ComponentFactory instance = new ComponentFactory();
    private static Group workspace = null;

    public static ComponentFactory getInstance() {
        return instance;
    }

    public static void setWorkspace(Group group){
        workspace = group;
    }

    public Component newComponent(Class componentClass, double posX, double posY, boolean composite){
        Component component = null;
        if (Lamp.class.isAssignableFrom(componentClass)) {
            component = new Lamp(composite);
        }
        else if (Battery.class.isAssignableFrom(componentClass)){
            component = new Battery(composite);
        }
        else {
            return null;
        }
        if(!composite) {
            ComponentView componentView = ComponentViewFactory.getInstance().buildComponentGroup(component);
            ComponentViewFactory.getInstance().buildInteractions(componentView, posX, posY);
            component.setComponentView(componentView);
        }
        ComponentRegistry.getInstance().addComponent(component);
        return component;
    }

    public ParallelComponent newParallelComponent(Class... classType){
        ComponentViewFactory viewFactory = ComponentViewFactory.getInstance();
        Component current;
        ComponentView currentGroup;
        Vector<Component> components = new Vector<Component>();
        Vector<ComponentView> componentViews = new Vector<>();
        for (final Class c : classType) {
            if (!c.isAssignableFrom(Component.class)) {
                return null;
            }
            current = newComponent(c, 0.0, 0.0, true);
            components.add(current);
            currentGroup = viewFactory.buildComponentGroup(current);
            componentViews.add(currentGroup);
        }
        ParallelComponentView componentView = new ParallelComponentView(componentViews);
        ParallelComponent parallelComponent = new ParallelComponent(components, componentView, false);
        workspace.getChildren().add(parallelComponent.getGroup());
        //TODO: Arrangement of ComponentViews within ParallelComponentView
        //TODO: Arrangement of wires connecting components
        //TODO: Attachment of anchors to ParallelComponentView
        //TODO: Registration of interaction events on child views
        return parallelComponent;
    }

}
