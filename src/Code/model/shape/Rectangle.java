package Code.model.shape;

public class Rectangle extends Shape{
    private String name;
    private double x,y,w,h;
    public int thisZOrder;

    public Rectangle(String name, double x, double y, double w, double h) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        thisZOrder = super.getzOrder();
    }

    public Rectangle(){}

    // return the bounding box of this object
    public Rectangle boundingBox(){
        return new Rectangle();
    }

    //judge whether this object contains the point(x,y)
    public boolean isContainPoint(double x, double y){
        return false;
    }

    //move dx and dy for this objective
    public void move(double dx, double dy){

    }

    //return a string that contains the information of the Shape
    public String toString() {
        return "";
    }


    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public int getThisZOrder() {
        return thisZOrder;
    }
}
