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
        Double bx=Double.parseDouble(String.format("%.2f", x));
        Double by=Double.parseDouble(String.format("%.2f", y));
        return new Rectangle("Boxof"+this.name,bx,by,this.l,this.l);
    }

    //judge whether this object contains the point(x,y)
    public boolean isContainPoint(double a, double b){
        if(a>x-0.05 && a<x+0.05 && b<y+0.05 && b>y-l-0.05){
            return true;
        }
        else if(a>x-0.05 && a<x+l+0.05 && b<y+0.05 && b>y-0.05){
            return true;
        }
        else if(a>x-0.05 && a<x+l+0.05 && b<y-l+0.05 && b>y-l-0.05){
            return true;
        }
        else if(a>x+l-0.05 && a<x+l+0.05 && b<y+0.05 && b>y-l-0.05){
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
