package mainUI;

import com.sun.deploy.uitoolkit.DragContext;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by carlahyenne on 12/01/2016.
 */
public class BackgroundFrame {

//    double originSceneX, originSceneY, originSceneTranslateX, originSceneTranslateY;

    public Group makeFrame(){
        Rectangle r = new Rectangle();
        r.setWidth(1000);
        r.setHeight(1000);
        r.prefHeight(10000);
        r.prefWidth(10000);
        r.setFill(Color.WHITE);

        Group backgroundFrame = new Group();

       // backgroundFrame.setAlignment(Pos.CENTER);
//        backgroundFrame.setStyle("-fx-background-color: pink;");
        backgroundFrame.isResizable();
       // backgroundFrame.setMinSize(10000,10000);
        backgroundFrame.getChildren().add(r);

        enableDrag(backgroundFrame);
        return backgroundFrame;
    }

    private void enableDrag(Group group) {
        final DragContext dragContext = new DragContext();

        group.setOnMousePressed((event) -> {
            // Centers the group onto the mouse
            group.toFront();
            dragContext.deltaX = group.getLayoutX() - event.getSceneX();
            dragContext.deltaY = group.getLayoutY() - event.getSceneY();
            group.setCursor(Cursor.CLOSED_HAND);
        });

        group.setOnMouseEntered((event) -> group.setCursor(Cursor.HAND));

        group.setOnMouseDragged((event) -> {
            group.setLayoutX(event.getSceneX() + dragContext.deltaX);
            group.setLayoutY(event.getSceneY() + dragContext.deltaY);
        });

        group.setOnMouseExited((event) -> group.setCursor(Cursor.DEFAULT));
    }

    private static final class DragContext {
        public double deltaX;
        public double deltaY;
    }
}