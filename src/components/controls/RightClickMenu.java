package components.controls;

import components.infrastructure.ComponentGroup;
import components.infrastructure.ComponentRegistry;
import components.parts.Component;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.util.ArrayList;

/**
 * Created by minttu on 13/01/2016.
 */
public class RightClickMenu extends ContextMenu {
    Component clickedComponent;
    ArrayList<Menu> options;

    public RightClickMenu(Component clicked) {
        clickedComponent = clicked;
        addOptions();
    }
    private void addOptions() {
        MenuItem item1 = new MenuItem("Edit");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("Edit");
            }
        });
        MenuItem item2 = new MenuItem("Delete");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("Delete " + clickedComponent.thisId);
                ComponentRegistry.getInstance().deleteComponent(clickedComponent.thisId);
            }
        });


        /*final TextField textField = new TextField("Type Something");
        textField.setContextMenu(this);*/
        this.getItems().addAll(item1,item2);

        //TODO: custom options
        // addMenus(clicked.getParentComponent().customOptions);
    }
}
