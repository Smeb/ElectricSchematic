package datastructures;

import javafx.scene.Group;
import tools.Wire;

import java.util.ArrayList;

public class ComponentGroup extends Group {

    // TODO: Decide if ComponentGroup is necessary

    ArrayList<Wire> wires ;
    public ComponentGroup(){
        wires = new ArrayList<>();
    }

    public void addWire(Wire wire){
        wires.add(wire);
    }
}
