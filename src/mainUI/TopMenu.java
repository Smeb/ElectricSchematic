package mainUI;

import IO.Loader;
import IO.Reader;
import components.infrastructure.ComponentRegistry;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by carlahyenne on 12/01/2016.
 */
public class TopMenu {

    public MenuBar makeMenu(Stage primaryStage) {

        //MENU MenuBar > Menus > MenuItems
        final FileChooser fileChooser = new FileChooser();
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
                System.out.println("Pretty pegasus");
                ComponentRegistry.getInstance().deleteAll();
            }
        });

        openLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Powerful platypus");
                File file = fileChooser.showOpenDialog(primaryStage);
                System.out.println(file.toString());
                JSONObject object = Reader.getInstance().read(file.toString());
                try{Loader.getInstance().load(object.getJSONArray("components"));}
                catch(JSONException e){System.err.println(e);}
            }
        });

        saveLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Partying portico");
            }
        });

        menuBar.getMenus().addAll(menuNew, menuOpen, menuSave);
        return menuBar;
    }

}
