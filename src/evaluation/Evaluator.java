package evaluation;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentRegistry;
import components.parts.Ammeter;
import components.parts.Battery;
import components.parts.Component;
import components.parts.Voltmeter;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
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
        for(Component c : ComponentRegistry.getInstance().getComponents()){
            if(c instanceof Voltmeter){
                resolveVoltmeter((Voltmeter)c);
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
        // Iterate a second time and set the current and voltages of reading devices
        Component origin = c;
        System.out.println(origin.toString());
        while((c = c.getNextComponent()) != origin && c != null){
            if(c instanceof Ammeter){
                c.setCurrent(aTotal);
            }
        }
        System.out.println("V: " + vTotal + " R: " + rTotal + " A: " + aTotal);
    }

    private void resolveVoltmeter(Voltmeter v){
        Pair<Anchor, Anchor> pairedAnchors = v.getLeftRightAnchors();
        if(!(pairedAnchors.getKey().getWire() != null && pairedAnchors.getValue().getWire() != null)){
            return;
        }
        Component candidateA = pairedAnchors.getKey().getWire().getOtherEnd(pairedAnchors.getKey()).getParentComponent();
        Component candidateB = pairedAnchors.getValue().getWire().getOtherEnd(pairedAnchors.getValue()).getParentComponent();
        if(candidateA == candidateB){
            v.setVoltDisplay(candidateA.getVoltage());
            return;
        }
        Component c = candidateA;
        double voltage = c.getVoltage();
        while((c = c.getNextComponent()) != candidateB){
            voltage += c.getVoltage();
        }
        v.setVoltDisplay(voltage);
    }

    private Component findNextComponent(Component component){
        System.out.print("Given a : " + component.toString());
        if(component instanceof Battery){
            Component c = ((Battery) component).getPositiveConnection();
            component.setNextComponent(c);
            System.out.println("found a : " + c.toString());
            return c;

        } else {
            if(component.getConnectedComponents().get(0) == component.getConnectedComponents().get(1)){
                Component c = component.getConnectedComponents().getFirst();
                component.setNextComponent(c);
                System.out.println("found a : " + c.toString());
                return c;
            }
            for(Component c : component.getConnectedComponents()){
                if(c.getNextComponent() != component){
                    component.setNextComponent(c);
                    System.out.println("found a : " + c.toString());
                    return c;
                }
            }
        }
        System.out.println("Found nothing");
        for(Component c : component.getConnectedComponents()){
            System.out.println(c.toString());
        }
        return null;
    }
}
