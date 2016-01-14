package IO;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentRegistry;
import components.parts.*;
import controllers.WireController;
import datastructures.ComponentConnections;
import javafx.scene.Node;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class Loader
{
    private static Loader instance = null;
    private ArrayList<ComponentConnections> allConnections = new ArrayList<>();
    protected Loader() {}

    public static Loader getInstance()
    {
        if(instance == null)
            instance = new Loader();
        return instance;
    }

    public Class parseClass(String type)
    {
        switch (type) {
            case "Lamp":
                return Lamp.class;
            case "Battery":
                return Battery.class;
            case "Resistor":
                return Resistor.class;
            default:
                return null;
        }
    }
    private ArrayList<Integer> parseConnectionsArray(JSONArray connections)
    {
        String connectionsString = connections.toString();
        connectionsString = connectionsString.replace("[","");
        connectionsString = connectionsString.replace("]","");
        if(connectionsString.isEmpty())
            return null;
        String[] connectionsStringList = connectionsString.split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for(String s:connectionsStringList) {
            System.out.println("adding:" + s);
            a.add(Integer.parseInt(s));
        }
        if(a == null)
            System.out.println("Null pointer returned");
        return a;
    }

    public void loadComponent(JSONObject component)
    {
        String type;
        double x,y;
        ArrayList<Integer> connections;
        int id;
        try
        {
            type = component.get("type").toString();
            x = Double.parseDouble(component.get("xPos").toString());
            y = Double.parseDouble(component.get("yPos").toString());
            connections = parseConnectionsArray(component.getJSONArray("connections"));
            id = Integer.parseInt(component.get("id").toString());
            allConnections.add(new ComponentConnections(id, connections));
            Class componentClass = parseClass(type);
            ComponentFactory.getInstance().newComponent(componentClass, x, y, false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private Component getLoadedComponentById(int id)
    {
        System.out.println("Trying to find component: " + id);
        ArrayList<Component> loadedComponents = ComponentRegistry.getInstance().getComponents();

        for(Component component: loadedComponents)
        {
            if(component.thisId == id)
                return component;
        }
        System.err.println("Error no component found");
        return null;
    }

    private Anchor getComponentAnchor(Component c)
    {
        for(Node n : c.getGroup().getChildren()){
            if(n instanceof Anchor){
                if(((Anchor) n).getWire() == null)
                    return (Anchor)n;
            }
        }
        System.err.println("Error no free anchors");
        return null;
    }

    private void loadWire(ComponentConnections c)
    {
        Component componentObject = getLoadedComponentById(c.getID());
        System.out.println("Loading wires for "+c.getID());
        if(c.getConnectedComponents() == null)
            return;
        Component currentConnection;
        Anchor start;
        Anchor end;
        WireController wireController = WireController.getInstance();
        for(int id: c.getConnectedComponents())
        {
            start = getComponentAnchor(componentObject);
            wireController.setStart(start);
            currentConnection = getLoadedComponentById(id);
            end = getComponentAnchor(currentConnection);
            wireController.completeWire(end);
        }
    }
    public void loadAllWires()
    {
        for(ComponentConnections c: allConnections)
        {
            loadWire(c);
        }
    }


    public void loadAllComponents(JSONArray components)
    {
        for(int i = 0; i < components.length(); i ++)
        {
            try{loadComponent(components.getJSONObject(i));}
            catch(Exception e){System.err.print(e);}
        }
    }

    public void load(JSONArray components)
    {
        loadAllComponents(components);
        loadAllWires();
    }
}