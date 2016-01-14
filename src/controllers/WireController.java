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
            deleteWire(start.getWire());
        }
        wire = makeWire(start, end);
        start.addWire(wire, Anchor.Direction.start);
        end.addWire(wire, Anchor.Direction.end);
        connectComponents(start.getParentComponent(), end.getParentComponent());

    }

    private void connectComponents(Component componentA, Component componentB){
        componentA.addConnectedComponent(componentB);
        componentB.addConnectedComponent(componentA);
    }

    private void decoupleComponents(Component componentA, Component componentB){
        componentA.removeConnectedComponent(componentB);
        componentB.removeConnectedComponent(componentA);
    }

    public void deleteWire(Wire wire){
        Component componentA, componentB;
        componentB = wire.getParentAnchor().getParentComponent();
        componentA = wire.getEndAnchor().getParentComponent();
        decoupleComponents(componentA, componentB);

        wire.getParentAnchor().removeWire();
        wire.getEndAnchor().removeWire();
        eraseWire(wire);
    }

    private void setInteractions(Wire wire){}

    public void eraseWire(Wire wire) {
        workspace.getChildren().remove(wire);
    }
}