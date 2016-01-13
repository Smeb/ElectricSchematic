package components.infrastructure;

import components.parts.Component;
import javafx.scene.Group;

import java.util.ArrayList;

public class ComponentRegistry {
    private ArrayList<Component> components;

    private static ComponentRegistry instance = null;
    public static ComponentRegistry getInstance(){
        if(instance == null)
            instance = new ComponentRegistry();
        return instance;
    }

    private ComponentRegistry(){
        components = new ArrayList<>();
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public ArrayList<Component> getComponents(){
        return (ArrayList<Component>)components.clone();
    }

    public void deleteComponent(int thisId) {
        for (Component c : components) {
            if (c.thisId == thisId) {
                Group workspace = c.getGroup();
                workspace.getChildren().clear();
                components.remove(c);
                //c = null;
                break;
            }
        }
    }
}
