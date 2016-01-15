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
        ComponentRegistry.getInstance().setAllNextComponentsNull();
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
        while((current.getNextComponent()) == null && (current = findNextComponent(current)) != null){
            r.remove(current);
            rTotal += current.getResistance();
            vTotal += current.getVoltage();
            if(current.getConnectedComponents().size() != 2) {
                // For components in series all components should have two connected components
                return;
            }
        }

        double aTotal = vTotal / rTotal;

        System.out.println("Second iteration");
        // Iterate a second time and set the current of all components
        Component origin = c;
        System.out.println(origin.toString());
        while((c = c.getNextComponent()) != origin){
            System.out.println(c.toString());
        }
        System.out.println("V: " + vTotal + " R: " + rTotal + " A: " + aTotal);
    }

    private Component findNextComponent(Component component){
        System.out.print("Given a : " + component.toString());
        if(component instanceof Battery){
            Component c = ((Battery) component).getPositiveConnection();
            component.setNextComponent(c);
            System.out.println("found a : " + c.toString());
            return c;

        } else {
            for(Component c : component.getConnectedComponents()){
                if(c.getNextComponent() != component){
                    component.setNextComponent(c);
                    System.out.println("found a : " + c.toString());
                    return c;
                }
            }
        }
        return null;
    }
}
