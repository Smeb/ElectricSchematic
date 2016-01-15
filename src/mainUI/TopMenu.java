package mainUI;

import IO.Loader;
import IO.Reader;
import IO.Writer;
import components.infrastructure.ComponentRegistry;
import components.parts.Component;
import javafx.event.EventHandler;
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
                Component.resetIDs();
            }
        });

        openLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Powerful platypus");
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file.toString() != null) {
                    ComponentRegistry.getInstance().deleteAll();
                    Component.resetIDs();
                    JSONObject object = Reader.getInstance().read(file.toString());
                    try {
                        Loader.getInstance().load(object.getJSONArray("components"));
                    } catch (JSONException e) {
                        System.err.println(e);
                    }
                }
            }
        });

        saveLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Partying portico");
                File file = fileChooser.showSaveDialog(primaryStage);
                try{Writer.getInstance().save(file.getPath());}
                catch(Exception e){}
            }
        });

        menuBar.getMenus().addAll(menuNew, menuOpen, menuSave);
        return menuBar;
    }
}
