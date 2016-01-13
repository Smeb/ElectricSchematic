package components.controls;

import components.infrastructure.ComponentGroup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.util.ArrayList;

/**
 * Created by minttu on 13/01/2016.
 */
public class RightClickMenu extends ContextMenu {
    ComponentGroup clickedComponent;
    ArrayList<Menu> options;

    public RightClickMenu(ComponentGroup clicked) {
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
                System.out.println("Delete");
            }
        });
        this.getItems().addAll(item1, item2);

        final TextField textField = new TextField("Type Something");
        textField.setContextMenu(this);
        //this.getMenus().addAll(menu1, menu2, menu3);
        //TODO: custom options
        // addMenus(clicked.getParentComponent().customOptions);
    }
}
