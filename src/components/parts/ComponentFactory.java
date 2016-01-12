package components.parts;

import components.infrastructure.ComponentGroup;
import components.infrastructure.ComponentGroupFactory;
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
            ComponentGroup group = ComponentGroupFactory.getInstance().buildComponentGroup(componentClass, posX, posY);
            component = new Lamp(group);
        }
        else if (Battery.class.isAssignableFrom(componentClass)){
            ComponentGroup group = ComponentGroupFactory.getInstance().buildComponentGroup(componentClass, posX, posY);
            component = new Battery(group);
        }
        return component;
    }

}
