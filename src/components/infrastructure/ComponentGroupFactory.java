package components.infrastructure;

import components.controls.RightClickMenuFactory;
import components.parts.Battery;
import components.parts.Component;
import components.parts.Lamp;
import datastructures.CoordinatePair;
import datastructures.Orientation;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import palette.PaletteIcon;

public class ComponentGroupFactory {

    private static final ComponentGroupFactory instance = new ComponentGroupFactory();
    private static Group workspace;
    private static boolean schematicIcons = true;

    public static ComponentGroupFactory getInstance() {
        return instance;
    }

    public static void setWorkspace(Group group){
        workspace = group;
    }

    public ComponentGroup buildComponentGroup(Component component, double posX, double posY) {
        ComponentGroup componentGroup = new ComponentGroup();
        componentGroup.setParentComponent(component);
        if (component instanceof Lamp) {
            Rectangle rectangle = new Rectangle(Lamp.width, Lamp.height);
            if (schematicIcons) {
                rectangle.setFill(Lamp.schematic);
            } else {
                rectangle.setFill(Lamp.iconColor);
            }
            rectangle.setStroke(Component.OUTLINE);
            componentGroup.getChildren().add(rectangle);
            component.setIcon(rectangle);

        } else if (component instanceof Battery) {
            Rectangle rectangle = new Rectangle(Battery.width, Battery.height);
            if (schematicIcons) {
                rectangle.setFill(Battery.schematic);
            } else {
                rectangle.setFill(Battery.iconColor);
            }            rectangle.setStroke(Component.OUTLINE);
            componentGroup.getChildren().add(rectangle);
            component.setIcon(rectangle);
        } else {
            return null;
        }
        enableDrag(componentGroup);
        enableRightClick(componentGroup);
        addAnchors(componentGroup, Orientation.LEFT, Orientation.RIGHT);
        componentGroup.setLayoutX(posX);
        componentGroup.setLayoutY(posY + PaletteIcon.size / 2);
        workspace.getChildren().add(componentGroup);
        return componentGroup;

        /* Fail condition, currently returning null
        TODO: Add enum to limit possible group types
        */
    }

    private void addAnchors(ComponentGroup group, Orientation... orientations){
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

    private void enableRightClick(ComponentGroup group) {
        group.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                group.setCursor(Cursor.HAND);
                RightClickMenuFactory.getInstance().buildRightClickMenu(group,event);
            }
        });
    }

    private static final class DragContext {
        public double deltaX;
        public double deltaY;
    }
}
