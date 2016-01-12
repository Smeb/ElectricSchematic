package Controllers;

import components.Anchor;
import javafx.scene.Group;
import tools.Wire;

public class WireController {

    private static WireController instance;
    private Wire wire = null;

    public static WireController getInstance() {
        if (instance == null) {
            instance = new WireController();
        }
        return instance;
    }

    public WireController(Wire wire){
        this.wire = wire;
    }

    public WireController(){
        wire = null;
    }

    public void addWire(Group root, Anchor start, Anchor end)
    {
        Wire aWire = new Wire(start, end);
        aWire.setStrokeWidth(2);
        root.getChildren().add(aWire);
    }

}
