package application;

import Controllers.WireController;
import components.infrastructure.AnchorFactory;
import components.parts.ComponentFactory;
import components.infrastructure.ComponentGroupFactory;
import components.parts.Battery;
import components.parts.Component;
import components.parts.Lamp;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import palette.Palette;

import java.util.ArrayList;

public class Main extends Application {
    private Palette createPalette() {
        ArrayList<Class> tools = new ArrayList<>();
        tools.add(Lamp.class);
        //tools.add(Component.class);
        tools.add(Battery.class);
        return new Palette(30,50,200,40,4,tools);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group workspace = new Group();
        AnchorFactory.getInstance().setStyle(AnchorFactory.Style.MickeyMouse);
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

        workspace.getChildren().addAll(pal);
        Button button = new Button("New component");
        button.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(Component.class, 50, 50));
        workspace.getChildren().addAll(button);
        Scene programScene = new Scene(workspace, 1000, 700);
        programScene.setOnMouseDragExited(event -> {
            WireController wireController = WireController.getInstance();
            if(wireController.active()) {
                wireController.setDormant();
            }
        });

        primaryStage.setTitle("Electric Schematic");
        primaryStage.setScene(programScene);
        primaryStage.show();
    }

}
