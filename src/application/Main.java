package application;

import IO.Reader;
import components.infrastructure.ComponentGroupFactory;
import components.parts.Battery;
import components.parts.ComponentFactory;
import components.parts.Lamp;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import mainUI.*;
import controllers.WireController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;
import palette.Palette;

import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {
    private Palette createPalette() {
        ArrayList<Class> tools = new ArrayList<>();
        tools.add(Lamp.class);
        tools.add(Battery.class);
        return new Palette(30,50,200,40,4,tools);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group workspace = new Group();
        VBox outerFrame = new VBox();
        ComponentGroupFactory.setWorkspace(workspace);
        ComponentFactory.setWorkspace(workspace);
        WireController.setWorkspace(workspace);
        /*
        for(int i = 0; i < 10; i++){
            Line line = new Line(i * 100, 0, i * 100, 700);
            workspace.getChildren().add(line);
        }

        */
        Palette pal = createPalette();
        MenuBar menuBar = new TopMenu().makeMenu();

        workspace.getChildren().addAll(pal);
        outerFrame.getChildren().addAll(menuBar,workspace);
        Scene programScene = new Scene(outerFrame, 1000, 700);
        programScene.setOnMouseDragExited(event -> {
            WireController wireController = WireController.getInstance();
            if(wireController.active()) {
                wireController.setDormant();
            }
        });
        primaryStage.setTitle("Electric Schematic");
        primaryStage.setScene(programScene);
        primaryStage.show();

        URL url = getClass().getResource("test.txt");
        System.out.println(url.getPath());
        JSONObject object = Reader.getInstance().read(url.getPath().replace("%20", " "));
        System.out.println(object.getJSONArray("components").getJSONObject(0).get("id"));
    }

}
