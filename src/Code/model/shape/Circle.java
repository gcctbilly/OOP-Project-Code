package Code.model.shape;

public class Circle extends Shape{
    private String name;
    private double x,y,r;
    private int thisZOrder;

    public Circle(String name, double x, double y, double r) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.r = r;
        thisZOrder = super.getzOrder();
    }
    public Rectangle boundingBox(){
        double l=2*r;
        Double bx=Double.parseDouble(String.format("%.2f", x-r));
        Double by=Double.parseDouble(String.format("%.2f", y+r));
        return new Rectangle("Boxof"+name,bx,by,l,l);
    }

    //judge whether this object contains the point(x,y)
    public boolean isContainPoint(double x, double y){
        double distance;
        distance=Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
        if(distance>getR()-0.05 && distance<getR()+0.05){
            return true;
        }
        return false;
    }

    //move dx and dy for this objective
    public void move(double dx, double dy){
        this.x=x+dx;
        this.y=y+dy;

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
