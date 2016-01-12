package sample;

import components.Component;
import components.ComponentFactory;
import components.ComponentGroupFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        // BorderPane borderBox = new BorderPane();
        Group workspace = new Group();
        // borderBox.setCenter(workspace);
        // borderBox.setPrefSize(1000, 700);
        ComponentGroupFactory.setWorkspace(workspace);
        ComponentFactory.setWorkspace(workspace);
        for(int i = 0; i < 10; i++){
            Line line = new Line(i * 100, 0, i * 100, 700);
            workspace.getChildren().add(line);
        }

        Button button = new Button("New component");
        button.setOnMouseClicked((event) -> ComponentFactory.getInstance().newComponent(Component.class, 50, 50));
        workspace.getChildren().addAll(button);
        primaryStage.setTitle("Electric Schematic");
        primaryStage.setScene(new Scene(workspace, 1000, 700));
        primaryStage.show();
    }

}
