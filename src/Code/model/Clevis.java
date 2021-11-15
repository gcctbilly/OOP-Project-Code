package Code.model;

import java.util.HashMap;
import Code.model.shape.Shape;

public class Clevis {
    private HashMap<String, Shape> storage;

    public Clevis(){
        storage = new HashMap<>();
    }

    // This method receive a command string and judge what function to execute
    public void process(String command) {

    }

    // This method receive information of rectangle and add rectangle to storage
    // can not create if the name has existed;
    public void createRectangle(String name, double x, double y, double w, double h) {

    }

    // This method receive information of circle and add circle to storage
    // can not create if the name has existed;
    public void createCircle(String name, double x, double y, double r) {

    }

    // This method receive information of square and add square to storage
    // can not create if the name has existed;
    public void createSquare(String name, double x, double y, double l) {

    }

    // This method receive information of group and add group to storage
    // the shape that will be grouped will not exist in the storage
    // can not create if the name has existed;
    public void createGroup(String name, String[] addName) {
        List<Shape> Arr= new ArrayList<>() ;//group里面自带的List（可能含group的list）
        List<Shape> All_Shape=new ArrayList<>() ;//group里的仅包含单个元素的List
        Shape temp;

        for(String a: addName){
            Arr.add(storage.get(a) );//给自带List赋值
        }


        for(String a: addName){
            if(storage.get(a).getClass().getName()=="Group" ){
                temp=(Group)storage.get(a);
                All_Shape.addAll(((Group) temp).All_Shape ); //给仅包含单个Shape的List赋值
            }
            else{
                All_Shape .add(storage .get(a) );
            }
        }

        Group A=new Group(name,Arr,All_Shape );//得到目前的Group

        storage.put(name,A);//向storage里存值


        for(String a : addName ){
            storage.remove(a); //删除所有的key
        }
    }

    // This method receive name of a group
    // the shape that will be ungrouped need to add to storage
    // the group will delete from the storage
    // can not ungroup if the name does not exist or it is not a group
    public void unGroup(String name) {

    }

    //This method receive name of a shape
    // the shape will delete from the storage
    // can not delete if the name does not exist
    public void delete(String name) {

    }

    //This method receive name of a shape
    //print out the information of the boundingBox in the shape
    // can not print if the name does not exist
    public void boundingBox(String name) {

    }

    //This method receive name of a shape and the distance need to move in the x and y
    //move the shape
    //can not move if the name do not exist
    public void move(String name, double dx, double dy) {

    }

    //This method receive the point(x,y)
    //move dx and dy for what contain the point and it has the largest zOrder
    //no shape will move if no shapes contains the point
    public void pickMove(double x, double y, double dx, double dy) {

    }

    //This method receive two names of shapes
    //print out whether the two shapes is intersected
    //can not print if one of the name can not find the shape
    public void intersect(String name1, String name2) {

    }

    //This method receive the name of a shape
    //print out the information of the shape
    //can not print if the name does not exist
    public void list(String name) {

    }

    //This method will print all the shape information in the storage
    public void listAll() {

    }

    //This method will quiz the application
    public void quiz() {

    }

    //This method will return shape contain the point(x,y)
    //return null if no
    public Shape findShape(double x, double y){
        return null;

    }

    //This method will return shape with the name
    //also consider all the simple shapes in the group
    //return null if no
    public Shape findShape(String name){
        return null;
    }


}
