package components.infrastructure;

import components.parts.Battery;
import components.parts.Component;
import components.parts.Lamp;
import datastructures.ComponentGroup;
import datastructures.CoordinatePair;
import datastructures.Orientation;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import palette.PaletteIcon;

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
        ComponentGroup componentGroup;
        if(Component.class.isAssignableFrom(classType)) {
            componentGroup = new ComponentGroup();
            if (classType == Lamp.class) {
                Rectangle rectangle = new Rectangle(Lamp.width, Lamp.height);
                rectangle.setFill(Lamp.iconColor);
                rectangle.setStroke(Component.OUTLINE);
                componentGroup.getChildren().add(rectangle);

            } else if (classType == Battery.class) {
                Rectangle rectangle = new Rectangle(Battery.width, Battery.height);
                rectangle.setFill(Battery.iconColor);
                rectangle.setStroke(Component.OUTLINE);
                componentGroup.getChildren().add(rectangle);
            }
            enableDrag(componentGroup);
            addAnchors(componentGroup, Orientation.LEFT, Orientation.RIGHT);
            componentGroup.setLayoutX(posX);
            componentGroup.setLayoutY(posY + PaletteIcon.size / 2);
            workspace.getChildren().add(componentGroup);
            return componentGroup;
        }
        /* Fail condition, currently returning null
        TODO: Add enum to limit possible group types
        */

        return null;
    }

    private void addAnchors(Group group, Orientation... orientations){
        AnchorFactory factory = AnchorFactory.getInstance();
        Bounds bounds = group.getBoundsInParent();
        for(Orientation o : orientations) {
            factory.addAnchor(group, o, bounds.getMaxX(), bounds.getMaxY(), bounds.getMinX());
        }
    }

    private void enableDrag(ComponentGroup group){
        final CoordinatePair dragContext = new CoordinatePair();

        group.setOnMousePressed((event)->{
            // Centers the group onto the mouse
            group.toFront();
            for(Node n : group.getChildren()){
                if(n instanceof Anchor){
                    Anchor a = (Anchor)n;
                    a.updateWire();
                }
            }
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

    private static final class DragContext {
        public double deltaX;
        public double deltaY;
    }
}
