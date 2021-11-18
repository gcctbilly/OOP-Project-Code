package Code.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;
import java.text.DecimalFormat;

import Code.model.shape.*;


/**
 * description is in the report
 */
public class Clevis {

    /**
     *
     */
    public static final int INT = 16;
    /**
     *
     */
    public static final double B = 0.5;
    /**
     *
     */
    private DecimalFormat  df=new DecimalFormat("0.00");

    private HashMap<String, Shape> storage;
    private String filePathHtml ="";
    private String filePathTxt = "";
    private int operationIndex = 0;


    /**
     *
     */
    public Clevis(){
        storage = new HashMap<>();
    }

    /**
     * @param command 1
     * @return 1
     */
    // This method receive a command string and judge what function to execute
    //return 0 for wrong input and 1 for success execute 2 for quit
    public int process(String command) {
        command = command.trim();
        if(!filePathTxt.equals("") && !filePathHtml.equals("")) {
            operationIndex++;
            writeFileTxt(command);
            writeFileHtml(command);
        }
        if(command.length() == 0) {
            System.out.println("There is no command");
            return 0;
        }
        String[] commands = command.split("\\s+");
        if(commands[0].equals("rectangle")) {
            if(commands.length != 6) {
                System.out.println("Please enter the right command");
                return 0;
            }
            else if(!isAllNumber(commands[2]) || !isAllNumber(commands[3]) || !isAllNumber(commands[4]) || !isAllNumber(commands[5])) {
                System.out.println("Please enter the right number");
                return 0;
            }
            String name = commands[1];
            double x = Double.parseDouble(commands[2]);
            double y = Double.parseDouble(commands[3]);
            double w = Double.parseDouble(commands[4]);
            double h = Double.parseDouble(commands[5]);
            return createRectangle(name,x,y,w,h);
        }
        else if(commands[0].equals("line")){
            if(commands.length != 6) {
                System.out.println("Please enter the right command");
                return 0;
            }
            else if(!isAllNumber(commands[2]) || !isAllNumber(commands[3]) || !isAllNumber(commands[4]) || !isAllNumber(commands[5])) {
                System.out.println("Please enter the right number");
                return 0;
            }
            String name = commands[1];
            double x1 = Double.parseDouble(commands[2]);
            double y1 = Double.parseDouble(commands[3]);
            double x2 = Double.parseDouble(commands[4]);
            double y2 = Double.parseDouble(commands[5]);
            return createLine(name,x1,y1,x2,y2);
        }
        else if(commands[0].equals("circle")){
            if(commands.length != 5) {
                System.out.println("Please enter the right command");
                return 0;
            }
            else if(!isAllNumber(commands[2]) || !isAllNumber(commands[3]) || !isAllNumber(commands[4])) {
                System.out.println("Please enter the right number");
                return 0;
            }
            String name = commands[1];
            double x = Double.parseDouble(commands[2]);
            double y = Double.parseDouble(commands[3]);
            double r = Double.parseDouble(commands[4]);
            return createCircle(name,x,y,r);
        }
        else if(commands[0].equals("square")) {
            if(commands.length != 5) {
                System.out.println("Please enter the right command");
                return 0;
            }
            else if(!isAllNumber(commands[2]) || !isAllNumber(commands[3]) || !isAllNumber(commands[4])) {
                System.out.println("Please enter the right number");
                return 0;
            }
            String name = commands[1];
            double x = Double.parseDouble(commands[2]);
            double y = Double.parseDouble(commands[3]);
            double l = Double.parseDouble(commands[4]);
            return createSquare(name,x,y,l);
        }
        else if(commands[0].equals("group")) {
            String name = commands[1];
            String addName[] = new String[commands.length-2];
            for(int i = 0; i < commands.length-2; i++) {
                addName[i] = commands[i+2];
            }

            return createGroup(name,addName);
        }
        else if(commands[0].equals("ungroup")) {
            if (commands.length != 2) {
                System.out.println("Please enter the right command");
                return 0;
            }
            String name = commands[1];

            return unGroup(name);
        }
        else if (commands[0].equals("delete")){
            if (commands.length != 2) {
                System.out.println("Please enter the right command");
                return 0;
            }
            String name = commands[1];
            return delete(name);
        }
        else if(commands[0].equals("boundingbox")) {
            if (commands.length != 2) {
                System.out.println("Please enter the right command");
                return 0;
            }
            String name = commands[1];
            return boundingBox(name);
        }
        else if(commands[0].equals("move")) {
            if(commands.length != 4) {
                System.out.println("Please enter the right command");
                return 0;
            }
            else if(!isAllNumber(commands[2]) || !isAllNumber(commands[3])) {
                System.out.println("Please enter the right number");
                return 0;
            }
            String name = commands[1];
            double dx = Double.parseDouble(commands[2]);
            double dy = Double.parseDouble(commands[3]);
            return move(name,dx,dy);
        }
        else if(commands[0].equals("pick-and-move")) {
            if(commands.length != 5) {
                System.out.println("Please enter the right command");
                return 0;
            }
            else if(!isAllNumber(commands[1]) || !isAllNumber(commands[2]) || !isAllNumber(commands[3]) || !isAllNumber(commands[4])) {
                System.out.println("Please enter the right number");
                return 0;
            }
            double x = Double.parseDouble(commands[1]);
            double y = Double.parseDouble(commands[2]);
            double dx = Double.parseDouble(commands[3]);
            double dy = Double.parseDouble(commands[4]);
            return pickMove(x,y,dx,dy);
        }
        else if(commands[0].equals("intersect")) {
            if(commands.length != 3) {
                System.out.println("Please enter the right command");
                return 0;
            }
            String name1 = commands[1];
            String name2 = commands[2];
            boolean isSuccess = intersect(name1,name2);
            if(isSuccess){
                System.out.println(name1 + " and "+ name2 + " intersect");
                return 1;
            }
            System.out.println(name1 + " and "+ name2 + " do not intersect");
            return 0;
        }
        else if(commands[0].equals("list")){
            if(commands.length != 2) {
                System.out.println("Please enter the right command");
                return 0;
            }
            String name = commands[1];
            return list(name);
        }
        else if(commands[0].equals("listAll")) {
            if (commands.length != 1) {
                System.out.println("Please enter the right command");
                return 0;
            }
            listAll();
            return 1;
        }
        else if(commands[0].equals("quit")){
            if (commands.length != 1) {
                System.out.println("Please enter the right command");
                return 0;
            }
            return 2;
        }
        System.out.println("There is no such command " + command);
        return 0;


    }

    /**
     * @param nameHtml 1
     * @param nameTxt 1
     * @return 1
     */
    //create the file 1 for success 0 for fail
    public int createFile(String nameHtml, String nameTxt) {
        filePathHtml = nameHtml;
        filePathTxt =  nameTxt;
        File newFil1 = new File(filePathHtml);
        File newFil2 = new File(filePathTxt);
        if(newFil1.exists() || newFil2.exists()) {
            System.out.println("The file has already existed. Please enter the other file name");
            return 0;
        }
        try{
            newFil1.createNewFile();
            newFil2.createNewFile();
            initialHtml();
            System.out.println("file created");
        }catch (IOException e){
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * @param command 1
     */
    //write command in the txt
    public void writeFileTxt(String command) {
        try {
            FileWriter fw = new FileWriter(filePathTxt,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(command + '\n');
            bw.close();
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    //initialize the html file
    public void initialHtml() {
        File file = new File(filePathHtml);
        try{
            RandomAccessFile random = new RandomAccessFile(file,"rw");
            String content = "<table><thead><tr><td>Index</td><td>Command</td></tr></thead><tbody></tbody></table>";
            random.write(content.getBytes(StandardCharsets.UTF_8));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param command 1
     */
    //write command in the html
    public void writeFileHtml(String command) {
        File file = new File(filePathHtml);
        try{
            RandomAccessFile randomFile = new RandomAccessFile(file,"rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength- INT);
            //store the remained content in order to add back after write
            String remained = randomFile.readLine();
            randomFile.seek(fileLength-INT);
            String processedCommand = "<tr><td>"+operationIndex +"</td><td>" + command + "</td></tr>";
            randomFile.write((processedCommand+remained).getBytes(StandardCharsets.UTF_8));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param name 1
     * @param x 1
     * @param y 1
     * @param w 1
     * @param h 1
     * @return 1
     */
    // This method receive information of rectangle and add rectangle to storage
    // can not create if the name has existed;
    //return 0 for do not create  and 1 for successfully created
    public int createRectangle(String name, double x, double y, double w, double h) {
        //whether the shape has existed
        if(findShape(name) != null) {
            System.out.println("This name has already existed !");
            return 0;
        }
        Rectangle newRectangle = new Rectangle(name,x,y,w,h);
        storage.put(name,newRectangle);
        System.out.println("Rectangle created successfully");
        return 1;
    }

    /**
     * @param name 1
     * @param x 1
     * @param y 1
     * @param r 1
     * @return 1
     */
    // This method receive information of circle and add circle to storage
    // can not create if the name has existed;
    //return 0 for do not create  and 1 for successfully created
    public int createCircle(String name, double x, double y, double r) {
        //whether the shape has existed
        if(findShape(name) != null) {
            System.out.println("This name has already existed !");
            return 0;
        }
        Circle newCircle = new Circle(name,x,y,r);
        storage.put(name,newCircle);
        System.out.println("Circle created successfully");
        return 1;

    }

    /**
     * @param name 1
     * @param x 1
     * @param y 1
     * @param l 1
     * @return 1
     */
    // This method receive information of square and add square to storage
    // can not create if the name has existed;
    //return 0 for do not create  and 1 for successfully created
    public int createSquare(String name, double x, double y, double l) {
        //whether the shape has existed
        if(findShape(name) != null) {
            System.out.println("This name has already existed !");
            return 0;
        }
        Square newSquare = new Square(name,x,y,l);
        storage.put(name,newSquare);
        System.out.println("Square created successfully");
        return 1;
    }

    /**
     * @param name 1
     * @param x1 1
     * @param y1 1
     * @param x2 1
     * @param y2 1
     * @return 1
     */
    // This method receive information of Line and add Line to storage
    // can not create if the name has existed;
    //return 0 for do not create  and 1 for successfully created
    public int createLine(String name, double x1, double y1, double x2, double y2){
        //whether the shape has existed
        if(findShape(name) != null) {
            System.out.println("This name has already existed !");
            return 0;
        }
        Line newLine = new Line(name,x1,y1,x2,y2);
        storage .put(name,newLine );
        System.out.println("Line created successfully");
        return 1;
    }

    /**
     * @param name 1
     * @param addName 1
     * @return 1
     */
    // This method receive information of group and add group to storage
    // the shape that will be grouped will not exist in the storage
    // can not create if the name has existed;
    //return 0 for fail and 1 for success
    public int createGroup(String name, String[] addName) {
        HashMap<String,Shape> add = new HashMap<>();
        HashMap<String,Shape> addAll = new HashMap<>();
        if(findShape(name) != null){
            System.out.println("The name has already existed. This time will not create the group");
            return 0;
        }
        for(int i = 0; i < addName.length; i++) {
            if (!storage.containsKey(addName[i])) {
                System.out.println("The "+ addName[i] + " does not exist. This time will not create the group" );
                return 0;
            }
        }
        for(int i = 0; i < addName.length;i++) {
            Shape currentShape = storage.get(addName[i]);
            if(currentShape.getClass().getSimpleName().equals("Group")) {
                addAll.putAll(((Group)currentShape).getAllShapes());
            }
            else {
                addAll.put(addName[i],currentShape);
                add.put(addName[i],currentShape);
            }
            storage.remove(addName[i]);
        }
        Group newGroup = new Group(name,add,addAll);
        storage.put(name,newGroup);
        System.out .println("Group created successfully") ;
        return 1;

    }

    // This method receive name of a group
    // the shape that will be ungrouped need to add to storage
    // the group will delete from the storage
    // can not ungroup if the name does not exist or it is not a group
    //return 0 for fail and 1 for success

    /**
     * @param name 1
     * @return 1
     */
    public int unGroup(String name) {
        if(!storage.containsKey(name)){
            System.out.println("The group does not exist");
            return 0;
        }
        Shape A=storage.get(name);
        if(A.getClass().getSimpleName().equals("Group") ){
            Group B=(Group)A;
            for(String a:B.getShapes() .keySet() ){
                storage.put(a,B.getShapes().get(a) );
            }
            storage.remove(name);
            return 1;
        }else{
            System.out .println("it is not a group");
            return 0;
        }


    }

    /**
     * @param name 1
     * @return 1
     */
    //This method receive name of a shape
    // the shape will delete from the storage
    // can not delete if the name does not exist
    //return 0 for fail and 1 for success
    public int delete(String name) {
        if(!storage.containsKey(name)) {
            System.out.println("This name does not exist");
            return 0;
        }
        storage.remove(name);
        System.out.println(name + " has deleted");
        return 1;

    }

    /**
     * @param name  1
     * @return 1
     */
    //This method receive name of a shape
    //print out the information of the boundingBox in the shape
    // can not print if the name does not exist
    //return 0 for fail and 1 for success
    public int boundingBox(String name) {
        if(!storage.containsKey(name)){
            System.out.println("This name does not exist");
            return 0;
        }
        Shape currentShape = storage.get(name);
        Rectangle boundingBox = currentShape.boundingBox();
        System.out.println("The bounding box is:");
        System.out.println("name: " + boundingBox.getName() + " x:" + boundingBox.getX() + " y:" + boundingBox.getY() + " w:" + boundingBox.getW() + " h:" + boundingBox.getH());
        return 1;
    }

    /**
     * @param name 1
     * @param dx 1
     * @param dy 1
     * @return 1
     */
    //This method receive name of a shape and the distance need to move in the x and y
    //move the shape
    //can not move if the name do not exist
    //return 0 for fail and 1 for success
    public int move(String name, double dx, double dy) {
        if(!storage.containsKey(name)){
            return 0;
        }
        Shape currentShape = storage.get(name);
        currentShape.move(dx,dy);
        System.out.println(name + " has moved");
        return 1;
    }

    /**
     * @param x 1
     * @param y 1
     * @param dx 1
     * @param dy 1
     * @return 1
     */
    //This method receive the point(x,y)
    //move dx and dy for what contain the point and it has the largest zOrder
    //no shape will move if no shapes contains the point
    //return 0 for fail and 1 for success
    public int pickMove(double x, double y, double dx, double dy) {
        Shape currentShape = findShape(x,y);
        if(currentShape == null) {
            System.out.println("There is no shape that contained the (" + x + "," + y + ")");
            return 0;
        }
        System.out.println(currentShape.getName());
        currentShape.move(dx,dy);
        System.out.println("target has moved");
        return 1;

    }

    /**
     * @param name1 1
     * @param name2 1
     * @return 1
     */
    //This method receive two names of shapes
    //print out whether the two shapes is intersected
    //can not print if one of the name can not find the shape
    //return 0 for fail and 1 for success
    public boolean  intersect(String name1, String name2) {
        if(!storage.containsKey(name1) || !storage.containsKey(name2)) {
            System.out.println("There is no such shapes");
            return false;
        }
        Shape sha1=storage.get(name1);
        Shape sha2=storage.get(name2);
        if(sha1.getClass().getSimpleName().equals("Line")){
            Line lin=(Line)sha1;
            if(sha2.getClass().getSimpleName().equals("Line") ){
                Line temp=(Line)sha2;
                return intersectLineLine(lin,temp);
            }
            if(sha2.getClass().getSimpleName().equals("Circle")){
                Circle temp=(Circle)sha2 ;
                return intersectLineCircle(lin,temp);
            }
            if(sha2.getClass().getSimpleName().equals("Rectangle") ){
                Rectangle temp=(Rectangle)sha2;
                return intersectLineRectangle(lin,temp);
            }
            if(sha2.getClass().getSimpleName().equals("Square") ){
                Square temp=(Square)  sha2;
                return intersectLineSquare(lin,temp);
            }
        }
        if(sha1.getClass().getSimpleName().equals("Circle") ){
            Circle  cir=(Circle)  sha1;
            if(sha2.getClass().getSimpleName().equals("Line")){
                Line temp=(Line)sha2;
                return intersectLineCircle(temp,cir) ;
            }
            if(sha2.getClass().getSimpleName().equals("Circle")){
                Circle temp=(Circle)sha2 ;
                return intersectCircleCircle(cir,temp);
            }
            if(sha2.getClass().getSimpleName().equals("Rectangle")){
                Rectangle temp=(Rectangle)sha2;
                return intersectCircleRectangle(cir,temp);
            }
            if(sha2.getClass().getSimpleName().equals("Square") ){
                Square temp=(Square)  sha2;
                return intersectCircleSquare(cir,temp);
            }
        }
        if(sha1.getClass().getSimpleName().equals("Rectangle") ) {
            Rectangle  rec = (Rectangle)  sha1;
            if (sha2.getClass().getSimpleName().equals("Line")) {
                Line temp = (Line) sha2;
                return intersectLineRectangle(temp, rec) ;
            }
            if (sha2.getClass().getSimpleName().equals("Circle")) {
                Circle temp = (Circle) sha2;
                return intersectCircleRectangle(temp, rec) ;
            }
            if (sha2.getClass().getSimpleName().equals("Rectangle")) {
                Rectangle temp = (Rectangle) sha2;
                return intersectRectangleRectangle(rec, temp);
            }
            if (sha2.getClass().getSimpleName().equals("Square")) {
                Square temp = (Square) sha2;
                return intersectSquareRectangle(temp, rec) ;
            }
        }
        if(sha1.getClass().getSimpleName().equals("Square") ) {
            Square squ = (Square) sha1;
            if (sha2.getClass().getSimpleName().equals("Line")) {
                Line temp = (Line) sha2;
                return intersectLineSquare(temp, squ);
            }
            if (sha2.getClass().getSimpleName().equals("Circle")) {
                Circle temp = (Circle) sha2;
                return intersectCircleSquare(temp, squ);
            }
            if (sha2.getClass().getSimpleName().equals("Rectangle")) {
                Rectangle temp = (Rectangle) sha2;
                return intersectSquareRectangle(squ, temp);
            }
            if (sha2.getClass().getSimpleName().equals("Square")) {
                Square temp = (Square) sha2;
                return intersectSquareSquare(squ, temp);
            }
        }
        else if(sha1.getClass().getSimpleName().equals("Group") && !(sha2.getClass().getSimpleName().equals("Group"))){
            Group temp=(Group)  sha1;
            for(String a: temp.getAllShapes() .keySet() ){
                storage .put(a,temp.getAllShapes().get(a) );
                if(intersect(a, name2)){
                    storage .remove(a);
                    return true;
                }
                storage .remove(a);
            }
        }
        else if(sha2.getClass().getSimpleName().equals("Group") && !(sha1.getClass().getSimpleName().equals("Group") )){
            Group temp=(Group)  sha2;
            for(String a: temp.getAllShapes() .keySet() ){
                storage .put(a,temp.getAllShapes().get(a) );
                if(intersect(a, name2)){
                    storage .remove(a);
                    return true;
                }
                storage .remove(a);
            }
        }

        else if(sha1.getClass().getSimpleName().equals("Group") && (sha2.getClass().getSimpleName().equals("Group"))){
            Group temp1=(Group)  sha1;
            Group temp2=(Group) sha2 ;
            for(String a:temp1.getAllShapes() .keySet() ){
                storage .put(a,temp1 .getAllShapes() .get(a) );
                for(String b:temp2.getAllShapes() .keySet() ){
                    storage .put(b,temp2.getAllShapes() .get(b) );
                    if(intersect(a,b) ){
                        storage .remove(b);
                        storage .remove(a);
                        return true;
                    }
                }
                storage .remove(a);
            }
        }
        return false;
    }


    /**
     * @param line1  1
     * @param line2 1
     * @return 1
     */
    public boolean intersectLineLine(Line line1,Line line2){
        if(Math.max(line1.getX1() ,line1.getX2() )<Math .min(line2.getX1(),line2.getX2()) ||
                Math .max(line1.getY1() ,line1.getY2() )<Math .min(line2.getY1() ,line2.getY2() ) ||
                Math .max(line2.getY1() ,line2.getY2() )<Math .min(line1.getY1() ,line1.getY2() )||
                Math.max(line2.getX1() ,line2.getX2() )<Math .min(line1.getX1(),line1.getX2()) ){
            return false;
        }
        if ((((line1.getX1() - line2.getX1()) * (line2.getY2() - line2.getY1()) - (line1.getY1() - line2.getY1()) * (line2.getX2() - line2.getX1()))
                * ((line1.getX2() - line2.getX1()) * (line2.getY2() - line2.getY1()) - (line1.getY2() - line2.getY1()) * (line2.getX2() - line2.getX1()))) > 0
                || (((line2.getX1() - line1.getX1()) * (line1.getY2() - line1.getY1()) - (line2.getY1() - line1.getY1()) * (line1.getX2() - line1.getX1()))
                * ((line2.getX2() - line1.getX1()) * (line1.getY2() - line1.getY1()) - (line2.getY2() - line1.getY1()) * (line1.getX2() - line1.getX1()))) > 0)
        {
            return false;
        }
        return true;

    }

    /**
     * @param line  1
     * @param cir 1
     * @return 1
     */
    public boolean intersectLineCircle(Line line,Circle cir){
        boolean flag1=(Math .pow(cir .getX() -line .getX1(),2 )+Math .pow(cir .getY() -line .getY1() ,2) )<Math .pow(cir.getR(),2);
        boolean flag2=(Math .pow(cir .getX() -line .getX2(),2 )+Math .pow(cir .getY() -line .getY2() ,2) )<Math .pow(cir.getR(),2);
        if(flag1 &&flag2 ){
            return false;
        }else if(flag1 || flag2 ){
            return true;
        }else{
            double A,B,C,dist1,dist2,angle1,angle2;
            A=line.getY1() -line.getY2() ;
            B=line.getX2() -line.getX1() ;
            C=line.getX1() *line.getY2() -line.getX2() *line.getY1() ;
            dist1=A*cir .getX() +B* cir.getY()+C;
            dist1= Math.pow(dist1 ,2);
            dist2=(Math .pow(A,2)+Math .pow(B,2) )* Math.pow(cir.getR() ,2);
            if(dist1 >dist2 ){
                return false;
            }
            angle1 =(cir .getX() -line .getX1() )*(line .getX2() -line.getX1() )+(cir .getY() -line.getY1() )*(line .getY2() -line.getY1() );
            angle2 =(cir .getX() -line .getX2() )*(line .getX1() -line.getX2() )+(cir .getY() -line.getY2() )*(line .getY1() -line.getY2() );
            if(angle1 >0&&angle2 >0){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * @param cir1  1
     * @param cir2 1
     * @return 1
     */
    public boolean intersectCircleCircle(Circle cir1,Circle cir2){
        double dist= Math.pow(Math .pow(cir1.getX()-cir2.getX() ,2)+ Math.pow(cir1.getY()- cir2.getY(),2),B);
        double maxR= Math.max(cir1 .getR() ,cir2 .getR() );
        double minR= Math.min(cir1.getR() , cir2.getR());
        if(dist>maxR -minR && dist<maxR +minR ){
            return true;
        }else{
            return false;
        }

    }

    /**
     * @param line  1
     * @param rec 1
     * @return 1
     */
    public boolean intersectLineRectangle(Line line,Rectangle rec){
        Line temp1=new Line("temp1",rec.getX() ,rec.getY() ,rec.getX() +rec.getW() ,rec.getY() );
        Line temp2=new Line("temp2",rec.getX() ,rec.getY() ,rec.getX() ,rec.getY() -rec.getH() );
        Line temp3=new Line("temp3",rec.getX() ,rec.getY() -rec.getH() ,rec.getX() +rec.getW() ,rec.getY() -rec.getH() );
        Line temp4=new Line("temp4",rec.getX() +rec.getW() ,rec.getY() ,rec.getX() +rec.getW() ,rec.getY() -rec.getH() );
        return intersectLineLine(line,temp1 )||intersectLineLine(line,temp2) ||intersectLineLine(line,temp3)||intersectLineLine(line,temp4);

    }

    /**
     * @param rec1 1
     * @param rec2 1
     * @return 1
     */
    public boolean intersectRectangleRectangle(Rectangle rec1,Rectangle rec2){
        Line temp1=new Line("temp1",rec1.getX() ,rec1.getY() ,rec1.getX() +rec1.getW() ,rec1.getY() );
        Line temp2=new Line("temp2",rec1.getX() ,rec1.getY() ,rec1.getX() ,rec1.getY() -rec1.getH() );
        Line temp3=new Line("temp3",rec1.getX() ,rec1.getY() -rec1.getH() ,rec1.getX() +rec1.getW() ,rec1.getY() -rec1.getH() );
        Line temp4=new Line("temp4",rec1.getX() +rec1.getW() ,rec1.getY() ,rec1.getX() +rec1.getW() ,rec1.getY() -rec1.getH() );
        return intersectLineRectangle(temp1,rec2)|| intersectLineRectangle(temp2,rec2)||intersectLineRectangle(temp3,rec2)||intersectLineRectangle(temp4,rec2);
    }

    /**
     * @param cir  1
     * @param rec 1
     * @return 1
     */
    public boolean intersectCircleRectangle(Circle cir,Rectangle rec){
        Line temp1=new Line("temp1",rec.getX() ,rec.getY() ,rec.getX() +rec.getW() ,rec.getY() );
        Line temp2=new Line("temp2",rec.getX() ,rec.getY() ,rec.getX() ,rec.getY() -rec.getH() );
        Line temp3=new Line("temp3",rec.getX() ,rec.getY() -rec.getH() ,rec.getX() +rec.getW() ,rec.getY() -rec.getH() );
        Line temp4=new Line("temp4",rec.getX() +rec.getW() ,rec.getY() ,rec.getX() +rec.getW() ,rec.getY() -rec.getH() );
        return intersectLineCircle(temp1,cir )|| intersectLineCircle(temp2,cir )||intersectLineCircle(temp3,cir )||intersectLineCircle(temp4,cir );

    }

    /**
     * @param lin  1
     * @param squ 1
     * @return 1
     */
    public boolean intersectLineSquare(Line lin,Square squ){
        Rectangle temp=new Rectangle("temp",squ.getX() ,squ.getY() ,squ.getW() ,squ.getL() );
        return intersectLineRectangle(lin,temp);

    }

    /**
     * @param squ1 1
     * @param squ2 1
     * @return 1
     */
    public boolean intersectSquareSquare(Square squ1,Square squ2){
        Rectangle temp1=new Rectangle("temp1",squ1.getX() ,squ1.getY() ,squ1.getW() ,squ1.getL() );
        Rectangle temp2=new Rectangle("temp2",squ2.getX() ,squ2.getY() ,squ2.getW() ,squ2.getL() );
        return intersectRectangleRectangle(temp1,temp2);

    }

    /**
     * @param cir  1
     * @param squ 1
     * @return 1
     */
    public boolean intersectCircleSquare(Circle cir,Square squ){
        Rectangle temp=new Rectangle("temp",squ.getX() ,squ.getY() ,squ.getW() ,squ.getL() );
        return intersectCircleRectangle(cir,temp);

    }

    /**
     * @param squ  1
     * @param rec 1
     * @return 1
     */
    public boolean intersectSquareRectangle(Square squ,Rectangle rec){
        Rectangle temp=new Rectangle("temp",squ.getX() ,squ.getY() ,squ.getL() ,squ.getL() );
        return intersectRectangleRectangle(temp,rec);
    }

    /**
     * @param name  1
     * @return 1
     */
    //This method receive the name of a shape
    //print out the information of the shape
    //can not print if the name does not exist
    //return 0 for fail and 1 for success
    public int list(String name) {
        if (!storage.containsKey(name)) {
            System.out.println("There is no such shape");
            return 0;
        }
        Shape currentShape = storage.get(name);
        if(currentShape.getClass().getSimpleName().equals("Rectangle")) {
            listRectangle(currentShape);
        }
        else if(currentShape.getClass().getSimpleName().equals("Line")) {
            listLine(currentShape);
        }
        else if(currentShape.getClass().getSimpleName().equals("Circle")) {
            listCircle(currentShape);
        }
        else if(currentShape.getClass().getSimpleName().equals("Square")) {
            listSquare(currentShape);
        }
        else if (currentShape.getClass().getSimpleName().equals("Group")) {
            listGroup(currentShape);
        }
        return 1;

    }

    /**
     *
     */
    //This method will print all the shape information in the storage
    public void listAll() {
        ArrayList<Shape> shapes = new ArrayList<>();
        Shape currentShape = null;
        Iterator it = storage.values().iterator();
        while (it.hasNext()){
            currentShape = (Shape) it.next();
            shapes.add(currentShape);
        }
        Shape[] a = shapes.toArray(new Shape[0]);
        //sort the shape according to the z-order
        mergeSort(a,0,a.length-1);
        for(int i = 0; i < a.length; i++) {
            currentShape = a[i];
            System.out.println(i+1 + ": ");
            if(currentShape.getClass().getSimpleName().equals("Rectangle")) {
                listRectangle(currentShape);
            }
            else if(currentShape.getClass().getSimpleName().equals("Line")) {
                listLine(currentShape);
            }
            else if(currentShape.getClass().getSimpleName().equals("Circle")) {
                listCircle(currentShape);
            }
            else if(currentShape.getClass().getSimpleName().equals("Square")) {
                listSquare(currentShape);
            }
            else if (currentShape.getClass().getSimpleName().equals("Group")) {
                listGroup(currentShape);
            }

        }


    }

    /**
     * @param currentShape  1
     */
    //List the information of rectangle
    public void listRectangle(Shape currentShape) {
        System.out.print("Rectangle: ");
        System.out.println("name:" + ((Rectangle)currentShape).getName() + " The top-left point:(" + df.format(((Rectangle)currentShape).getX()) + "," + df.format(((Rectangle)currentShape).getY())+ ")" + " w:" + df.format(((Rectangle) currentShape).getW()) + " h:" + df.format(((Rectangle) currentShape).getH()));
    }

    /**
     * @param currentShape  1
     */
    //List the information of line
    public void listLine(Shape currentShape) {
        System.out.print("Line: ");
        System.out.println("name:" + ((Line)currentShape).getName() + " The two nodes are:(" +  df.format(((Line)currentShape).getX1()) + "," + df.format(((Line)currentShape).getY1())+ ") , (" + df.format(((Line)currentShape).getX2()) + "," + df.format(((Line)currentShape).getY2()) + ")");
    }

    /**
     * @param currentShape 1
     */
    //List the information of Circle
    public void listCircle(Shape currentShape) {
        System.out.print("Circle: ");
        System.out.println("name:" + ((Circle)currentShape).getName() + " Center of circle:(" + df.format(((Circle)currentShape).getX()) + "," + df.format(((Circle)currentShape).getY())+ ")" + " r:" + df.format(((Circle)currentShape).getR()));
    }

    /**
     * @param currentShape 1
     */
    //List the information of Square
    public void listSquare(Shape currentShape) {
        System.out.print("Square: ");
        System.out.println("name:" + ((Square) currentShape).getName() + " The top-left point:(" + df.format(((Square)currentShape).getX()) + "," + df.format(((Square)currentShape).getY())+ ")" + " l:" + df.format(((Square)currentShape).getL()) );
    }

    /**
     * @param currentShape 1
     */
    //List the information of Group
    public void listGroup(Shape currentShape) {
        System.out.println("The content in the group " + currentShape.getName()+ " is: ");
        HashMap<String,Shape> shapes = ((Group)currentShape).getShapes();
        Shape oneShape = null;
        Iterator it = shapes.values().iterator();
        int i = 1;
        while (it.hasNext()) {
            oneShape = (Shape) it.next();
            if(oneShape.getClass().getSimpleName().equals("Rectangle")) {
                System.out.print("    ("+i + "): ");
                listRectangle(oneShape);
            }
            else if(oneShape.getClass().getSimpleName().equals("Line")) {
                System.out.print("    ("+i + "): ");
                listLine(oneShape);
            }
            else if(oneShape.getClass().getSimpleName().equals("Circle")) {
                System.out.print("    ("+i + "): ");
                listCircle(oneShape);
            }
            else if(oneShape.getClass().getSimpleName().equals("Square")) {
                System.out.print("    ("+i + "): ");
                listSquare(oneShape);
            }
            else if (oneShape.getClass().getSimpleName().equals("Group")) {
                System.out.print("    ("+i + "): " + "Group: ");
                System.out.println("This is a group shape and name is:" + ((Group)oneShape).getName());
            }
            i++;
        }

    }

    /**
     * @param x 1
     * @param y 1
     * @return 1
     */
    //This method will return shape with biggest z-order contain the point(x,y)
    //return null if no
    public Shape findShape(double x, double y){
        ArrayList<Shape> shapes = new ArrayList<>();
        Shape currentShape = null;
        Iterator it = storage.values().iterator();
        while (it.hasNext()){
            currentShape = (Shape) it.next();
            if(currentShape.isContainPoint(x,y)) {
                shapes.add(currentShape);
            }
        }
        if(shapes.isEmpty()) return null;
        Shape[] shapesList  = shapes.toArray(new Shape[0]);
        mergeSort(shapesList,0,shapesList.length-1);

        return shapesList[0];

    }

    /**
     * @param name  1
     * @return 1
     */
    //This method will return shape with the name
    //also consider all the simple shapes in the group
    //return null if no
    public Shape findShape(String name){
        Shape currentShape = null;
        if(storage.containsKey(name)) {
            return storage.get(name);
        }

        Iterator it = storage.values().iterator();
        while (it.hasNext()){
            currentShape = (Shape) it.next();
            if(currentShape.getClass().getSimpleName().equals("Group")) {
                HashMap<String,Shape> allShapes = ((Group)currentShape).getAllShapes();
                if(allShapes.containsKey(name)){
                    return allShapes.get(name);
                }
            }
        }
        return null;
    }


    /**
     * @param str 1
     * @return 1
     */
    public boolean isAllNumber(String str) {
        char[] a = str.toCharArray();

        for(int i = 0 ; i< a.length;i++) {
            if(i == 0 && a[i] == '-') continue;
            if(!Character.isDigit(a[i]) && a[i] != '.') {
                return false;
            }
        }
        return true;
    }

    /**
     * @param a 1
     * @param low 1
     * @param high 1
     */
    public void mergeSort(Shape a[], int low, int high) {
        if(low >= high) return;
        int mid = low + (high - low)/2;
        mergeSort(a, low, mid);
        mergeSort(a, mid+1, high);
        merge(a,low,mid,high);
        return;
    }

    /**
     * @param a  1
     * @param low 1
     * @param mid 1
     * @param high 1
     */
    public void merge(Shape a[], int low, int mid, int high) {
        Shape[] left = new Shape[mid-low + 1];
        Shape[] right = new Shape[high - mid];
        int index = low;
        for(int i = 0; i < mid-low+1; i++) left[i] = a[index++];
        for(int i = 0; i < high-mid; i++) right[i] = a[index++];
        int leftIndex = 0, rightIndex = 0;
        index = low;
        while(leftIndex < mid-low+1 && rightIndex < high-mid) {
            a[index++] = left[leftIndex].getThisZOrder() > right[rightIndex].getThisZOrder()? left[leftIndex++] : right[rightIndex++];
        }
        while(leftIndex < mid - low+1) a[index++] = left[leftIndex++];
        while(rightIndex < high-mid) a[index++] = right[rightIndex++];
    }

    /**
     * @param newStorage 1
     */
    public void setStorage(HashMap<String,Shape> newStorage) {
        this.storage = newStorage;
    }

}
