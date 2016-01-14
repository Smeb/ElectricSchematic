package components.parts;

import components.infrastructure.*;
import datastructures.ComponentValueMap;
import datastructures.CoordinatePair;
import javafx.scene.Group;
import javafx.util.Pair;

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
        Component component;
        if (Lamp.class.isAssignableFrom(componentClass)) {
            component = new Lamp(composite);
        }
        else if (Battery.class.isAssignableFrom(componentClass)){
            component = new Battery(composite);
        }
        else if (Resistor.class.isAssignableFrom(componentClass)) {
            component = new Resistor(composite);
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
        if(classType.length < ParallelComponent.MINCOMPONENTS || classType.length > ParallelComponent.MAXCOMPONENTS){
            System.out.println("Invalid number of arguments");
            return null;
        }
        ComponentViewFactory viewFactory = ComponentViewFactory.getInstance();
        Component current;
        ComponentView currentView;
        Vector<Component> components = new Vector<>();
        Vector<ComponentView> componentViews = new Vector<>();
        int i = 0;
        double heightOffset = 0.0;

        for (Class c : classType) {
            if (!Component.class.isAssignableFrom(c)) {
                return null;
            }
            current = newComponent(c, 0.0, 0.0, true);
            components.add(current);
            currentView = viewFactory.buildComponentGroup(current);
            AnchorFactory.getInstance().addAnchorLines(currentView);
            currentView.setLayoutY(heightOffset);
            heightOffset += ComponentValueMap.getInstance().get(c).getHeight() + ParallelComponent.PARALLELOFFSET;
            componentViews.add(currentView);
            i++;
        }
        ParallelComponentView componentView = new ParallelComponentView(componentViews);
        Pair<CoordinatePair, CoordinatePair> topAnchorPositions;
        currentView = componentViews.get(0);
        Pair<CoordinatePair, CoordinatePair> bottomAnchorPositions;
        currentView = componentViews.get(componentViews.size() - 1);
        ParallelComponent parallelComponent = new ParallelComponent(components, componentView, false);
        componentView.setParentComponent(parallelComponent);
        viewFactory.buildInteractions(componentView, 20.0, 20.0);
        //TODO: Arrangement of ComponentViews within ParallelComponentView
        //TODO: Arrangement of wires connecting components
        //TODO: Attachment of anchors to ParallelComponentView
        //TODO: Registration of interaction events on child views
        return parallelComponent;
    }

}
