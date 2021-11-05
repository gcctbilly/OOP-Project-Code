package Code.model.shape;

import java.util.HashMap;

public class Group extends Shape{
    private String name;
    private HashMap<String, Shape> groupShape;
    private int thisZOrder;

    public Group(String name, HashMap<String, Shape> groupShape) {
        this.name = name;
        this.groupShape = groupShape;
        thisZOrder = super.getzOrder();
    }
    public Rectangle boundingBox(){
        return new Rectangle();
    }

    //judge whether this object contains the point(x,y)
    public boolean isContainPoint(double x, double y){
        return false;
    }

    //move dx and dy for this objective
    public void move(double dx, double dy){

    }

    //return a string that contains the information of the Shape
    public String toString() {
        return "";
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Shape> getGroupShape() {
        return groupShape;
    }

    public int getThisZOrder() {
        return thisZOrder;
    }
}
