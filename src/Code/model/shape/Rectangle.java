package Code.model.shape;

/**
 *
 */
public class Rectangle extends Shape{
    /**
     *
     */
    public static final double DOUBLE = 0.05;
    private String name;
    private double x,y,w,h;
    private int thisZOrder;

    /**
     * @param name
     * name
     * @param x
     * x
     * @param y
     * y
     * @param w
     * w
     * @param h
     * h
     */
    public Rectangle(String name, double x, double y, double w, double h) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        thisZOrder = super.getzOrder();
    }

    /**
     *
     */
    public Rectangle(){}

    // return the bounding box of this object
    @Override
    public Rectangle boundingBox(){
        Double bx=Double.parseDouble(String.format("%.2f", x));
        Double by=Double.parseDouble(String.format("%.2f", y));
        return new Rectangle("Boxof"+name,bx,by,w,h);
    }

    //judge whether this object contains the point(x,y)
    @Override
    public boolean isContainPoint(double a, double b){
        if(a>x- DOUBLE && a<x+DOUBLE && b<y+DOUBLE && b>y-h-DOUBLE){
            return true;
        }
        else if(a>x-DOUBLE && a<x+w+DOUBLE && b<y+DOUBLE && b>y-DOUBLE){
            return true;
        }
        else if(a>x-DOUBLE && a<x+w+DOUBLE && b<y-h+DOUBLE && b>y-h-DOUBLE){
            return true;
        }
        else if(a>x+w-DOUBLE && a<x+w+DOUBLE && b<y+DOUBLE && b>y-h-DOUBLE){
            return true;
        }
        return false;
    }

    //move dx and dy for this objective
    @Override
    public void move(double dx, double dy){
        this.x=this.x+dx;
        this.y=this.y+dy;
    }



    @Override
    public String getName() {
        return name;
    }

    /**
     * @return
     * x
     */
    public double getX() {
        return x;
    }

    /**
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * @return w
     */
    public double getW() {
        return w;
    }

    /**
     * @return h
     */
    public double getH() {
        return h;
    }

    @Override
    public int getThisZOrder() {
        return thisZOrder;
    }
}
