package Code.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

import Code.model.shape.*;

public class Clevis {
    private HashMap<String, Shape> storage;
    private String filePathHtml ="";
    private String filePathTxt = "";
    private int operationIndex = 0;

    public Clevis(){
        storage = new HashMap<>();
    }

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
            createGroup(name,addName);
            return 1;
        }
        else if(commands[0].equals("ungroup")) {
            if (commands.length != 2) {
                System.out.println("Please enter the right command");
                return 0;
            }
            String name = commands[1];
            unGroup(name);
            return 1;
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
            double x = Double.parseDouble(commands[2]);
            double y = Double.parseDouble(commands[3]);
            double dx = Double.parseDouble(commands[4]);
            double dy = Double.parseDouble(commands[5]);
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
            if(isSuccess) return 1;
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
        System.out.println("There is no such command" + command);
        return 0;


    }

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

    //write command in the html
    public void writeFileHtml(String command) {
        File file = new File(filePathHtml);
        try{
            RandomAccessFile randomFile = new RandomAccessFile(file,"rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength-16);
            //store the remained content in order to add back after write
            String remained = randomFile.readLine();
            randomFile.seek(fileLength-16);
            String processedCommand = "<tr><td>"+operationIndex +"</td><td>" + command + "</td></tr>";
            randomFile.write((processedCommand+remained).getBytes(StandardCharsets.UTF_8));

            System.out.println(fileLength);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }




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
        System.out.println("Line created successfully");
        return 1;
    }

    // This method receive information of group and add group to storage
    // the shape that will be grouped will not exist in the storage
    // can not create if the name has existed;
    //return 0 for fail and 1 for success
    public int createGroup(String name, String[] addName) {
        HashMap<String,Shape> add = new HashMap<>();
        HashMap<String,Shape> addAll = new HashMap<>();
        for(int i = 0; i < addName.length; i++) {
            if (storage.containsKey(addName[i])) {
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
        return 1;

    }

    // This method receive name of a group
    // the shape that will be ungrouped need to add to storage
    // the group will delete from the storage
    // can not ungroup if the name does not exist or it is not a group
    //return 0 for fail and 1 for success

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
        }
        storage.remove(name);
        return 1;

    }

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
        System.out.println("name: " + boundingBox.getName() + " x: " + boundingBox.getX() + " y: " + boundingBox.getY() + " w: " + boundingBox.getW() + " h: " + boundingBox.getH());
        return 1;
    }

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
        currentShape.move(dx,dy);
        System.out.println("target has moved");
        return 1;

    }

    //This method receive two names of shapes
    //print out whether the two shapes is intersected
    //can not print if one of the name can not find the shape
    //return 0 for fail and 1 for success
    public boolean intersect(String name1, String name2) {
        if(!storage.containsKey(name1) || !storage.containsKey(name2)) {
            System.out.println("There is no such shapes");
            return false;
        }

        return true;

    }

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
            System.out.println(i + ": ");
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

    //List the information of rectangle
    public void listRectangle(Shape currentShape) {
        System.out.println("The information of the rectangle is: ");
        System.out.println("name: " + ((Rectangle) currentShape).getName() + "The top-left : (" + ((Rectangle)currentShape).getX() + "," + ((Rectangle)currentShape).getY()+ ")" + " w: " + ((Rectangle) currentShape).getW() + " h: " + ((Rectangle) currentShape).getH());
    }

    //List the information of line
    public void listLine(Shape currentShape) {
        System.out.println("The information of the Line is: ");
        System.out.println("name: " + ((Line)currentShape).getName() + " The two nodes are: (" +  ((Line)currentShape).getX1() + "," + ((Line)currentShape).getY1()+ ") , (" + ((Line)currentShape).getX2() + "," + ((Line)currentShape).getY2() + ")");
    }

    //List the information of Circle
    public void listCircle(Shape currentShape) {
        System.out.println("The information of the circle is: ");
        System.out.println("name: " + ((Circle)currentShape).getName() + "Center of circle is: (" + ((Circle)currentShape).getX() + "," + ((Circle)currentShape).getY()+ ")" + "r: " + ((Circle)currentShape).getR());
    }

    //List the information of Square
    public void listSquare(Shape currentShape) {
        System.out.println("The information of the Square is: ");
        System.out.println("name: " + ((Square) currentShape).getName() + "The top-left : (" + ((Square)currentShape).getX() + "," + ((Square)currentShape).getY()+ ")" + "l:" + ((Square)currentShape).getL() );
    }

    //List the information of Group
    public void listGroup(Shape currentShape) {
        System.out.println("The information of the group " + ((Group)currentShape).getName()+ " is: ");
        HashMap<String,Shape> shapes = ((Group)currentShape).getShapes();
        Shape oneShape = null;
        Iterator it = shapes.values().iterator();
        int i = 1;
        while (it.hasNext()) {
            oneShape = (Shape) it.next();
            if(oneShape.getClass().getSimpleName().equals("Rectangle")) {
                System.out.print("    "+i + ": ");
                listRectangle(oneShape);
            }
            else if(currentShape.getClass().getSimpleName().equals("Line")) {
                System.out.print("    "+i + ": ");
                listLine(oneShape);
            }
            else if(currentShape.getClass().getSimpleName().equals("Circle")) {
                System.out.print("    "+i + ": ");
                listCircle(oneShape);
            }
            else if(currentShape.getClass().getSimpleName().equals("Square")) {
                System.out.print("    "+i + ": ");
                listSquare(oneShape);
            }
            else if (currentShape.getClass().getSimpleName().equals("Group")) {
                System.out.print("    "+i + ": " + "Group");
                System.out.println("This is a group shape and name is:" + ((Group)oneShape).getName());
            }
            i++;
        }

    }

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


    public boolean isAllNumber(String str) {
        char[] a = str.toCharArray();
        for(int i = 0 ; i< a.length;i++) {
            if(!Character.isDigit(a[i]) && a[i] != '.') {
                return false;
            }
        }
        return true;
    }

    public void mergeSort(Shape a[], int low, int high) {
        if(low >= high) return;
        int mid = low + (high - low)/2;
        mergeSort(a, low, mid);
        mergeSort(a, mid+1, high);
        merge(a,low,mid,high);
        return;
    }

    public void merge(Shape a[], int low, int mid, int high) {
        Shape[] left = new Shape[mid-low + 1];
        Shape[] right = new Shape[high - mid];
        int index = low;
        for(int i = 0; i < mid-low+1; i++) left[i] = a[index++];
        for(int i = 0; i < high-mid; i++) right[i] = a[index++];
        int leftIndex = 0, rightIndex = 0;
        index = low;
        while(leftIndex < mid-low+1 && rightIndex < high-mid) {
            a[index++] = left[leftIndex].getzOrder() > right[rightIndex].getzOrder()? left[leftIndex++] : right[rightIndex++];
        }
        while(leftIndex < mid - low+1) a[index++] = left[leftIndex++];
        while(rightIndex < high-mid) a[index++] = right[rightIndex++];
    }


}
