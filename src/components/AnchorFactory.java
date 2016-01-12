package components;

import Controllers.WireController;
import datastructures.CoordinatePair;
import datastructures.Orientation;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

public class AnchorFactory {

    private static AnchorFactory instance = null;
    public static AnchorFactory getInstance(){
        if(instance == null){
            instance = new AnchorFactory();
        }
        return instance;
    }

    public void addAnchor(Group root, Orientation orientation){
        Bounds bounds = root.getBoundsInParent();
        Anchor anchor;
        if(orientation == Orientation.UP){
            anchor = new Anchor(bounds.getMaxX() / 2, bounds.getMinY());
        }
        else if(orientation == Orientation.RIGHT){
            anchor = new Anchor(bounds.getMaxX(), bounds.getMaxY() / 2);
        }
        else if(orientation == Orientation.DOWN){
            anchor = new Anchor(bounds.getMaxX() / 2, bounds.getMaxY());
        }
        else {
            anchor = new Anchor(bounds.getMinX(), bounds.getMaxY() / 2);
        }
        setInteractions(anchor);
        root.getChildren().add(anchor);
    }

    private void setInteractions(Anchor anchor){
        anchor.setOnMouseEntered(event -> anchor.setStroke(Color.RED));
        anchor.setOnMouseExited(event -> anchor.setStroke(Color.TRANSPARENT));
        anchor.setOnMouseDragEntered(event -> anchor.setStroke(Color.RED));
        anchor.setOnMouseDragExited(event -> anchor.setStroke(Color.TRANSPARENT));

        anchor.setOnDragDetected(event -> {
            WireController wireController = WireController.getInstance();
            if(!wireController.active() &&
                    anchor.getDirection() == Anchor.Direction.unset){
                wireController.setActive();
                wireController.setParent(anchor);
            }
            anchor.startFullDrag();
            event.consume();
        });

        anchor.setOnMouseDragged(event -> event.consume());

        anchor.setOnMouseDragReleased((event) -> {
            WireController wireController = WireController.getInstance();
            if(wireController.active() &&
                    wireController.getParentAnchor().getParent() != anchor.getParent() &&
                    anchor.getDirection() == Anchor.Direction.unset) {
                wireController.completeWire(anchor);
                wireController.setDormant();
            }
        });
    }
}
