package topmenu;

import application.Main;
import components.infrastructure.ComponentRegistry;
import components.infrastructure.ComponentViewFactory;
import components.parts.*;
import components.wires.WireController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainUI.TopMenu;
import org.junit.Before;
import org.junit.Test;
import application.Main;

/**
 * Created by minttu on 15/01/2016.
 */
public class TopMenuTest {
    @Before
    public void before() {
        ComponentRegistry.getInstance().addComponent(new Battery());
        ComponentRegistry.getInstance().addComponent(new Lamp());
        ComponentRegistry.getInstance().addComponent(new Resistor());

    }
    @Test
    public void deleteAllCheck(){
        ComponentRegistry.getInstance().deleteAll();
        assert(ComponentRegistry.getInstance().getComponents().size() == 0);
    }

}
