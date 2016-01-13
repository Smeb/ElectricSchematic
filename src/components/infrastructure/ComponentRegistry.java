package components.infrastructure;

import components.parts.Component;
import javafx.scene.Group;
import javafx.scene.Node;

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

                /*if (c.getNextNodes() != null) {
                    System.out.println(c.getNextNodes().size());
                    c.getNextNodes().getFirst().removePrevNode();
                }
                if (c.getPrevNodes() != null) {
                    System.out.println(c.getPrevNodes().size());
                    c.getPrevNodes().getFirst().removeNextNode();
                }*/
                for(Node n : c.getGroup().getChildren()){
                    if(n.getClass() == Anchor.class){
                        Anchor a = (Anchor)n;
                        a.clearWire();
                    }
                }
                workspace.getChildren().clear();
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
    }
}
