package components.infrastructure;

import Controllers.WireController;
import datastructures.Orientation;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class AnchorFactory {

    public enum Style {Boring, MickeyMouse}
    private static Style style;
    private static AnchorFactory instance = null;
    public static AnchorFactory getInstance(){
        if(instance == null){
            instance = new AnchorFactory();
        }
        return instance;
    }

    public void setStyle(Style s){
        style = s;
    }

    public void addAnchor(Group root, Orientation orientation, double MaxX, double MaxY, double MinX, double MinY){
        Anchor anchor;
        if(style == Style.MickeyMouse){
            if (orientation == Orientation.UP) {
                anchor = new Anchor(MaxX, MinY - 10.0);
            } else if (orientation == Orientation.RIGHT) {
                anchor = new Anchor(MaxX, MaxY + 10.0);
            } else if (orientation == Orientation.DOWN) {
                anchor = new Anchor(MinX, MaxY + 10.0);
            } else {
                anchor = new Anchor(MinX, MinY - 10.0);
            }
        }
        else{
            if (orientation == Orientation.UP) {
                anchor = new Anchor(MaxX / 2, MinY);
            } else if (orientation == Orientation.RIGHT) {
                anchor = new Anchor(MaxX, MaxY / 2);
            } else if (orientation == Orientation.DOWN) {
                anchor = new Anchor(MaxX / 2, MaxY);
            } else {
                anchor = new Anchor(MinX, MaxY / 2);
            }
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
