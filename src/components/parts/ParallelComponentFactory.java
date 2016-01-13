package components.parts;

import components.infrastructure.ComponentGroup;
import components.infrastructure.ComponentGroupFactory;

/**
 * Created by archie on 13/01/16.
 */
public class ParallelComponentFactory {
    ParallelComponentFactory instance = new ParallelComponentFactory();
    private ParallelComponentFactory(){}

    public ParallelComponentFactory getInstance(){
        return instance;
    }

    public ParallelComponent newParallelComponent(Class... classType){
        ComponentGroupFactory factory = ComponentGroupFactory.getInstance();
        ParallelComponent parallelComponent = new ParallelComponent();
        ComponentGroup current;
        for(Class c : classType){
            if(!c.isAssignableFrom(Component.class)){
                return null;
            }
            current = factory.buildComponentGroup(c);
        }
    }
}
