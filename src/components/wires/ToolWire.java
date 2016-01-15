package components.wires;

import components.infrastructure.Anchor;
import javafx.scene.paint.Color;

/**
 * Created by archie on 15/01/16.
 */
public class ToolWire extends Wire {
    // Main differences:
    // 1. Wire is different color
    // 2. Wire does not connect components
    // 3. Wire is unique to Voltmeter
    public ToolWire(Anchor start, Anchor end)
    {
        super(start, end);
        this.setStrokeWidth(2);
        this.setStroke(Color.RED);
    }

}
