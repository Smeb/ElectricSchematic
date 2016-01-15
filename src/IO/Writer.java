package IO;

import components.infrastructure.Anchor;
import components.infrastructure.ComponentRegistry;
import components.parts.Component;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Pipe;
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
        int a = 20;
        ArrayList<Anchor> returnValues = new ArrayList<>();
        for(Node n :c.getGroup().getChildren()){
            System.out.println(a++);
            if(n instanceof Anchor){
                if(((Anchor) n).getWire().getParentAnchor() == n)
                    returnValues.add((Anchor)n);
            }
        }
        System.out.println(returnValues.toString());
        return returnValues;
    }


    private JSONObject constructJSON(Component c) throws JSONException
    {
        JSONObject object = new JSONObject();
        System.out.println("\nJSON Object: " + object);
        object.put("id",c.thisId);
        System.out.println("\nJSON Object: " + object);
        object.put("type", c.getClass().toString().substring(23));
        System.out.println("\nJSON Object: " + object);
        object.put("xPos", c.getGroup().getLayoutX());
        System.out.println("\nJSON Object: " + object);
        object.put("yPos", c.getGroup().getLayoutY());
        System.out.println("\nJSON Object: " + object);


        JSONArray connections = new JSONArray();
        ArrayList<Anchor> startAnchors = getComponentAnchors(c);
        int a = 10;
        for(Anchor anchor:startAnchors)
        {
            System.out.println(a++);

            int id = anchor.getWire().getEndAnchor().getParentComponent().thisId;
            System.out.println(id);
            connections.put(id);

        }
        System.out.println(object.get("id").toString());

        object.put("connections", connections);
        return object;
    }

    public void save(String filePath) throws JSONException
    {
        System.out.println(1);
        JSONArray componentsToSave = new JSONArray();
        for(Component c:components)
        {
            System.out.println("Saving component " + c);
            componentsToSave.put(constructJSON(c));

        }
        System.out.println(2);
        JSONObject obj = new JSONObject();
        obj.put("components", componentsToSave);
        System.out.println(3);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }
        catch(IOException e){System.err.println(e);}
        System.out.println(4);
    }


}
