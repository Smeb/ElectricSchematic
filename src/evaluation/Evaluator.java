package evaluation;

import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;

import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Evaluator {
    ConcurrentLinkedQueue<Component> registryCopy = new ConcurrentLinkedQueue<>();
    private ComponentRegistry registry;

    public Evaluator(){
        registry = ComponentRegistry.getInstance();
    }

    public void evaluate(){
        for(Component c : registry.getComponents()){
            registryCopy.add(c);
        }

        for(Component c : registryCopy){
            if(c instanceof Battery){
                evaluateGraph(c, registryCopy);
            }
            if(registryCopy.isEmpty()){
                break;
            }
        }
    }

    public void evaluateGraph(Component c, ConcurrentLinkedQueue<Component> r){
        Component current = c;
        HashSet<Integer> visitedComponents = new HashSet<>();
        double rTotal = 0.0;
        double vTotal = 0.0;
        if(c.getConnectedComponents().size() != 2){
            // Immediate failure, found battery is not connected
            return;
        }
        // Iterate once and find the resistance
        while((current = getNextComponent(current, visitedComponents)) != null){
            r.remove(current);
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
        Component origin = c;
        while(c.getNextComponent() != origin){
            System.out.println(c.toString());
        }
        System.out.println("V: " + vTotal + " R: " + rTotal + " A: " + aTotal);
    }

    private Component getNextComponent(Component component, HashSet<Integer> visitedComponents){
        if(component instanceof Battery){
            Component c = ((Battery) component).getPositiveConnection();
            if(!visitedComponents.contains(c.thisId)){
                visitedComponents.add(c.thisId);
                component.setNextComponent(c);
                return c;
            }
        } else {
            for(Component c : component.getConnectedComponents()){
                if(!visitedComponents.contains(c.thisId)){
                    visitedComponents.add(c.thisId);
                    component.setNextComponent(c);
                    return c;
                }
            }
        }
        return null;
    }
}
