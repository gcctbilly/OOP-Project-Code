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
        thisZOrder = super.getzOrder();

    }
    public Rectangle boundingBox(){
        double xl,xr,yu,yd;
        double Xl,Xr,Yu,Yd;
        xl=1;xr=1;yu=1;yd=1;
        for(String a:allShapes .keySet()) {
            if (allShapes.get(a).boundingBox() != null) {
                xl = allShapes.get(a).boundingBox().getX();

                yu = allShapes.get(a).boundingBox().getY();
                xr = allShapes.get(a).boundingBox().getX() + allShapes.get(a).boundingBox().getW();
                yd = allShapes.get(a).boundingBox().getY() - allShapes.get(a).boundingBox().getH();
            }
        }
        for(String a:allShapes .keySet()){
            if(allShapes.get(a).boundingBox()!=null) {
                Xl = allShapes.get(a).boundingBox().getX();
                Yu = allShapes.get(a).boundingBox().getY();
                Xr = allShapes.get(a).boundingBox().getX() + allShapes.get(a).boundingBox().getW();
                Yd = allShapes.get(a).boundingBox().getY() - allShapes.get(a).boundingBox().getH();

            }else{
                Line temp=(Line)allShapes.get(a);
                Xl=Math.min(temp.getX1(),temp.getX2() );
                Yu=Math.max(temp.getY1(),temp.getY2() );
                Xr= Math.max(temp.getX1(),temp.getX2() );
                Yd= Math.min(temp.getY1() ,temp.getY2() );

            }
            if (Xl < xl) {
                xl = Xl;
            }
            if (Yu > yu) {
                yu = Yu;
            }
            if (Xr > xr) {
                xr = Xr;
            }
            if (Yd < yd) {
                yd = Yd;
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
