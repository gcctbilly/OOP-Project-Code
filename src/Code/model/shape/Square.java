package Code.model.shape;

/**
 *
 */
public class Square extends Rectangle{
    /**
     *
     */
    public static final double DOUBLE1 = 0.05;
    private String name;
    private double x,y,l;
    private int thisZOrder;

    /**
     * @param name 1
     * @param x 1
     * @param y 1
     * @param l 1
     */
    public Square(String name, double x, double y, double l) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.l = l;
        thisZOrder = super.getzOrder();
    }
    @Override
    public Rectangle boundingBox(){
        Double bx=Double.parseDouble(String.format("%.2f", x));
        Double by=Double.parseDouble(String.format("%.2f", y));
        return new Rectangle("Boxof"+this.name,bx,by,this.l,this.l);
    }

    //judge whether this object contains the point(x,y)
    @Override
    public boolean isContainPoint(double a, double b){
        if(a>x- DOUBLE1 && a<x+DOUBLE1 && b<y+DOUBLE1 && b>y-l-DOUBLE1){
            return true;
        }
        else if(a>x-DOUBLE1 && a<x+l+DOUBLE1 && b<y+DOUBLE1 && b>y-DOUBLE1){
            return true;
        }
        else if(a>x-DOUBLE1 && a<x+l+DOUBLE1 && b<y-l+DOUBLE1 && b>y-l-DOUBLE1){
            return true;
        }
        else if(a>x+l-DOUBLE1 && a<x+l+DOUBLE1 && b<y+DOUBLE1 && b>y-l-DOUBLE1){
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

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    /**
     * @return 1
     */
    public double getL() {
        return l;
    }

    @Override
    public int getThisZOrder() {
        return thisZOrder;
    }
}
