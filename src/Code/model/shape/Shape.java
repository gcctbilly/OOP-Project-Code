package Code.model.shape;

public abstract class Shape {
    private static int zOrder = 0;
    public Shape(){
        zOrder++;
    }
    public int getzOrder(){return zOrder;}

    // return the bounding box of this object
    public abstract Rectangle boundingBox();

    //judge whether this object contains the point(x,y)
    public abstract boolean isContainPoint(double x, double y);

    //move dx and dy for this objective
    public abstract void move(double dx, double dy);

    public abstract int getThisZOrder();
   

    public abstract String getName();

}
