package components.infrastructure;

import application.Globals;
import components.parts.Battery;
import components.parts.Component;
import components.parts.Lamp;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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

                // Remove references to c from neighbours
                for (Component neighbour : c.getConnectedComponents()) {
                    LinkedList<Component> neighbourConnections = neighbour.getConnectedComponents();
                    for (Component neighboursNeighbour : neighbourConnections) {
                        if (neighboursNeighbour == c) {
                            neighbourConnections.remove(neighboursNeighbour);
                            System.out.println("Removed connection from " + c.thisId + " to " + neighbour.thisId);
                            break;
                        }
                    }
                }
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
            Group group = next.getGroup();
            for(Node n : group.getChildren()){
                if(n.getClass() == Anchor.class){
                    Anchor a = (Anchor)n;
                    a.clearWire();
                }
            }
            group.getChildren().clear();
            it.remove();
        }
    }
    public void changeIcons() {
        for (Component c : components) {
            if (c instanceof Battery) {
                ((Battery)c).changeIcon(Globals.schematicIcons);
            }
            else if (c instanceof Lamp) {
                ((Lamp)c).changeIcon(Globals.schematicIcons);
            }
        }
    }
}
