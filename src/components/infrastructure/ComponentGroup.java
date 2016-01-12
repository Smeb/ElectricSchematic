package components.infrastructure;

import components.parts.Component;
import javafx.scene.Group;

public class ComponentGroup extends Group {
        private Component parent;
        protected ComponentGroup(){
        }

        public Component getParentComponent(){return parent;}

        public void setParentComponent(Component component){
            if(parent == null){
                parent = component;
            }
        }
}
