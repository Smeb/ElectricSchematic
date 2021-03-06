package components.infrastructure;

import components.parts.Component;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ComponentRegistry {
    private static ComponentRegistry instance = null;
    private ArrayList<Component> components;
    private ComponentRegistry(){
        components = new ArrayList<>();
    }

    public static ComponentRegistry getInstance(){
        if(instance == null)
            instance = new ComponentRegistry();
        return instance;
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public ArrayList<Component> getComponents(){
        return (ArrayList<Component>)components.clone();
    }

    public void setAllPolaritiesNull(){
        for(Component c : components){
            c.setAnchorPolarity(null, true);
            c.setAnchorPolarity(null, false);
        }
    }

    public void setAllNextComponentsNull(){
        for(Component c : components){
            c.setNextComponent(null);
        }
    }

    public void deleteComponent(int thisId) {
        System.out.println("Deleting");
        Component target = null;
        for (Component c : components) {
            if (c.thisId == thisId) {
                target = c;
                System.out.println(c.toString());
            }
        }
        System.out.println("Scan over");
        Group componentView = target.getGroup();
        // Remove references to c from neighbours
        for (Component neighbour : target.getConnectedComponents()) {
            LinkedList<Component> neighbourConnections = neighbour.getConnectedComponents();
            for (Component neighboursNeighbour : neighbourConnections) {
                if (neighboursNeighbour == target) {
                    neighbourConnections.remove(neighboursNeighbour);
                    break;
                }
            }
        }
        for(Node n : target.getGroup().getChildren()){
            if(n.getClass() == Anchor.class){
                Anchor a = (Anchor)n;
                a.clearWire();
            }
        }
        componentView.getChildren().clear();
        components.remove(target);
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
            c.fill();
        }
    }
}
