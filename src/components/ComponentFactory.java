package components;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class ComponentFactory {

    private static ComponentFactory instance = null;

    public static ComponentFactory getInstance() {
        if(instance == null){
            instance = new ComponentFactory();
        }
        return instance;
    }

    protected ComponentFactory(){}

    public void newComponent(Group root, Class classType, double posX, double posY){
        if(classType == Component.class){
            Component component = new Component(posX, posY);
            enableDrag(component);
            addAnchors(root, component);
            root.getChildren().add(component);
        }
    }

    private void addAnchors(Group root, Component component){
        for(int i = 0; i < 2; i++){
            Anchor anchor = new Anchor(component, i);
            component.addAnchor(i, anchor);
            root.getChildren().add(anchor);
        }
    }

    private void enableDrag(Component component){
        final Delta dragDelta = new Delta();
        component.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                dragDelta.x = component.getTranslateX();
                dragDelta.y = component.getTranslateY();
            }
        });
        component.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                component.toFront();
            }
        });
        component.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                double dragX = event.getSceneX();
                double dragY = event.getSceneY();
                double newXPosition = dragDelta.x + dragX - component.getDefaultXY() / 2;
                double newYPosition = dragDelta.y + dragY - component.getDefaultXY() / 2;
                component.setX(newXPosition);
                component.setY(newYPosition);
                component.updateAnchors();
            }
        });
    }

    private class Delta { double x, y;}
}
