package components.infrastructure;

import components.parts.Component;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Iterator;

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
                /*if (c.getNextNodes() != null) {
                    c.getNextNodes().getFirst().removePrevNode();
                }
                if (c.getPrevNodes() != null) {
                    c.getPrevNodes().getFirst().removeNextNode();
                }*/
                components.remove(c);
                break;
            }
        }
    }
    public void deleteAll() {
        for (Iterator<Component> it = components.iterator(); it.hasNext();) {
            Component next = it.next();
            Group workspace = next.getGroup();
            workspace.getChildren().clear();
            it.remove();
        }
        System.out.println("Cleared");
    }
}
