package components.controls;

import components.infrastructure.ComponentRegistry;
import components.parts.Battery;
import components.parts.Component;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

/**
 * Created by minttu on 13/01/2016.
 */
public class RightClickMenu extends ContextMenu {
    Component clickedComponent;
    ArrayList<EditMenuItem> options;

    public RightClickMenu(Component clicked) {
        clickedComponent = clicked;
        addOptions();
    }
    private void addOptions() {
        options = new ArrayList<>();
        Menu edit = new Menu("Edit");

        if (clickedComponent instanceof Battery) {
            options.add(new EditMenuItem("Voltage",0,20,5));
        }
        else {
            options.add(new EditMenuItem("Resistance", 0, 100, 40));
        }

        for (EditMenuItem em : options) {
            em.setHideOnClick(false);
        }

        edit.setOnAction(event -> {
            edit.getItems().addAll(options);
            edit.show();
        });

        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> ComponentRegistry.getInstance().deleteComponent(clickedComponent.thisId));
        this.getItems().addAll(edit,delete);
    }
}
