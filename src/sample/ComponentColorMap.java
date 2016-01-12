package sample;

import components.Battery;
import components.Component;
import components.Lamp;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * Created by minttu on 12/01/2016.
 */
public class ComponentColorMap {
    private static ComponentColorMap instance = null;
    private static HashMap<Class, Color> colorMap;
    public static ComponentColorMap getInstance(){
        if(instance == null)
            instance = new ComponentColorMap();
        return instance;
    }

    private ComponentColorMap(){
        colorMap = new HashMap<>();
        colorMap.put(Lamp.class, Color.YELLOW);
        colorMap.put(Battery.class, Color.GREEN);
        colorMap.put(Component.class, Color.TRANSPARENT);
    }
    public Color getColor(Class componentType) {
        return colorMap.get(componentType);
    }
}
