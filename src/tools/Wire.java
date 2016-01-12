package tools;

import components.*;
import datastructures.CoordinatePair;
import javafx.scene.shape.Line;

public class Wire extends Line
{
    private Anchor start;
    private Anchor end;

    public Wire(Anchor start, Anchor end)
    {
        super(start.getPosition().getX(), start.getPosition().getY(), end.getPosition().getX(), end.getPosition().getY());
        this.setStrokeWidth(2);
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
