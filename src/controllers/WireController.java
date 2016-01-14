package controllers;

import components.infrastructure.Anchor;
import javafx.scene.Group;
import tools.Wire;

public class WireController {

    private static Group workspace;
    private static WireController instance;
    private boolean active = false;
    private Anchor parent;

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

    public Anchor getParentAnchor(){return parent;}

    public boolean active(){return active;}
    public void setActive(){active = true;}
    public void setDormant(){active = false;}
    public void setParent(Anchor start){this.parent = start;}
    public void completeWire(Anchor end){

        Wire wire;
        if(parent.getWire() != null) {
            // Then we update the original wire
            wire = parent.getWire();

            Anchor oldParentAnchor = wire.getParentAnchor();
            oldParentAnchor.removeWire();
            Anchor oldEndAnchor = wire.getEndAnchor();
            oldEndAnchor.removeWire();
            wire.setParentAnchor(parent);
            parent.addWire(wire, Anchor.Direction.parent);
            wire.setEndAnchor(end);
            end.addWire(wire, Anchor.Direction.end);
            parent.updateDirection(Anchor.Direction.parent);
            wire.update(parent);
            wire.update(end);
        } else {
            // Otherwise we create a new wire
            wire = makeWire(parent, end);
            parent.addWire(wire, Anchor.Direction.parent);
            end.addWire(wire, Anchor.Direction.end);
        }
    }

    public void setInteractions(Wire wire){}

    public void eraseWire(Wire wire) {
        workspace.getChildren().remove(wire);
    }
}
