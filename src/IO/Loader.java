package IO;

import components.infrastructure.Anchor;
import components.parts.Battery;
import components.parts.Component;
import components.parts.ComponentFactory;
import components.parts.Lamp;
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
        String type = null;
        double x = 0.0,y = 0.0;
        ArrayList<Integer> connections;
        int id;
        try
        {
            type = component.get("type").toString();
            x = Double.parseDouble(component.getString("xPos"));
            y = Double.parseDouble(component.getString("yPos"));
            connections = parseConnectionsArray(component.getJSONArray("connections"));
            id = Integer.parseInt(component.getString("id"));
            allConnections.add(new ComponentConnections(id, connections));
            Class componentClass = parseClass(type);
            // TODO: Temp false value needs to be checked against parallel component type
            Component componentObj = ComponentFactory.getInstance().newComponent(componentClass, x, y, false);
            for(Node n :componentObj.getGroup().getChildren()){
                if(n instanceof Anchor){

                }
            }
        }
        catch(Exception e)
        {
            System.err.print(e);
        }
    }

    public void loadWires()
    {
        for(ComponentConnections c: allConnections)
        {

        }
    }


    public void loadComponents(JSONArray components)
    {
        for(int i = 0; i < components.length(); i ++)
        {
            try{loadComponent(components.getJSONObject(i));}
            catch(Exception e){System.err.print(e);}
        }
    }
}
