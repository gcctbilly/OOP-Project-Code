package Code.model.shape;

/**
 * description is in the report
 */
public abstract class Shape {
    private static int zOrder = 0;

    /**
     *
     */
    public Shape(){
        zOrder++;
    }

    /**
     * @return zOrder
     */
    public int getzOrder(){return zOrder;}

    /**
     * @return x
     */
    // return the bounding box of this object
    public abstract Rectangle boundingBox();

    /**
     * @param x  x
     * @param y y
     * @return 1
     */
    //judge whether this object contains the point(x,y)
    public abstract boolean isContainPoint(double x, double y);

    /**
     * @param dx  x
     * @param dy y
     */
    //move dx and dy for this objective
    public abstract void move(double dx, double dy);

    /**
     * @return 1
     */
    public abstract int getThisZOrder();


    /**
     * @return 1
     */
    public abstract String getName();

}
