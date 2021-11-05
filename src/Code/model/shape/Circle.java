package Code.model.shape;

public class Circle extends Shape{
    private String name;
    double x,y,r;
    int thisZOrder;

    public Circle(String name, double x, double y, double r) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.r = r;
        thisZOrder = super.getzOrder();
    }
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

    public double getR() {
        return r;
    }

    public int getThisZOrder() {
        return thisZOrder;
    }
}
