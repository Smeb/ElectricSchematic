package components.infrastructure;

import components.parts.Component;
import controllers.WireController;
import datastructures.Orientation;
import javafx.event.Event;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AnchorFactory {
    private static AnchorFactory instance = null;
    public static AnchorFactory getInstance(){
        if(instance == null){
            instance = new AnchorFactory();
        }
        return instance;
    }

    public void addAnchor(ComponentView componentView, Orientation orientation, double maxX, double maxY, double minX){
        double anchorX, anchorY;
        double componentEdgeX, componentEdgeY;
        if (orientation == Orientation.RIGHT) {
            componentEdgeX = maxX;
            componentEdgeY = maxY / 2;
            anchorX = componentEdgeX + Component.OFFSET;
            anchorY = componentEdgeY;

        } else {
            componentEdgeX = minX;
            componentEdgeY = maxY / 2;
            anchorX = componentEdgeX - Component.OFFSET;
            anchorY = componentEdgeY;
        }
        Anchor anchor = new Anchor(componentView.getParentComponent(), anchorX, anchorY);
        Line line = new Line(componentEdgeX, componentEdgeY, anchorX, anchorY);
        setInteractions(anchor);
        componentView.getChildren().addAll(anchor, line);
    }

    private void setInteractions(Anchor anchor){
        anchor.setOnMouseEntered(event -> anchor.setStroke(Color.RED));
        anchor.setOnMouseExited(event -> anchor.setStroke(Color.TRANSPARENT));
        anchor.setOnMouseDragEntered(event -> anchor.setStroke(Color.RED));
        anchor.setOnMouseDragExited(event -> anchor.setStroke(Color.TRANSPARENT));

        anchor.setOnDragDetected(event -> {
            WireController wireController = WireController.getInstance();
            if(!wireController.active()){
                wireController.setActive();
                wireController.setStart(anchor);
                anchor.startFullDrag();
            }
        });

        anchor.setOnMouseDragged(Event::consume);

        anchor.setOnMouseDragReleased((event) -> {
            WireController wireController = WireController.getInstance();
            if(wireController.active() &&
                    wireController.getStartAnchor().getParent() != anchor.getParent() &&
                    anchor.getDirection() == Anchor.Direction.unset) {
                wireController.completeWire(anchor);
                wireController.setDormant();
            }
        });
    }
}
