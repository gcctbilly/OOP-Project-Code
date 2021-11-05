package Code.model.shape;

public class Line extends Shape {
    private String name;
    private double x1,y1,x2,y2;
    private int thisZOrder;

    public Line(String name, double x1, double y1, double x2, double y2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thisZOrder = super.getzOrder();
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

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public int getThisZOrder() {
        return thisZOrder;
    }
}
