package evaluation;

import application.Globals;
import application.Main;
import components.infrastructure.ComponentRegistry;
import components.infrastructure.ComponentViewFactory;
import static org.junit.Assert.assertEquals;
import components.parts.*;
import datastructures.ComponentValueMap;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by archie on 14/01/16.
 */
public class FactoryTest extends Main {
    @Before
    public void initialize(){
        Globals.testMode = true;
        ComponentValueMap.getInstance();
    }

    @Test
    public void componentRegistryPatternCheck(){
        ComponentRegistry registryA = ComponentRegistry.getInstance();
        ComponentRegistry registryB = ComponentRegistry.getInstance();
        assert(registryA.equals(registryB));
    }

    @Test
    public void parallelComponentViewFactoryPatternCheck(){
        ParallelComponentViewFactory factoryA = ParallelComponentViewFactory.getInstance();
        ParallelComponentViewFactory factoryB = ParallelComponentViewFactory.getInstance();
        assert(factoryA.equals(factoryB));
    }

    @Test
    public void componentViewFactoryPatternCheck(){
        ComponentViewFactory factoryA = ComponentViewFactory.getInstance();
        ComponentViewFactory factoryB = ComponentViewFactory.getInstance();
        assert(factoryA.equals(factoryB));
    }

    @Test
    public void componentFactoryPatternCheck(){
        ComponentFactory factoryA = ComponentFactory.getInstance();
        ComponentFactory factoryB = ComponentFactory.getInstance();
        assert(factoryA.equals(factoryB));
    }

    @Test
    public void registryGrowsOnCreateLampComponent(){
        Integer before = ComponentRegistry.getInstance().getComponents().size();
        ComponentFactory.getInstance().newComponent(Lamp.class, 0.0, 0.0, true);
        before += 1;
        Integer after = ComponentRegistry.getInstance().getComponents().size();
        assertEquals("after must be before + 1", before, after);
    }

    @Test
    public void registryGrowsOnCreateBatteryComponent(){
        Integer before = ComponentRegistry.getInstance().getComponents().size();
        ComponentFactory.getInstance().newComponent(Battery.class, 0.0, 0.0, true);
        before += 1;
        Integer after = ComponentRegistry.getInstance().getComponents().size();
        assertEquals("after must be before + 1", before, after);
    }

    @Test
    public void registryGrowsOnCreateResistorComponent(){
        Integer before = ComponentRegistry.getInstance().getComponents().size();
        ComponentFactory.getInstance().newComponent(Resistor.class, 0.0, 0.0, true);
        before += 1;
        Integer after = ComponentRegistry.getInstance().getComponents().size();
        assertEquals("after must be before + 1", before, after);
    }
}
