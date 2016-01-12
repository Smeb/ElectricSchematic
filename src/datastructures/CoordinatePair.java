package datastructures;

public class CoordinatePair {
    private double posX;
    private double posY;

    public CoordinatePair(){
        posX = 0.0;
        posY = 0.0;
    }

    public CoordinatePair(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }


    public double getX(){return posX;}
    public double getY(){return posY;}
    public void setXY(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public String toString(){
        return "(" + posX + " " + posY + ")";
    }
}
