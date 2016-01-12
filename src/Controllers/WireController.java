package Controllers;

import datastructures.CoordinatePair;

public class WireController {

    private static WireController instance;
    private boolean active = false;
    private CoordinatePair parent;

    public static WireController getInstance() {
        if (instance == null) {
            instance = new WireController();
        }
        return instance;
    }

    private WireController() {
    }

    public void addWire(Group root, Anchor start, Anchor end)
    {
        Wire aWire = new Wire(start, end);
        aWire.setStrokeWidth(2);
        root.getChildren().add(aWire);
    }


    public boolean active(){return active;}
    public void setActive(){active = true;}
    public void setDormant(){active = false;}
    public void setParent(CoordinatePair coordinates){this.parent = coordinates;
        System.out.println(coordinates.toString());}
    public void completeWire(CoordinatePair coordinates){
        System.out.println(coordinates.toString());
        //TODO : Add application code to manage wire creation
    }
}
