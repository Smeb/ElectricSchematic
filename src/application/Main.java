package application;

import components.infrastructure.ComponentViewFactory;
import components.infrastructure.ParallelComponent;
import components.parts.*;
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

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Palette createPalette() {
        ArrayList<Component> tools = new ArrayList<>();
        tools.add(new Lamp());
        tools.add(new Battery());
        tools.add(new Resistor());
        tools.add(new ParallelComponent());
        tools.add(new ParallelComponent());
        tools.add(new ParallelComponent());
        tools.add(new ParallelComponent());
        tools.add(new Ammeter());
        tools.add(new Voltmeter());
        int toolsPerRow = 3;
        int iconSize = 50;
        return new Palette(30,50,iconSize,toolsPerRow,tools);
    }

    public Scene initScene(Stage primaryStage){
        primaryStage.setTitle("Electric Schematic");
        Group workspace = new Group();
        VBox outerFrame = new VBox();
        ComponentViewFactory.setWorkspace(workspace);
        ComponentFactory.setWorkspace(workspace);
        WireController.setWorkspace(workspace);

        Palette pal = createPalette();
        MenuBar menuBar = new TopMenu().makeMenu(primaryStage);
        Button button = new Button("Evaluate");

        button.setOnAction(event -> new Evaluator().evaluate());

        Button pictures = new Button("Pictures");
        pictures.setLayoutX(100);
        pictures.setOnAction(event -> {
            if (Globals.schematicIcons) { pictures.setText("Pictures"); }
            else { pictures.setText("Schematic"); }
            new Controller().changePictures(pal);
        });
        workspace.getChildren().addAll(pal,button,pictures);
        outerFrame.getChildren().addAll(menuBar,workspace);
        Scene programScene = new Scene(outerFrame, 1000, 700);
        programScene.setOnMouseDragExited(event -> {
            WireController wireController = WireController.getInstance();
            if(wireController.active()) {
                wireController.setDormant();
            }
        });
        return programScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{



        primaryStage.setScene(initScene(primaryStage));
        if(!Globals.testMode){
            primaryStage.show();
        }


        /*
        URL url = getClass().getResource("test.txt");
        System.out.println(url.getPath());

        JSONObject object = Reader.getInstance().read(url.getPath().replace("%20", " "));
        JSONObject test = object.getJSONArray("components").getJSONObject(0);
        Loader.getInstance().load(object.getJSONArray("components"));
        */

    }

}
