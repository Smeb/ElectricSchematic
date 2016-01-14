package IO;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentRegistry;
import components.parts.Component;
import javafx.scene.Node;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by henrymortimer on 14/01/2016.
 */
public class Writer
{
    private static Writer instance = null;
    private ArrayList<Component> components = ComponentRegistry.getInstance().getComponents();

    protected Writer() {
        // Exists only to defeat instantiation.
    }
    public static Writer getInstance() {
        if(instance == null) {
            instance = new Writer();
        }
        return instance;
    }

    private ArrayList<Anchor> getComponentAnchors(Component c)
    {
        ArrayList<Anchor> returnValues = new ArrayList<>();
        for(Node n :c.getGroup().getChildren()){
            if(n instanceof Anchor){
                if(((Anchor) n).getDirection() == Anchor.Direction.start)
                    returnValues.add((Anchor)n);
            }
        }
        return returnValues;
    }


    private JSONObject constructJSON(Component c) throws JSONException
    {
        JSONObject object = new JSONObject();
        object.put("id",c.thisId);
        object.put("type", c.getClass().toString().substring(23));
        object.put("xPos", c.getGroup().getLayoutX());
        object.put("yPos", c.getGroup().getLayoutY());
        object.put("layout", "vertical");

        JSONArray connections = new JSONArray();
        ArrayList<Anchor> startAnchors = getComponentAnchors(c);
        for(Anchor anchor:startAnchors)
        {
            int id = anchor.getWire().getEndAnchor().getParentComponent().thisId;
            System.out.println(id);
            connections.put(id);
        }

        object.put("connections", connections);

        return object;
    }

    public void save(String filePath) throws JSONException
    {
        JSONArray componentsToSave = new JSONArray();
        for(Component c:components)
            componentsToSave.put(constructJSON(c));
        JSONObject obj = new JSONObject();
        obj.put("components", componentsToSave);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }
        catch(IOException e){}
    }


}
