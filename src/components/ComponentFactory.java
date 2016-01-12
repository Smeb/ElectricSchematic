package components;

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
        if(componentClass == Component.class){
            Group group = ComponentGroupFactory.getInstance().buildComponentGroup(componentClass, posX, posY);
            component = new Component(group);
        }
        return component;
    }

}
