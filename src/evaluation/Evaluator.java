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
        int i = 0;
        if(c.getConnectedComponents() != null){
            System.out.println("Inner Loop");
            Component current = c.getConnectedComponents().getFirst();
            while(current != c){
                System.out.println(i++);
                if(c.getConnectedComponents().isEmpty()){
                    break;
                } else {
                    current = current.getConnectedComponents().getFirst();
                }
            }
        }
        System.out.println("Finished");
    }
}
