package evaluation;

import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;

import java.util.ArrayList;

/**
 * Created by archie on 12/01/16.
 */
public class Evaluator {
    ComponentRegistry registry;
    public Evaluator(){
        registry = ComponentRegistry.getInstance();
    }

    public void evaluate(){
        ArrayList<Component> registryCopy = new ArrayList<>();
        for(Component c : registry.getComponents()){
            registryCopy.add(c);
        }
        for(Component c : registryCopy){
            if(c instanceof Battery){
                evaluateGraph(c);
            }
        }
    }

    public void evaluateGraph(Component c){
        Component root = c;
        System.out.println("Evaluating");
        if(c.getNextNodes() != null){
            Component current = c.getNextNodes().getFirst();
            while(current != c){
                System.out.println(current.getClass().toString());
                current = current.getNextNodes().getFirst();
                if(c.getNextNodes().isEmpty()){
                    break;
                }
            }
        }
        System.out.println("Finished");
    }
}
