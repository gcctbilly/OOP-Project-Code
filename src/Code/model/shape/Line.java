package Code.model.shape;

/**
 * This class is a line class.
 * This class is used to create a line according to user commands.
 * And some functions need to call methods in this class.
 */
public class Line extends Shape {
    /**
     * name: The name of the created line.
     * x1: The x-axis of the left end point of the line segment.
     * y1: The y-axis of the left end point of the line segment.
     * x2: The x-axis of the right end point of the line segment.
     * y2: The y-axis of the right end point of the line segment.
     * thisZOrder: This field is used to keep track of the zOrder of each shape. It will increase one after shapes are been created
     *
     */
    public static final double DOUBLE = 0.05;
    private String name;
    private double x1,y1,x2,y2;
    private int thisZOrder;

    /**
     * @param name name
     * @param x1 x
     * @param y1 x
     * @param x2 x
     * @param y2 x
     */
    public Line(String name, double x1, double y1, double x2, double y2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thisZOrder = super.getzOrder();
    }

    /**
     * @return Rectangle boundingBox()
     */
    @Override
    public Rectangle boundingBox() {
        if(x1==x2||y1==y2){
            return null;
        }
        return new Rectangle("boundingbox", Math.min(x1,x2), Math.max(y1,y2), Math.max(x1,x2)- Math.min(x1,x2), Math.max(y1,y2)- Math.min(y1,y2));
    }

    /**
     * @param x  x
     * @param y y
     * @return true/false
     */
    //judge whether this object contains the point(x,y)
    @Override
    public boolean isContainPoint(double x, double y){
        if(x1==x2 && y1==y2){
            if(x==x1 && y==y1){
                return true;
            }
            else{
                return false;
            }
        }
        else if(x1==x2 && y1!=y2){

            if(x>x1- DOUBLE && x<x1+DOUBLE && y>Math.min(y1,y2)-DOUBLE && y<Math.max(y1,y2)+DOUBLE){
                return true;
            }
            else{
                return false;
            }

        }
        else if(y1==y2 && x1!=x2){
            if(x>Math.min(x1,x2)-DOUBLE && x<Math.max(x1,x2)+DOUBLE && y<y1+DOUBLE && y>y1-DOUBLE){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            float k;
            k= (float) ((y1-y2)/(float)(x1-x2));
            double b=(y1-k*x1);
            double distance=(float)(Math.abs(k*x-y+b))/(float)Math.sqrt(k*k+1);
            if(distance<DOUBLE){
                return true;
            }
            return false;
        }

    }

    /**
     * @param dx  x
     * @param dy y
     */

    //move dx and dy for this objective
    @Override
    public void move(double dx, double dy){
        this.x1=this.x1+dx;
        this.y1=this.y1+dy;
        this.x2=this.x2+dx;
        this.y2=this.y2+dy;
    }

    /**
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return x
     */
    public double getX1() {
        return x1;
    }

    /**
     * @return y
     */
    public double getY1() {
        return y1;
    }

    /**
     * @return x
     */
    public double getX2() {
        return x2;
    }

    /**
     * @return y
     */
    public double getY2() {
        return y2;
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
