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
        return new Rectangle();
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
