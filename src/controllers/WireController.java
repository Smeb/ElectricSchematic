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
//        System.out.println("Drawing wire: " + parent.getPosition().toString() + ", " + end.getPosition().toString());
        Wire wire;
        if(start.getWire() != null){
            // Then we update the original wire
            wire = start.getWire();
            Anchor oldEndAnchor = wire.getEndAnchor();
            oldEndAnchor.removeWire();
            wire.setEndAnchor(end);
            end.addWire(wire, Anchor.Direction.end);
            wire.update(end);
        } else {
            // Otherwise we create a new wire
            wire = makeWire(start, end);
            start.addWire(wire, Anchor.Direction.parent);
            end.addWire(wire, Anchor.Direction.end);
            connectComponents(start.getParentComponent(), end.getParentComponent());
        }
    }

    private void connectComponents(Component componentA, Component componentB){
        componentA.addConnectedComponent(componentB);
        componentB.addConnectedComponent(componentA);
    }

    private void setInteractions(Wire wire){}

    public void eraseWire(Wire wire) {
        workspace.getChildren().remove(wire);
    }
}
