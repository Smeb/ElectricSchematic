package Controllers;

import components.Anchor;
import javafx.scene.Group;
import tools.Wire;

public class WireController {

    private Group workspace;
    private static WireController instance;
    private boolean active = false;
    private Anchor parent;

    public static WireController getInstance() {
        if (instance == null) {
            instance = new WireController();
        }
        return instance;
    }

    private WireController() {
    }

    public void setWorkspace(Group workspace){
        this.workspace = workspace;
    }

    public void addWire(Anchor start, Anchor end)
    {
        Wire aWire = new Wire(start, end);
        workspace.getChildren().add(aWire);
    }


    public boolean active(){return active;}
    public void setActive(){active = true;}
    public void setDormant(){active = false;}
    public void setParent(Anchor start){this.parent = start;
        System.out.println(start.getPosition().toString());}
    public void completeWire(Anchor end){
        System.out.println(end.getPosition().toString());
        //TODO : Add application code to manage wire creation
    }
}
