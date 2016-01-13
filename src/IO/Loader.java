package IO;

import components.parts.Battery;
import components.parts.ComponentFactory;
import components.parts.Lamp;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by henrymortimer on 13/01/2016.
 */
public class Loader
{
    private static Loader instance = null;

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

    public void loadComponent(JSONObject component)
    {
        String type = null;
        double x = 0.0,y = 0.0;
        try
        {
            type = component.get("type").toString();
            x = Double.parseDouble(component.get("xPos").toString());
            y = Double.parseDouble(component.get("yPos").toString());

            Class componentClass = parseClass(type);
            ComponentFactory.getInstance().newComponent(componentClass, x, y);
        }
        catch(Exception e)
        {
            System.err.print(e);
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
