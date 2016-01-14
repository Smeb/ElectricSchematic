package IO;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;
import components.parts.ComponentFactory;
import components.parts.Lamp;
import controllers.WireController;
import datastructures.ComponentConnections;
import javafx.scene.Node;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.Wire;

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
            default:
                return null;
        }
    }
    private ArrayList<Integer> parseConnectionsArray(JSONArray connections)
    {
        String connectionsString = connections.toString();
        connectionsString = connectionsString.replace("[","");
        connectionsString = connectionsString.replace("]","");
        String[] connectionsStringList = connectionsString.split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for(String s:connectionsStringList)
            a.add(Integer.parseInt(s));
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
            System.err.print(e);
        }
    }

    private Component getLoadedComponentById(int id)
    {
        ArrayList<Component> loadedComponents = ComponentRegistry.getInstance().getComponents();
        System.out.println(id);
        for(Component component: loadedComponents)
        {
            System.out.println(component.thisId);
            if(component.thisId == id)
                return component;
        }
        System.err.println("Error no component found");
        return null;
    }

    private Anchor getComponentAnchor(Component c)
    {
        for(Node n :c.getGroup().getChildren()){
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
        Component currentConnection;
        Anchor start = getComponentAnchor(componentObject);
        Anchor end;
        WireController wireController = WireController.getInstance();
        wireController.setStart(start);
        for(int id: c.getConnectedComponents())
        {
            currentConnection = getLoadedComponentById(id);
            end = getComponentAnchor(currentConnection);
            wireController.completeWire(end);
        }
    }
    public void loadAllWires()
    {
        ArrayList<Component> loadedComponents = ComponentRegistry.getInstance().getComponents();
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