package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Hello World");
        Palette pal = createPalette();
        //pal.setX(100);
        //pal.setY(200);
        root.getChildren().add(pal);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
