package components;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class ComponentFactory {

    private static ComponentFactory instance = null;
    private static Group root = null;

    public static ComponentFactory getInstance() {
        if(instance == null){
            instance = new ComponentFactory();
        }
        if(root == null){
            System.out.println("Fatal error : canvas not assigned");
            System.exit(0);
        }
        return instance;
    }

    private ComponentFactory() {
        ;
    }

    public static void assignGroup(Group root) throws Exception {
        if(instance == null){
            instance = new ComponentFactory();
        }
        if(ComponentFactory.root != null){
            throw(new Exception("Root already initialized"));
        }
        ComponentFactory.root = root;
    }

    public void newComponent(Group root, Class classType, double posX, double posY){
        if(classType == Component.class){
            Component component = new Component(posX, posY);
            enableDrag(component);
            addAnchors(root, component);
            root.getChildren().add(component);
            ComponentRegistry.getInstance().addComponent(component);
        }
    }

    private void addAnchors(Group root, Component component){
        AnchorFactory factory = AnchorFactory.getInstance();
        factory.addAnchors(root, component);
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
                component.moveToFront();
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
