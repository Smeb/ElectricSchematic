package Controllers;

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

    private WireController() {
    }

    public void setActive(){active = true;}
    public void setDormant(){active = false;}
    public void setCoordinatePair(CoordinatePair coordinates){this.coordinates = coordinates;}
}
