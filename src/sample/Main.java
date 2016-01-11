package sample;

import components.Component;
import components.ComponentFactory;
import components.ComponentGroupFactory;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        ComponentGroupFactory.setWorkspace(root);
        ComponentFactory.setWorkspace(root);

        Button button = new Button("New component");
        button.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(Component.class, 50, 50));
        root.getChildren().addAll(button);
        primaryStage.setTitle("Electric Schematic");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }


}
