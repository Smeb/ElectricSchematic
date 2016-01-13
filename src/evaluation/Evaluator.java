package evaluation;

import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

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
        Component current = c;
        HashSet<Integer> visitedComponents = new HashSet<>();
        System.out.println(current.toString());
        visitedComponents.add(c.thisId);
        while((current = getUnvisitedComponent(c.getConnectedComponents(), visitedComponents)) != null){
            System.out.println(current.toString());
        }
    }

    private Component getUnvisitedComponent(LinkedList<Component> connectedComponents, HashSet<Integer> visitedComponents){
        for(Component c : connectedComponents){
            if(!visitedComponents.contains(c.thisId)){
                visitedComponents.add(c.thisId);
                return c;
            }
        }
        return null;
    }
}
