package evaluation;

import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by archie on 12/01/16.
 */
public class Evaluator {
    ComponentRegistry registry;
    public Evaluator(){
        registry = ComponentRegistry.getInstance();
    }

    public void evaluate(){
        ArrayList<Component> registryCopy = new ArrayList<>();
        for(Component c : registry.getComponents()){
            registryCopy.add(c);
        }
        for(Component c : registryCopy){
            if(c instanceof Battery){
                evaluateGraph(c);
            }
        }
    }

    public void evaluateGraph(Component c){
        Component root = c;
        HashSet<Integer> visitedComponents = new HashSet<>();
        visitedComponents.add(c.thisId);
        int i = 0;
        if(c.getConnectedComponents() != null){
            Component current = getUnvisitedComponent(c.getConnectedComponents(), visitedComponents);
            while(true){
                System.out.println(current.thisId);
                for(Node n : c.getGroup().getChildren()) {
                    ;
                }
                if((current = getUnvisitedComponent(current.getConnectedComponents(), visitedComponents)) == null){
                    break;
                }
            }
            System.out.println("Search completed");
        }
    }

    private Component getUnvisitedComponent(LinkedList<Component> connectedComponent, HashSet<Integer> visitedComponents){
        for(Component c : connectedComponent){
            if(!visitedComponents.contains(c.thisId)){
                visitedComponents.add(c.thisId);
                return c;
            }
        }
        return null;
    }
}
