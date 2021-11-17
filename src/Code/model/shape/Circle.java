package Code.model.shape;

/**
 * description is in the report
 */
public class Circle extends Shape{
    /**
     *
     */
    public static final double DOUBLE = 0.05;
    private String name;
    private double x,y,r;
    private int thisZOrder;

    /**
     * @param name
     * name
     * @param x
     * x
     * @param y
     * y
     * @param r
     * r
     */
    public Circle(String name, double x, double y, double r) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.r = r;
        thisZOrder = super.getzOrder();
    }
    @Override
    public Rectangle boundingBox(){
        double l=2*r;
        Double bx=Double.parseDouble(String.format("%.2f", x-r));
        Double by=Double.parseDouble(String.format("%.2f", y+r));
        return new Rectangle("Boxof"+name,bx,by,l,l);
    }

    //judge whether this object contains the point(x,y)
    @Override
    public boolean isContainPoint(double x, double y){
        double distance;
        distance=Math.sqrt((this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
        if(distance>getR()- DOUBLE && distance<getR()+DOUBLE){
            return true;
        }
        return false;
    }

    //move dx and dy for this objective
    @Override
    public void move(double dx, double dy){
        this.x=x+dx;
        this.y=y+dy;

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
     * @return
     * y
     */
    public double getY() {
        return y;
    }

    /**
     * @return
     * r
     */
    public double getR() {
        return r;
    }

    @Override
    public int getThisZOrder() {
        return thisZOrder;
    }
}
