package Controllers;

import components.Anchor;
import javafx.scene.Group;
import tools.Wire;
import datastructures.CoordinatePair;

public class WireController {

    private static WireController instance;
    private boolean active = false;
    private CoordinatePair coordinates;

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


    public void setActive(){active = true;}
    public void setDormant(){active = false;}
    public void setCoordinatePair(CoordinatePair coordinates){this.coordinates = coordinates;}
}
