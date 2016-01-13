package components.infrastructure;

import components.controls.RightClickMenuFactory;
import components.parts.Component;
import datastructures.ComponentValueMap;
import datastructures.CoordinatePair;
import datastructures.DefaultComponentValues;
import datastructures.Orientation;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import palette.PaletteIcon;

public class ComponentViewFactory {

    private static final ComponentViewFactory instance = new ComponentViewFactory();
    private static final ComponentValueMap valueMap = ComponentValueMap.getInstance();
    private static Group workspace;

    public static ComponentViewFactory getInstance() {
        return instance;
    }

    public static void setWorkspace(Group group){
        workspace = group;
    }

    public ComponentView buildComponentGroup(Component component) {
        ComponentView componentView = new ComponentView();
        componentView.setParentComponent(component);
        if(valueMap.get(component.getClass()) == null){
            System.out.println("Component not loaded into DefaultValueMap");
        }
        buildSingleComponent(component, componentView);
        return componentView;

        /* Fail condition, currently returning null
        TODO: Add enum to limit possible group types
        */
    }

    public void buildInteractions(ComponentView componentView, double posX, double posY){
        enableDrag(componentView);
        enableRightClick(componentView);
        addAnchors(componentView, Orientation.LEFT, Orientation.RIGHT);
        componentView.setLayoutX(posX);
        componentView.setLayoutY(posY + PaletteIcon.size / 2);
        workspace.getChildren().add(componentView);
    }

    private Rectangle buildSingleComponent(Component component, ComponentView componentView){
        DefaultComponentValues values = valueMap.get(component.getClass());
        Rectangle rectangle = new Rectangle(values.getWidth(), values.getHeight());
        rectangle.setStroke(Component.OUTLINE);
        componentView.getChildren().add(rectangle);
        component.setIcon(rectangle);
        component.fill();
        return rectangle;
    }

    private void addAnchors(ComponentView group, Orientation... orientations){
        AnchorFactory factory = AnchorFactory.getInstance();
        Bounds bounds = group.getBoundsInParent();
        for(Orientation o : orientations) {
            factory.addAnchor(group, o, bounds.getMaxX(), bounds.getMaxY(), bounds.getMinX());
        }
    }

    private void enableDrag(ComponentView group){
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

    private void enableRightClick(ComponentView group) {
        group.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                group.setCursor(Cursor.HAND);
                RightClickMenuFactory.getInstance().buildRightClickMenu(group,event);
            }
        });
    }
}
