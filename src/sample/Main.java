package sample;

import components.Component;
import components.ComponentFactory;
import components.ComponentGroupFactory;
import components.Lamp;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import palette.Palette;

import java.util.ArrayList;

public class Main extends Application {
    private Palette createPalette() {
        ArrayList<Class> tools = new ArrayList<Class>();
        tools.add(Lamp.class);
        /*for (int i = 0; i < 5; ++i) {
            tools.add(new Component(30,50, Color.YELLOW));
            tools.add(new Component(30,50, Color.BLUE));
            tools.add(new Component(30,50, Color.GREEN));
        }*/
        Palette pal = new Palette(30,50,200,40,4,tools);
        return pal;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        ComponentGroupFactory.setWorkspace(root);
        ComponentFactory.setWorkspace(root);

        Palette pal = createPalette();
        Button button = new Button("New component");
        button.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(Component.class, 50, 50));
        /*Button lampButton = new Button("New lamp");
        lampButton.setLayoutX(150);
        lampButton.setLayoutY(0);
        lampButton.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(Lamp.class, 150, 150));
        root.getChildren().addAll(lampButton);
        */root.getChildren().addAll(button);
        root.getChildren().addAll(pal);
        primaryStage.setTitle("Electric Schematic");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }


}
