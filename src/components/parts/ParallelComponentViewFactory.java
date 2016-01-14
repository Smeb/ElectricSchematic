package components.parts;

import components.infrastructure.*;
import datastructures.ComponentValueMap;
import datastructures.CoordinatePair;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Vector;

public class ParallelComponentViewFactory {

    private static ParallelComponentViewFactory instance = new ParallelComponentViewFactory();

    private ParallelComponentViewFactory(){}
    public static ParallelComponentViewFactory getInstance(){return instance;}

    public ParallelComponent newParallelComponent(Class... classType){
        // TODO: Better logic for parallel components
        if(classType.length < ParallelComponent.MINCOMPONENTS || classType.length > ParallelComponent.MAXCOMPONENTS){
            System.out.println("Invalid number of arguments");
            return null;
        }
        ComponentViewFactory viewFactory = ComponentViewFactory.getInstance();
        Component current;
        ComponentView currentView;
        Vector<Component> components = new Vector<>();
        Vector<ComponentView> componentViews = new Vector<>();
        int i = 0;
        double heightOffset = 0.0;

        for (Class c : classType) {
            if (!Component.class.isAssignableFrom(c)) {
                return null;
            }
            current = ComponentFactory.getInstance().newComponent(c, 0.0, 0.0, true);
            components.add(current);

            currentView = viewFactory.buildComponentGroup(current);
            current.setComponentView(currentView);

            // Add anchors to facilitate finding XY of top and bottom line ends
            AnchorFactory.getInstance().addAnchors(currentView);

            currentView.setLayoutY(heightOffset);
            heightOffset += ComponentValueMap.getInstance().get(c).getHeight() + ParallelComponent.PARALLELOFFSET;
            // ComponentViewFactory.getInstance().enableRightClick(currentView);
            componentViews.add(currentView);
            i++;
        }
        ParallelComponentView componentView = new ParallelComponentView(componentViews);

        // Get first and final pair of anchors, get their coordinates and record them, then delete all anchors from all
        // components

        Pair<CoordinatePair, CoordinatePair> topAnchorPositions = componentViews.get(0).getParentComponent().getAnchorPositions();
        Pair<CoordinatePair, CoordinatePair> bottomAnchorPositions = componentViews.get(componentViews.size() - 1).getParentComponent().getAnchorPositions();
        ParallelComponent parallelComponent = new ParallelComponent(components, componentView, false);
        componentView.setParentComponent(parallelComponent);
        removeAnchors(componentViews);

        // Add vertical lines to emulate wires from top most component to bottom
        componentView.getChildren().addAll(
                new Line(topAnchorPositions.getKey().getX(), topAnchorPositions.getKey().getY(), bottomAnchorPositions.getKey().getX(), bottomAnchorPositions.getKey().getY()),
                new Line(topAnchorPositions.getValue().getX(), topAnchorPositions.getValue().getY(), bottomAnchorPositions.getValue().getX(), bottomAnchorPositions.getValue().getY()));

        viewFactory.buildInteractions(componentView, 20.0, 20.0);
        //TODO: Registration of interaction events on child views
        ComponentRegistry.getInstance().addComponent(parallelComponent);
        return parallelComponent;
    }

    public void removeAnchors(Vector<ComponentView> componentViews){
        LinkedList<Node> nodes = new LinkedList<>();
        for(ComponentView cv : componentViews){
            for(Node n : cv.getChildren()){
                if(n instanceof Anchor){
                    nodes.add(n);
                }
            }           cv.getChildren().removeAll(nodes);
            nodes.removeAll(nodes);
        }
    }
}
