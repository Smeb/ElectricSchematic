package datastructures;

import application.Globals;
import components.infrastructure.ParallelComponent;
import components.parts.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.HashMap;

public class ComponentValueMap {
    private static ComponentValueMap instance = new ComponentValueMap();
    private HashMap<Class, DefaultComponentValues> valueMap;

    private ComponentValueMap(){
        valueMap = new HashMap<>();
        if(!Globals.testMode) {
            valueMap.put(Lamp.class, new DefaultComponentValues(
                    80.0,
                    80.0,
                    new ImagePattern(new Image("file:img/lamp-colour.png")),
                    new ImagePattern(new Image("file:img/lamp-icon.png")),
                    "Lamp"
            ));

            valueMap.put(Battery.class, new DefaultComponentValues(
                    50.0,
                    80.0,
                    new ImagePattern(new Image("file:img/battery-colour.png")),
                    new ImagePattern(new Image("file:img/battery-icon.png")),
                    "Battery"
            ));

        valueMap.put(Resistor.class, new DefaultComponentValues(
                30.0,
                80.0,
                new ImagePattern(new Image("file:img/resistor-colour.png")),
                new ImagePattern(new Image("file:img/resistor-icon.png")),
                "Resistor"
        ));
        valueMap.put(Ammeter.class, new DefaultComponentValues(
                60.0,
                60.0,
                new ImagePattern(new Image("file:img/ammeter-icon.png")),
                new ImagePattern(new Image("file:img/ammeter-icon.png")),
                "Ammeter"
        ));
        valueMap.put(Voltmeter.class, new DefaultComponentValues(
                60.0,
                60.0,
                new ImagePattern(new Image("file:img/voltmeter-icon.png")),
                new ImagePattern(new Image("file:img/voltmeter-icon.png")),
                "Voltmeter"
        ));

            valueMap.put(ParallelComponent.class, new DefaultComponentValues(
                    0.0,
                    0.0,
                    new ImagePattern(new Image("file:img/battery-colour.png")),
                    new ImagePattern(new Image("file:img/battery-icon.png")),
                    "ParallelComponent"
            ));
        }
    }

    public static ComponentValueMap getInstance(){
        return instance;
    }

    public void put(Class c, DefaultComponentValues v){
        valueMap.put(c, v);
    }

    public DefaultComponentValues get(Class c){
        return valueMap.get(c);
    }
}
