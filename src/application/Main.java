package application;

import components.infrastructure.ComponentGroupFactory;
import components.parts.Battery;
import components.parts.ComponentFactory;
import components.parts.Lamp;
import controllers.WireController;
import evaluation.Evaluator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainUI.TopMenu;
import palette.Palette;

import java.util.ArrayList;

// import org.json.JSONObject;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Palette createPalette() {
        ArrayList<Class> tools = new ArrayList<>();
        tools.add(Lamp.class);
        tools.add(Battery.class);
        return new Palette(30,50,200,40,4,tools);
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
        Button button = new Button("Evaluate");
        button.setOnAction(event -> new Evaluator().evaluate());
        workspace.getChildren().addAll(pal, button);
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


        /*
        URL url = getClass().getResource("test.txt");
        System.out.println(url.getPath());
        JSONObject object = Reader.getInstance().read(url.getPath().replace("%20", " "));
        System.out.println(object.getJSONArray("components").getJSONObject(0).get("id"));
        */
    }

}
