package Code.model.shape;

import java.util.HashMap;

public class Group extends Shape{
    String name;
    public List<Shape> Arr;//之前的List（可以存在group在里面）
    public List<Shape> All_Shape;//仅包含单个Shape的List
    private int thisZOrder;

    public Group(String name,List<Shape> Arr,List<Shape> All_Shape) {//构造器
        Arr = new ArrayList<>();
        this.Arr=Arr;
        this.name=name;
        this.All_Shape =All_Shape ;
    }
    public Rectangle boundingBox(){
        return new Rectangle();
    }

    //judge whether this object contains the point(x,y)
    public boolean isContainPoint(double x, double y){
        for(Shape a:All_Shape ){
            if(a.isContainPoint(x,y) ){
                return true;
            }
        }
        return false ;
    }

    //move dx and dy for this objective
    public void move(double dx, double dy){
        for(Shape a:All_Shape){
            a.move(dx,dy);
        }
        for(Shape b:Arr ){
            b.move(dx,dy);
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
}
