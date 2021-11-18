package Code.model.shape;

/**
 * This class is a Square class.
 * This class is used to create a square according to user commands.
 * And some functions need to call methods in this class.
 */
public class Square extends Rectangle{
    /**
     *Field
     * name: The name of the created square.
     * x: The x-axis of the vertex of the top-left corner of the created rectangle.
     * y: The y-axis of the vertex of the top-left corner of the created rectangle.
     * l: The side length of the square.
     * thisZOrder: This field is used to keep track of the zOrder of each shape. It will increase one after shapes are been created.
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

    /**
     *
     * @return Rectangle boundingBox()
     */
    @Override
    public Rectangle boundingBox(){
        Double bx=Double.parseDouble(String.format("%.2f", x));
        Double by=Double.parseDouble(String.format("%.2f", y));
        return new Rectangle("Boxof"+this.name,bx,by,this.l,this.l);
    }

    /**
     *
     * @param a
     * @param b
     * @return true/false
     */
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
     *
     * @return x
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     *
     * @return y
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * @return l
     */
    public double getL() {
        return l;
    }

    /**
     *
     * @return thisZOder
     */
    @Override
    public int getThisZOrder() {
        return thisZOrder;
    }
}
