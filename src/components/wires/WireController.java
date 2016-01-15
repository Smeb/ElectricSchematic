package components.wires;

import components.infrastructure.Anchor;
import components.parts.Component;
import components.parts.Voltmeter;
import javafx.scene.Group;

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
        return wire;
    }

    public Anchor getStartAnchor(){return start;}

    public boolean active(){return active;}
    public void setActive(){active = true;}
    public void setDormant(){active = false;}
    public void setStart(Anchor start){this.start = start;}
    public void completeWire(Anchor end){
        Wire wire;
        if(end.getParentComponent() instanceof Voltmeter){
            return;
        }
        if(start.getWire() != null) {
            deleteWire(start.getWire());
        } else if (end.getWire() != null){
            deleteWire(end.getWire());
        }
        if(start.getParentComponent() instanceof Voltmeter){
            wire = new ToolWire(start, end);
            start.addWire((ToolWire)wire);
            end.addToolWire((ToolWire)wire);
        } else {
            wire = new Wire(start, end);
            connectComponents(start.getParentComponent(), end.getParentComponent());
            start.addWire(wire);
            end.addWire(wire);
        }
        workspace.getChildren().add(wire);


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

    public void eraseWire(Wire wire) {
        workspace.getChildren().remove(wire);
    }
}