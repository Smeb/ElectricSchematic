package components.controls;

import components.infrastructure.ComponentRegistry;
import components.parts.Component;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
        /*MenuItem item1 = new MenuItem("Edit");
        item1.getParentMenu().setOnAction(event ->
            EditMenuFactory.getInstance().buildEditMenu(clickedComponent,event)
        );

        MenuItem item2 = new MenuItem("Delete");
        item2.getParentMenu().setOnAction(event ->
            ComponentRegistry.getInstance().deleteComponent(clickedComponent.thisId)
        );*/
        Menu item1 = new Menu("Edit");
        EditMenuItem property1 = new EditMenuItem("Resistance",0,100,40);
        EditMenuItem property2 = new EditMenuItem("Voltage",0,20,5);
        property1.setHideOnClick(false);
        property2.setHideOnClick(false);

        property1.setOnAction(editEvent -> {
            System.out.println("Editing resistance");
            //item1.getItems().add(setting);
            //item1.show();
        });
        property1.setOnAction(editEvent -> {
            System.out.println("Editing voltage");
            //item1.getItems().add(setting);
            //item1.show();
        });
        /*item1.setOnAction(event ->
            EditMenuFactory.getInstance().buildEditMenu(clickedComponent,event)
        );*/
        item1.setOnAction(event -> {
            System.out.println("got this far");
            item1.getItems().addAll(property1,property2);
            item1.show();
        });
        //item1.addEventHandler(MouseEvent.MOUSE_ENTERED);

        MenuItem item2 = new MenuItem("Delete");
        item2.setOnAction(event -> ComponentRegistry.getInstance().deleteComponent(clickedComponent.thisId));
        this.getItems().addAll(item1,item2);

        //TODO: custom options
        // addMenus(clicked.getParentComponent().customOptions);
    }
}
