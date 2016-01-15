package evaluation;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;
import components.parts.Voltmeter;

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
            return;
        }

        Anchor outGoingAnchor = c.getLeftRightAnchors().getValue();
        Anchor originalExitAnchor = outGoingAnchor;
        Anchor incomingAnchor = null;
        // Mark up all anchors in the map of components to give direction
        do {
            incomingAnchor = outGoingAnchor.getWire().getOtherEnd(outGoingAnchor);
            allocateTerminals(outGoingAnchor, incomingAnchor);
            outGoingAnchor = (incomingAnchor.getParentComponent().getLeftRightAnchors().getKey() == incomingAnchor) ?
                    incomingAnchor.getParentComponent().getLeftRightAnchors().getValue() :
                    incomingAnchor.getParentComponent().getLeftRightAnchors().getKey();
        } while(outGoingAnchor != originalExitAnchor);
        /*
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
        */
    }

    private void resolveVoltmeter(Voltmeter v){
        v.setVoltDisplay(0.0);
    }


    public void allocateTerminals(Anchor from, Anchor to){
        System.out.println("Writing polarity");
        from.getParentComponent().setAnchorPolarity(from, 1);
        to.getParentComponent().setAnchorPolarity(to, 0);
    }
}
