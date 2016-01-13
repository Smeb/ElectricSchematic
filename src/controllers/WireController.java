package controllers;

import components.infrastructure.Anchor;
import components.parts.Component;
import javafx.scene.Group;
import tools.Wire;

public class WireController {

    private static Group workspace;
    private static WireController instance;
    private boolean active = false;
    private Anchor start;

    private WireController() {
    }

    public static WireController getInstance() {
        if (instance == null) {
            instance = new WireController();
        }
        return instance;
    }

    public static void setWorkspace(Group group){
        workspace = group;
    }

    public Wire makeWire(Anchor start, Anchor end)
    {
        Wire wire = new Wire(start, end);
        workspace.getChildren().add(wire);
        setInteractions(wire);
        return wire;
    }

    public Anchor getStartAnchor(){return start;}

    public boolean active(){return active;}
    public void setActive(){active = true;}
    public void setDormant(){active = false;}
    public void setStart(Anchor start){this.start = start;}
    public void completeWire(Anchor end){
        Wire wire;
        if(start.getWire() != null) {
            // Then we update the original wire
            wire = start.getWire();

            //Remove old anchors
            Anchor oldParentAnchor = wire.getParentAnchor();
            oldParentAnchor.removeWire();
            Anchor oldEndAnchor = wire.getEndAnchor();
            oldEndAnchor.removeWire();
            wire.setParentAnchor(start);
            start.addWire(wire, Anchor.Direction.start);
            wire.setEndAnchor(end);
            end.addWire(wire, Anchor.Direction.end);
            start.updateDirection(Anchor.Direction.start);
            wire.update(start);
            wire.update(end);
        } else {
            // Otherwise we create a new wire
            wire = makeWire(start, end);
            start.addWire(wire, Anchor.Direction.start);
            end.addWire(wire, Anchor.Direction.end);
            connectComponents(start.getParentComponent(), end.getParentComponent());
        }
    }

    private void connectComponents(Component componentA, Component componentB){
        System.out.println("Connecting " + componentA.toString() + " to " + componentB.toString());
        componentA.addConnectedComponent(componentB);
        componentB.addConnectedComponent(componentA);
    }

    private void setInteractions(Wire wire){}

    public void eraseWire(Wire wire) {
        workspace.getChildren().remove(wire);
    }
}
