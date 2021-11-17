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
        Double bx=Double.parseDouble(String.format("%.2f", x));
        Double by=Double.parseDouble(String.format("%.2f", y));
        return new Rectangle("Boxof"+name,x,y,w,h);
    }

    //judge whether this object contains the point(x,y)
    public boolean isContainPoint(double a, double b){
        if(a>x-0.05 && a<x+0.05 && b<y+0.05 && b>y-h-0.05){
            return true;
        }
        else if(a>x-0.05 && a<x+w+0.05 && b<y+0.05 && b>y-0.05){
            return true;
        }
        else if(a>x-0.05 && a<x+w+0.05 && b<y-h+0.05 && b>y-h-0.05){
            return true;
        }
        else if(a>x+w-0.05 && a<x+w+0.05 && b<y+0.05 && b>y-h-0.05){
            return true;
        }
        return false;
    }

    //move dx and dy for this objective
    public void move(double dx, double dy){
        this.x=this.x+dx;
        this.y=this.y+dy;
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
