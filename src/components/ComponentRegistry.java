package components;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComponentRegistry {
    private ArrayList<Component> components;

    private static ComponentRegistry instance = null;
    public static ComponentRegistry getInstance(){
        if(instance == null)
            instance = new ComponentRegistry();
        return instance;
    }

    private ComponentRegistry(){
        components = new ArrayList<Component>();
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public ArrayList<Component> getComponents(){
        return (ArrayList<Component>)components.clone();
    }

}
