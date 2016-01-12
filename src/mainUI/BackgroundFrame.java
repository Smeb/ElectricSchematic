package mainUI;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * Created by carlahyenne on 12/01/2016.
 */
public class BackgroundFrame {

    double originSceneX, originSceneY, originSceneTranslateX, originSceneTranslateY;

    public BackgroundFrame(){
        Rectangle r = new Rectangle();
        r.setX(10);
        r.setY(10);
        r.setWidth(10);
        r.setHeight(10);

        //BACKGROUND FRAME

        final VBox backgroundFrame = new VBox();

        backgroundFrame.setAlignment(Pos.CENTER);
        backgroundFrame.setStyle("-fx-background-color: pink;");
        backgroundFrame.setMinSize(10000,10000);
        backgroundFrame.getChildren().add(r);


        backgroundFrame.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                backgroundFrame.startFullDrag();
            }
        });
        backgroundFrame.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                originSceneX = event.getSceneX();
                originSceneY = event.getSceneY();
                originSceneTranslateX = ((VBox) event.getSource()).getTranslateX();
                originSceneTranslateY = ((VBox) event.getSource()).getTranslateY();
            }
        });
        backgroundFrame.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double offsetX = event.getSceneX() - originSceneX;
                double offsetY = event.getSceneY() - originSceneY;
                double newTranslateX = originSceneTranslateX + offsetX;
                double newTranslateY = originSceneTranslateY + offsetY;

                ((VBox)(event.getSource())).setTranslateX(newTranslateX);
                ((VBox)(event.getSource())).setTranslateY(newTranslateY);
            }
        });
    }
}
