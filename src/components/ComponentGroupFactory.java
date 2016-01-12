package components;

import datastructures.ComponentGroup;
import datastructures.CoordinatePair;
import datastructures.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ComponentGroupFactory {

    private static final ComponentGroupFactory instance = new ComponentGroupFactory();
    private static Group workspace;

    public static ComponentGroupFactory getInstance() {
        return instance;
    }

    public static void setWorkspace(Group group){
        workspace = group;
    }

    public ComponentGroup buildComponentGroup(Class classType, double posX, double posY){
        ComponentGroup group = null;
        if(classType == Component.class){
            group = new ComponentGroup();
            Rectangle rectangle = new Rectangle(posX, posY);
            rectangle.setFill(Color.TRANSPARENT);
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

    private void enableDrag(ComponentGroup group){
        final CoordinatePair dragContext = new CoordinatePair();

        group.setOnMousePressed((event)->{
            // Centers the group onto the mouse
            group.toFront();
            dragContext.setXY(group.getLayoutX() - event.getSceneX(),
                    group.getLayoutY() - event.getSceneY());
            group.setCursor(Cursor.CLOSED_HAND);
        });

        group.setOnMouseEntered((event) -> group.setCursor(Cursor.HAND));

        // TODO: Refactor anchor wire handling coupling
        group.setOnMouseDragged((event) -> {
            group.setLayoutX(event.getSceneX() + dragContext.getX());
            group.setLayoutY(event.getSceneY() + dragContext.getY());
            for(Node n : group.getChildren()){
                if(n.getClass() == Anchor.class){
                    Anchor a = (Anchor)n;
                    a.updateWire();
                }
            }
        });

        group.setOnMouseExited((event) -> group.setCursor(Cursor.DEFAULT));
    }
}
