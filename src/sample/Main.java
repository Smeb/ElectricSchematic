package sample;

import components.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import palette.Palette;

import java.util.ArrayList;

public class Main extends Application {
    private Palette createPalette() {
        ArrayList<Class> tools = new ArrayList<>();
        tools.add(Lamp.class);
        //tools.add(Component.class);
        tools.add(Battery.class);
        Palette pal = new Palette(30,50,200,40,4,tools);
        return pal;
    }

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

        Palette pal = createPalette();

        workspace.getChildren().addAll(pal);
        primaryStage.setTitle("Electric Schematic");
        primaryStage.setScene(new Scene(workspace, 1000, 700));
        primaryStage.show();
    }

}
