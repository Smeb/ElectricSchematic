package components.controls;

import components.infrastructure.ComponentView;
import javafx.scene.input.MouseEvent;

public class RightClickMenuFactory {
    private static RightClickMenuFactory instance = new RightClickMenuFactory();
    //private static Group workspace;

    public static RightClickMenuFactory getInstance(){
        return instance;
    }
    /*public static void setWorkspace(Group group){
        workspace = group;
    }*/

    public RightClickMenu buildRightClickMenu(ComponentView group, MouseEvent event) {
        RightClickMenu rcMenu = new RightClickMenu(group.getParentComponent());
        rcMenu.show(group,event.getScreenX(),event.getScreenY());
        return rcMenu;
    }
}
