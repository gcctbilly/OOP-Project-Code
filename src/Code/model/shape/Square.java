package Code.model.shape;

public class Square extends Rectangle{
    private String name;
    private double x,y,l;
    private int thisZOrder;

    public Square(String name, double x, double y, double l) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.l = l;
        //可能会有问题
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public double getL() {
        return l;
    }

    @Override
    public int getThisZOrder() {
        return thisZOrder;
    }
}
