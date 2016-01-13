package components.parts;

import components.infrastructure.ComponentGroupFactory;
import components.infrastructure.ComponentRegistry;
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

    public Component newComponent(Class componentClass, double posX, double posY){
        Component component = null;
        if (Lamp.class.isAssignableFrom(componentClass)) {
            component = new Lamp();
        }
        else if (Battery.class.isAssignableFrom(componentClass)){
            component = new Battery();
        }
        component.setComponentGroup(ComponentGroupFactory.getInstance().buildComponentGroup(component, posX, posY));

        ComponentRegistry.getInstance().addComponent(component);
        return component;
    }

}
