package components.wires;

import components.infrastructure.Anchor;
import javafx.scene.shape.Line;

public class Wire extends Line
{
    protected Anchor start;
    protected Anchor end;

    public Wire(Anchor start, Anchor end)
    {
        super(start.getPosition().getX(), start.getPosition().getY(), end.getPosition().getX(), end.getPosition().getY());
        this.setStrokeWidth(2);
        this.start = start;
        this.end = end;
    }

    public Anchor getParentAnchor(){return start;}
    public Anchor getEndAnchor(){return end;}

    public void update(Anchor anchor)
    {
        if(anchor == this.start){
            this.setStartX(anchor.getPosition().getX());
            this.setStartY(anchor.getPosition().getY());
        } else {
            this.setEndX(anchor.getPosition().getX());
            this.setEndY(anchor.getPosition().getY());
        } this.toFront();
    }

    public Anchor getOtherEnd(Anchor a) {
        if (this.end == a) { return start; }
        else { return end; }
    }

}
