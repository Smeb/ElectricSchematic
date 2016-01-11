package sample;

import components.Component;
import components.ComponentFactory;
import javafx.application.Application;
<<<<<<< HEAD
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
=======
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
>>>>>>> a6b72dc3dd1dc1d1fbe0330027b8f6411cefe6cb
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private Palette createPalette() {
        ArrayList<Component> tools = new ArrayList<Component>();
        for (int i = 0; i < 5; ++i) {
            tools.add(new Component("Lamp", Color.YELLOW));
            tools.add(new Component("Transistor", Color.BLUE));
            tools.add(new Component("Battery", Color.GREEN));
        }
        Palette pal = new Palette(30,50,200,40,4,tools);
        return pal;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
<<<<<<< HEAD
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Hello World");
        Palette pal = createPalette();
        //pal.setX(100);
        //pal.setY(200);
        root.getChildren().add(pal);
        primaryStage.setScene(new Scene(root, 800, 600));
=======
        Group root = new Group();
        Button button = new Button("New component");
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                ComponentFactory.getInstance().newComponent(root, Component.class, 50, 50);
            }
        });
        root.getChildren().addAll(button);
        primaryStage.setTitle("Electric Schematic");
        primaryStage.setScene(new Scene(root, 1000, 700));
>>>>>>> a6b72dc3dd1dc1d1fbe0330027b8f6411cefe6cb
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
