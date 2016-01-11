package components;

import Controllers.WireController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AnchorFactory {

    private static AnchorFactory instance = null;
    public static AnchorFactory getInstance(){
        if(instance == null){
            instance = new AnchorFactory();
        }
        return instance;
    }

    public void addAnchors(Group root, Component parent){
        for(int i = 0; i < parent.getNAnchors(); i++){
            Anchor anchor = newAnchor(parent, i);
            root.getChildren().add(anchor);
        }
    }

    private Anchor newAnchor(Component parent, int i){
        Anchor anchor = new Anchor(parent, i);
        parent.addAnchor(i, anchor);
        setInteractions(anchor);
        return anchor;
    }

    private void setInteractions(Anchor anchor){
        anchor.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchor.setStroke(Color.RED);
            }
        });

        anchor.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchor.setStroke(Color.TRANSPARENT);
            }
        });

        /*
        anchor.setOnMouseClicked(new EventHandler<MouseEvent> {
            if(WireController.getInstance().holdingWire()){
                Wire wire = WireController.getInstance().getWire();
                anchor.attach(Wire);
            }
        });
        */
    }
}
