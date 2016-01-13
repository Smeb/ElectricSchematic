package datastructures;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by henrymortimer on 13/01/2016.
 */
public class ComponentConnections
{
    private int componentID;
    private ArrayList<Integer> connectedComponents;

    public ComponentConnections(int id, ArrayList<Integer> connections)
    {
        componentID = id;
        connectedComponents = connections;
    }

    public int getID(){return componentID;}

    public ArrayList<Integer> getConnectedComponents() {return connectedComponents;}
}
