package components.infrastructure;

import components.parts.Component;
import controllers.WireController;
import datastructures.CoordinatePair;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class AnchorFactory {
    private static AnchorFactory instance = null;
    public static AnchorFactory getInstance(){
        if(instance == null){
            instance = new AnchorFactory();
        }
        return instance;
    }

    public Pair<CoordinatePair, CoordinatePair> addAnchorLines(ComponentView componentView){
        Bounds bounds = componentView.getBoundsInParent();
        // Left Anchor wire
        CoordinatePair pair1 = new CoordinatePair(bounds.getMinX() - Component.OFFSET, bounds.getMaxY() / 2);
        // Right Anchor wire
        CoordinatePair pair2 = new CoordinatePair(bounds.getMaxX() + Component.OFFSET, bounds.getMaxY() / 2);
        System.out.println("Anchor x, y pair :" + pair1.toString() + " " + pair2.toString());
        Line line1 = new Line(bounds.getMinX(), bounds.getMaxY() / 2, bounds.getMinX() - Component.OFFSET, bounds.getMaxY() / 2);
        Line line2 = new Line(bounds.getMaxX(), bounds.getMaxY() / 2, bounds.getMaxX() + Component.OFFSET, bounds.getMaxY() / 2);
        componentView.getChildren().addAll(line1, line2);
        return new Pair(pair1, pair2);
    }

    public void addAnchors(ComponentView componentView){
        Pair<CoordinatePair, CoordinatePair> pairedCoordinates = addAnchorLines(componentView);
        // Left Anchor
        Anchor anchor1 = new Anchor(componentView.getParentComponent(), pairedCoordinates.getKey());
        // Right Anchor
        Anchor anchor2 = new Anchor(componentView.getParentComponent(), pairedCoordinates.getValue());
        setInteractions(anchor1);
        setInteractions(anchor2);
        componentView.getChildren().addAll(anchor1, anchor2);
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
