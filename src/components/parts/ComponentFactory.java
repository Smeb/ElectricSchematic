package components.parts;

import components.infrastructure.ComponentGroup;
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

    public Component newComponent(Class componentClass, double posX, double posY, boolean composite){
        Component component = null;
        if (Lamp.class.isAssignableFrom(componentClass)) {
            component = new Lamp(composite);
        }
        else if (Battery.class.isAssignableFrom(componentClass)){
            component = new Battery(composite);
        }
        if(!composite) {
            ComponentGroup componentGroup = ComponentGroupFactory.getInstance().buildComponentGroup(component);
            ComponentGroupFactory.getInstance().buildInteractions(componentGroup, posX, posY);
            component.setComponentGroup(componentGroup);
        }
        ComponentRegistry.getInstance().addComponent(component);
        return component;
    }

}
