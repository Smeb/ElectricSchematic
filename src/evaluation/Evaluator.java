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
        Component originBattery = c;
        Component current = c;
        HashSet<Integer> visitedComponents = new HashSet<>();

        if(c.getConnectedComponents().size() != 2){
            // Immediate failure, found battery is not connected
            return;
        }
        // Iterate once and find the resistance
        while((current.getNextComponent()) == null && (current = findNextComponent(current)) != null){
            r.remove(current);

            if(current.getConnectedComponents().size() != 2) {
                // For components in series all components should have two connected components
                return;
            }
        }
        current = originBattery;
        double rTotal = 0.0;
        double vTotal = 0.0;
        do {
            rTotal += current.getResistance();
            vTotal += current.getVoltage();
        } while((current = current.getNextComponent()) != originBattery && current != null);

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
        Anchor positiveAnchor = pairedAnchors.getValue();
        Anchor negativeAnchor = pairedAnchors.getKey();
        Component positiveTerminal = positiveAnchor.getWire().getOtherEnd(positiveAnchor).getParentComponent();
        Component negativeTerminal = negativeAnchor.getWire().getOtherEnd(negativeAnchor).getParentComponent();
        if(positiveTerminal == negativeTerminal){
            if(positiveTerminal.getAnchorPolarity(positiveAnchor) == 1) {
                v.setVoltDisplay(positiveTerminal.getVoltage());
            } else {
                v.setVoltDisplay(-positiveTerminal.getVoltage());
            }
            return;
        }
        Component c = positiveTerminal;
        double voltage = c.getVoltage();
        while((c = c.getNextComponent()) != negativeTerminal){
            voltage += c.getVoltage();
        }
        v.setVoltDisplay(voltage);
    }

    private Component findNextComponent(Component component){
        if(component.getConnectedComponents().get(0) == component.getConnectedComponents().get(1)){
            Component c = component.getConnectedComponents().getFirst();
            component.setNextComponent(c);
            c.setNextComponent(component);
            allocateTerminals(component, c);
            return null;
        }
        if(component instanceof Battery){
            Component c = ((Battery) component).getPositiveConnection();
            component.setNextComponent(c);
            allocateTwoTerminals(component, c);
            return c;
        } else {
            for(Component c : component.getConnectedComponents()){
                if(c.getNextComponent() != component){
                    component.setNextComponent(c);
                    allocateTerminals(component, c);
                    return c;
                }
            }
        }
        for(Component c : component.getConnectedComponents()){
            System.out.println(c.toString());
        }
        return null;
    }

    public void allocateTwoTerminals(Component from, Component to){
        Pair<Anchor, Anchor> anchorPair = from.getLeftRightAnchors();
        Anchor a = anchorPair.getKey();
        Anchor b = anchorPair.getValue();
        System.out.println("Writing polarity");
        from.setAnchorPolarity(b, 1);
        to.setAnchorPolarity(b.getWire().getOtherEnd(b), 0);

        from.setAnchorPolarity(a, 0);
        to.setAnchorPolarity(a.getWire().getOtherEnd(a), 1);

    }

    public void allocateTerminals(Component from, Component to){
        Pair<Anchor, Anchor> anchorPair = from.getLeftRightAnchors();
        Anchor a = anchorPair.getKey();
        Anchor b = anchorPair.getValue();
        System.out.println("Writing polarity");
        // Then anchor a -> anchor on Component to
        if(a.getWire().getOtherEnd(a).getParentComponent() == to){
            from.setAnchorPolarity(a, 1);
            to.setAnchorPolarity(a.getWire().getOtherEnd(a), 0);
        } else {
            from.setAnchorPolarity(b, 1);
            to.setAnchorPolarity(b.getWire().getOtherEnd(b), 0);
        }
    }
}
