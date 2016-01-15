package evaluation;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentRegistry;
import components.parts.Ammeter;
import components.parts.Battery;
import components.parts.Component;
import components.parts.Voltmeter;
import javafx.util.Pair;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Evaluator {
    ConcurrentLinkedQueue<Component> registryCopy = new ConcurrentLinkedQueue<>();
    private ComponentRegistry registry;

    public Evaluator(){
        registry = ComponentRegistry.getInstance();
    }

    public void evaluate(){
        ComponentRegistry.getInstance().setAllNextComponentsNull();
        ComponentRegistry.getInstance().setAllPolaritiesNull();
        for(Component c : registry.getComponents()){
            registryCopy.add(c);
        }
        boolean validFlag = true;
        for(Component c : registryCopy){
            if(c instanceof Battery){
                validFlag = (validFlag) ? evaluateGraph(c, registryCopy) : validFlag;
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

    public boolean evaluateGraph(Component c, ConcurrentLinkedQueue<Component> r){
        if(c.getConnectedComponents().size() != 2){
            return false;
        }

        Anchor outGoingAnchor = c.getLeftRightAnchors().getValue();
        Anchor originalExitAnchor = outGoingAnchor;
        Anchor incomingAnchor;
        // Mark up all anchors in the map of components to give direction
        double rTotal = 0.0;
        double vTotal = 0.0;
        boolean validFlag = true;
        do {
            incomingAnchor = outGoingAnchor.getWire().getOtherEnd(outGoingAnchor);
            if(incomingAnchor.getParentComponent() instanceof Battery){
                if(incomingAnchor == incomingAnchor.getParentComponent().getLeftRightAnchors().getValue()) {
                    rTotal = 0.0;
                    vTotal = 0.0;
                    validFlag = false;
                }
            }
            allocateTerminals(outGoingAnchor, incomingAnchor);
            r.remove(incomingAnchor);
            if(validFlag) {
                rTotal += incomingAnchor.getParentComponent().getResistance();
                vTotal += incomingAnchor.getParentComponent().getVoltage();
            }
            outGoingAnchor = (incomingAnchor.getParentComponent().getLeftRightAnchors().getKey() == incomingAnchor) ?
                    incomingAnchor.getParentComponent().getLeftRightAnchors().getValue() :
                    incomingAnchor.getParentComponent().getLeftRightAnchors().getKey();
        } while(outGoingAnchor != originalExitAnchor);

        double aTotal = (rTotal != 0.0) ? vTotal / rTotal : 0.0;

        System.out.println("V: " + vTotal + " R: " + rTotal + " A: " + aTotal);

        outGoingAnchor = c.getLeftRightAnchors().getValue();
        originalExitAnchor = outGoingAnchor;

        do {
            incomingAnchor = outGoingAnchor.getWire().getOtherEnd(outGoingAnchor);
            if(incomingAnchor.getParentComponent() instanceof Ammeter){
                incomingAnchor.getParentComponent().setCurrent(aTotal);
            }
            outGoingAnchor = (incomingAnchor.getParentComponent().getLeftRightAnchors().getKey() == incomingAnchor) ?
                    incomingAnchor.getParentComponent().getLeftRightAnchors().getValue() :
                    incomingAnchor.getParentComponent().getLeftRightAnchors().getKey();
        } while(outGoingAnchor != originalExitAnchor);
        return validFlag;
    }

    private void resolveVoltmeter(Voltmeter v){
        System.out.println("Called");
        Pair<Anchor, Anchor> anchorPair = v.getLeftRightAnchors();
        // Check voltmeter is connected to circuit
        int count = 0;
        double voltage = 0.0;
        if(anchorPair.getKey().getWire() != null && anchorPair.getValue().getWire() != null){
            Anchor outGoingAnchor = anchorPair.getValue().getWire().getOtherEnd(anchorPair.getValue());
            Anchor finalAnchor = anchorPair.getKey().getWire().getOtherEnd(anchorPair.getKey());
            Anchor incomingAnchor;
            // outGoingAnchor should be the anchor the voltmeter's positive wire is connected to
            // finalAnchor should be the anchor the voltmeter's negative wire is connected to
            do {
                if(!outGoingAnchor.getParentComponent().getAnchorPolarity(outGoingAnchor)){
                    // If outGoingAnchor is negative
                    // Get the opposite anchor - positive
                    outGoingAnchor = (outGoingAnchor.getParentComponent().getLeftRightAnchors().getKey() == outGoingAnchor) ?
                            outGoingAnchor.getParentComponent().getLeftRightAnchors().getValue() :
                            outGoingAnchor.getParentComponent().getLeftRightAnchors().getKey();
                    // Add the voltage to the count
                    voltage += outGoingAnchor.getParentComponent().getVoltage();
                } else {
                    // Otherwise outgoing anchor is positive
                    // Set incoming anchor to the outgoning anchor's opposite - positive
                    incomingAnchor = outGoingAnchor.getWire().getOtherEnd(outGoingAnchor);
                    // If the negative terminal is the end then halt
                    if(incomingAnchor == finalAnchor){
                        break;
                    }
                    // Otherwise add the component
                    voltage += incomingAnchor.getParentComponent().getVoltage();
                    // Step over the component
                    outGoingAnchor = (incomingAnchor.getParentComponent().getLeftRightAnchors().getKey() == incomingAnchor) ?
                            incomingAnchor.getParentComponent().getLeftRightAnchors().getValue() :
                            incomingAnchor.getParentComponent().getLeftRightAnchors().getKey();
                    // If the positive terminal is the final anchor stop
                }
                count++;
                if(count == 50){
                    voltage = 0;
                    break;
                }
            } while(outGoingAnchor != finalAnchor);
            v.setVoltDisplay(voltage);
        }
    }


    public void allocateTerminals(Anchor from, Anchor to){
        from.getParentComponent().setAnchorPolarity(from, true);
        to.getParentComponent().setAnchorPolarity(to, false);
    }
}
