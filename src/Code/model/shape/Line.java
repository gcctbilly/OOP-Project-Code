package Code.model.shape;

public class Line extends Shape {
    private String name;
    private double x1,y1,x2,y2;
    private int thisZOrder;

    public Line(String name, double x1, double y1, double x2, double y2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thisZOrder = super.getzOrder();
    }
    public Rectangle boundingBox(){
        return null;
    }

    //judge whether this object contains the point(x,y)
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
            if(x>x1-0.05 && x<x1+0.05 && y>Math.min(y1,y2)-0.05 && y<Math.max(y1,y2)+0.05){
                return true;
            }
            else{
                return false;
            }

        }
        else if(y1==y2 && x1!=x2){
            if(x>Math.min(x1,x2)-0.05 && x<Math.max(x1,x2)+0.05 && y<y1+0.05 && y>y1-0.05){
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
            if(distance<0.05){
                return true;
            }
            return false;
        }

    }

    //move dx and dy for this objective
    public void move(double dx, double dy){
        this.x1=this.x1+dx;
        this.y1=this.y1+dy;
        this.x2=this.x2+dx;
        this.y2=this.y2+dy;
    }


    public String getName() {
        return name;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public int getThisZOrder() {
        return thisZOrder;
    }
}
