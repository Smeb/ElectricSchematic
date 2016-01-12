package mainUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;

/**
 * Created by carlahyenne on 12/01/2016.
 */
public class TopMenu {

    public TopMenu() {

        //MENU MenuBar > Menus > MenuItems

        MenuBar menuBar = new MenuBar();

        Label newLabel = new Label("New");
        Label openLabel = new Label("Open");
        Label saveLabel = new Label("Save");

        Menu menuNew = new Menu();
        Menu menuOpen = new Menu();
        Menu menuSave = new Menu();

        menuNew.setGraphic(newLabel);
        menuOpen.setGraphic(openLabel);
        menuSave.setGraphic(saveLabel);

        newLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.print("Pretty pegasus");
            }
        });

        openLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.print("Powerful platypus");
            }
        });

        saveLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.print("Partying portico");
            }
        });

        menuBar.getMenus().addAll(menuNew, menuOpen, menuSave);
    }

}
