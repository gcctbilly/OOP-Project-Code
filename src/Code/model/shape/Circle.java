package Code.model.shape;

/**
 * This class is a circle class. This class is used to create a circle according
 * to user commands. And some functions need to call methods in this class.
 */
public class Circle extends Shape{
    /**
     *Field
     * name: The name of the created circle.
     * x: The x-axis of the center of the circle.
     * y: The y-axis of the center of the circle.
     * r: The radius of the circle
     * thisZOrder: This field is used to keep track of the zOrder of each shape. It will increase one after shapes are been created.
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

    /**
     * @return Rectangle boundingBox()
     */
    @Override
    public Rectangle boundingBox(){
        double l=2*r;
        Double bx=Double.parseDouble(String.format("%.2f", x-r));
        Double by=Double.parseDouble(String.format("%.2f", y+r));
        return new Rectangle("Boxof"+name,bx,by,l,l);
    }

    /**
     *
     * @param x x
     * @param y y
     * @return true/false
     */
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

    /**
     *
     * @param dx x
     * @param dy y
     */
    //move dx and dy for this objective
    @Override
    public void move(double dx, double dy){
        this.x=x+dx;
        this.y=y+dy;

    }

    /**
     *
     * @return name
     */

    @Override
    public String getName() {
        return name;
    }

    /**
     * @return x
     * x
     */
    public double getX() {
        return x;
    }

    /**
     * @return y
     * y
     */
    public double getY() {
        return y;
    }

    /**
     * @return r
     * r
     */
    public double getR() {
        return r;
    }

    /**
     *
     * @return thisZOrder
     */
    @Override
    public int getThisZOrder() {
        return thisZOrder;
    }
}
