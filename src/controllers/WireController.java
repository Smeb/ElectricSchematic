package controllers;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentGroup;
import components.parts.Component;
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
        System.out.println("Drawing wire: " + parent.getPosition().toString() + " " + end.getPosition().toString());
        Wire wire = makeWire(parent, end);
        parent.addWire(wire, Anchor.Direction.send);
        end.addWire(wire, Anchor.Direction.recv);
        Component sendComponent = ((ComponentGroup) parent.getParent()).getParentComponent();
        Component recvComponent = ((ComponentGroup) end.getParent()).getParentComponent();
        sendComponent.addConnectedComponent(recvComponent);
        recvComponent.addConnectedComponent(sendComponent);
    }

    public void setInteractions(Wire wire){}

    public void eraseWire(Wire wire) {
        workspace.getChildren().remove(wire);
    }
}
