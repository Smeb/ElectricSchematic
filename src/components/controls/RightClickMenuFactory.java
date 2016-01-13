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
    //private static Group workspace;

    public static RightClickMenuFactory getInstance(){
        return instance;
    }
    /*public static void setWorkspace(Group group){
        workspace = group;
    }*/

    public RightClickMenu buildRightClickMenu(ComponentGroup group, MouseEvent event) {
        RightClickMenu rcMenu = new RightClickMenu(group.getParentComponent());
        rcMenu.show(group,event.getScreenX(),event.getScreenY());
        return rcMenu;
    }
}
