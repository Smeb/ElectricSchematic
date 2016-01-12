package components;

import datastructures.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.ComponentColorMap;

public class ComponentGroupFactory {

    private static final ComponentGroupFactory instance = new ComponentGroupFactory();
    private static Group workspace;

    public static ComponentGroupFactory getInstance() {
        return instance;
    }


    public static void setWorkspace(Group group){
        workspace = group;
    }

    public Group buildComponentGroup(Class classType, double posX, double posY){
        Group group = null;
        if(Component.class.isAssignableFrom(classType)) { // == Component.class){
            group = new Group();
            Rectangle rectangle = new Rectangle(posX, posY);
            rectangle.setFill(ComponentColorMap.getInstance().getColor(classType));
            rectangle.setStroke(Color.BLACK);
            group.getChildren().add(rectangle);
            enableDrag(group);
            addAnchors(group, Orientation.LEFT, Orientation.RIGHT);
            workspace.getChildren().add(group);
            return group;
        }

        /* Fail condition, currently returning null
        TODO: Add enum to limit possible group types
        */

        return group;
    }

    private void addAnchors(Group group, Orientation... orientations){
        AnchorFactory factory = AnchorFactory.getInstance();
        for(Orientation o : orientations) {
            factory.addAnchor(group, o);
        }
    }

    private void enableDrag(Group group){
        final DragContext dragContext = new DragContext();

        group.setOnMousePressed((event)->{
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
