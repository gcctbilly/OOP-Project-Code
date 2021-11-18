package Code.model.shape;

/**
 *This class is a Rectangle class.
 * This class is used to create a rectangle according to user commands.
 * And some functions need to call methods in this class.
 */
public class Rectangle extends Shape{
    /**
     *Field
     * name: The name of the created rectangle.
     * x: The x-axis of the vertex of the top-left corner of the created rectangle.
     * y: The y-axis of the vertex of the top-left corner of the created rectangle.
     * w: The width of the created rectangle.
     * h: The height of the created rectangle.
     * thisZOrder: This field is used to keep track of the zOrder of each shape. It will increase one after shapes are been created.
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
     * null
     */
    public Rectangle(){}

    /**
     *
     * @return Rectangle boundingBox()
     */
    // return the bounding box of this object
    @Override
    public Rectangle boundingBox(){
        Double bx=Double.parseDouble(String.format("%.2f", x));
        Double by=Double.parseDouble(String.format("%.2f", y));
        return new Rectangle("Boxof"+name,bx,by,w,h);
    }

    /**
     *
     * @param a x
     * @param b y
     * @return true/false
     */
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

    /**
     *
     * @param dx  x
     * @param dy y
     */
    //move dx and dy for this objective
    @Override
    public void move(double dx, double dy){
        this.x=this.x+dx;
        this.y=this.y+dy;
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

    /**
     *
     * @return thisZOrder
     */
    @Override
    public int getThisZOrder() {
        return thisZOrder;
    }
}
