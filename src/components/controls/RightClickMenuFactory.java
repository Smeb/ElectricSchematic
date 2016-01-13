package components.controls;

import components.infrastructure.ComponentGroup;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Created by minttu on 13/01/2016.
 */
public class RightClickMenuFactory {
    private static RightClickMenuFactory instance = new RightClickMenuFactory();
    private static Group workspace;

    public static RightClickMenuFactory getInstance(){
        return instance;
    }
    public static void setWorkspace(Group group){
        workspace = group;
    }

    public RightClickMenu buildRightClickMenu(ComponentGroup group, MouseEvent event) {
        RightClickMenu rcmenu = new RightClickMenu(group);
        //rcmenu.setLay
        //workspace.getChildren().add(rcmenu);
        rcmenu.show(group,event.getScreenX(),event.getScreenY());
        System.out.println("Menu at " + event.getScreenX() + " " + event.getScreenY());
        return rcmenu;
    }
}
