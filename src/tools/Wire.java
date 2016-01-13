package tools;

import components.infrastructure.Anchor;
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

    public void update(Anchor anchor)
    {
        if(anchor.getDirection() == Anchor.Direction.send){
            this.setStartX(anchor.getPosition().getX());
            this.setStartY(anchor.getPosition().getY());
        } else if (anchor.getDirection() == Anchor.Direction.recv) {
            this.setEndX(anchor.getPosition().getX());
            this.setEndY(anchor.getPosition().getY());
        } this.toFront();
    }
    public Anchor getOtherEnd(Anchor a) {
        if (this.end == a) { return start; }
        else { return end; }
    }
}
