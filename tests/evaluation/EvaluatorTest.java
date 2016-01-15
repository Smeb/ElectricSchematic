package evaluation;

import application.Globals;
import components.parts.Battery;
import components.parts.Lamp;
import components.parts.Resistor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by archie on 13/01/16.
 */
public class EvaluatorTest {
    private Battery battery;
    private Lamp lamp;
    private Resistor resistor;
    @Before
    public void before(){
        battery = new Battery();
        lamp = new Lamp();
        resistor = new Resistor();
    }

    @Test
    public void testConnectedBatteryToLamp() {
        Globals.testMode = true;
        battery.addPositiveComponent(lamp);
        lamp.addConnectedComponent(battery);
        new Evaluator().evaluate();
        assertEquals("Battery and Lamp voltage should match", battery.getCurrent(), lamp.getCurrent());
    }

    @Test
    public void testConnectedBatteryToResistor() {
        Globals.testMode = true;
        battery.addPositiveComponent(lamp);
        resistor.addConnectedComponent(resistor);
        new Evaluator().evaluate();
        assertEquals("Battery and Lamp voltage should match", resistor.getCurrent(), lamp.getCurrent());
    }

    @After
    public void after(){
        battery = new Battery();
        lamp = new Lamp();
        resistor = new Resistor();
    }
}
