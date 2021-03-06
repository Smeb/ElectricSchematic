package components.parts;

import components.infrastructure.ComponentRegistry;
import components.infrastructure.ComponentView;
import components.infrastructure.ComponentViewFactory;
import javafx.scene.Group;

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
        else if (Ammeter.class.isAssignableFrom(componentClass)) {
            component = new Ammeter(composite);
        }
        else if (Voltmeter.class.isAssignableFrom(componentClass)) {
            component = new Voltmeter(composite);
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



}
