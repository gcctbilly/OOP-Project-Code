package Code.model.shape;

import java.util.HashMap;
import java.util.Iterator;

public class Group extends Shape{
    private String name;
    private HashMap<String,Shape> shapes;
    private HashMap<String,Shape> allShapes; // store all the simple shapes of the group
    private int thisZOrder;

    public Group(String name, HashMap<String,Shape> shapes, HashMap<String,Shape> allShapes) {
        this.name = name;
        this.shapes = shapes;
        this.allShapes = allShapes;

    }
    public Rectangle boundingBox(){
        double xl,xr,yu,yd;
        double Xl,Xr,Yu,Yd;
        Object key=allShapes .keySet() .toArray() [0];
        xl=allShapes.get(key).boundingBox().getX() ;
        yu=allShapes.get(key).boundingBox().getY() ;
        xr=allShapes.get(key).boundingBox().getX() +allShapes.get(key).boundingBox().getW() ;
        yd=allShapes.get(key).boundingBox().getY() -allShapes.get(key).boundingBox().getH() ;
        for(String a:allShapes .keySet()){
            Xl=allShapes.get(a).boundingBox(). getX() ;
            Yu=allShapes.get(a).boundingBox().getY() ;
            Xr=allShapes.get(a).boundingBox().getX() +allShapes.get(a).boundingBox().getW() ;
            Yd=allShapes.get(a).boundingBox().getY() -allShapes.get(a).boundingBox().getH() ;
            if(Xl<xl){
                xl=Xl;
            }
            if(Yu>yu){
                yu=Yu;
            }
            if(Xr>xr){
                xr=Xr;
            }
            if(Yd<yd){
                yd=Yd;
            }


        }
        return new Rectangle("boundingbox",xl,yu,xr-xl,yu-yd);
    }

    //judge whether this object contains the point(x,y)
    public boolean isContainPoint(double x, double y){
        Shape currentShape = null;
        Iterator it = allShapes.values().iterator();
        while (it.hasNext()){
            currentShape = (Shape) it.next();
            if (currentShape.isContainPoint(x, y)){
                return true;
            }
        }
        return false;
    }

    //move dx and dy for this objective
    public void move(double dx, double dy){
        Shape currentShape = null;
        Iterator it = allShapes.values().iterator();
        while (it.hasNext()){
            currentShape = (Shape) it.next();
            currentShape.move(dx,dy);
        }


    }

    //return a string that contains the information of the Shape
    public String toString() {
        return "";
    }

    public String getName() {
        return name;
    }

    public int getThisZOrder() {
        return thisZOrder;
    }

    public HashMap<String, Shape> getShapes() {
        return shapes;
    }

    public HashMap<String, Shape> getAllShapes() {
        return allShapes;
    }
}
