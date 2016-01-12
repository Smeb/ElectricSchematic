package tools;

import components.*;
import javafx.scene.shape.Line;

/**
 * Created by henrymortimer on 12/01/2016.
 */
public class Wire extends Line
{
    private Anchor start;
    private Anchor end;

    public Wire(Anchor start, Anchor end)
    {
        super(start.getCenterX(), start.getCenterY(), end.getCenterX(), end.getCenterY());
        this.start = start;
        this.end = end;
    }

    public void updateWire()
    {
        setStartX(start.getCenterX());
        setStartY(start.getCenterY());
        setEndX(end.getCenterX());
        setEndY(end.getCenterY());
    }
}
