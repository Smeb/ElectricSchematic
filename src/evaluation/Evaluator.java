package evaluation;

import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Evaluator {
    private ComponentRegistry registry;

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
        double rTotal = 0.0;
        double vTotal = 0.0;
        if(c.getConnectedComponents().size() != 2){
            // Immediate failure, found battery is not connected
            return;
        }

        // Iterate once and find the resistance
        while((current = getUnvisitedComponent(current.getConnectedComponents(), visitedComponents)) != null){
            rTotal += current.getResistance();
            vTotal += current.getVoltage();
            if(current.getConnectedComponents().size() != 2) {
                // For components in series all components should have two connected components
                return;
            }
        }
        current = c;
        visitedComponents = new HashSet<>();
        double aTotal = vTotal / rTotal;

        // Iterate a second time and set the current of all components
        while((current = getUnvisitedComponent(current.getConnectedComponents(), visitedComponents)) != null){
            current.setCurrent(aTotal);
        }
        System.out.println("V: " + vTotal + " R: " + rTotal + " A: " + aTotal);
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
