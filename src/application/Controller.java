package application;

import components.infrastructure.ComponentRegistry;
import palette.Palette;

public class Controller {
    public void changePictures(Palette pal) {
        Globals.schematicIcons = !Globals.schematicIcons;
        ComponentRegistry.getInstance().changeIcons();
        pal.changeIcons();
    }
}
