import Code.model.Clevis;
import Code.model.shape.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class ClevisTest{
    Rectangle rec1;
    Rectangle rec2;
    Rectangle rec3;
    Square squ1;
    Circle cir1;
    Group gro1;
    Group gro2;
    Line lin1;
    HashMap<String,Shape> storage;

    Clevis application = new Clevis();
    @BeforeEach
    public void before(){
        storage = new HashMap<>();
        rec1 = new Rectangle("test1",-1,1,2,2);
        squ1 = new Square("test2",-1,1,2);
        cir1 = new Circle("test3",0,0,1);
        lin1 = new Line("test4",0,0,2,0);
        rec2 = new Rectangle("test5",1,1,2,2);
        rec3 = new Rectangle("test8",3,1,2,2);

        HashMap<String,Shape> shapes = new HashMap<>();
        shapes.put("test3", cir1);
        shapes.put("test4", lin1);
        HashMap<String,Shape> allShapes = new HashMap<>();
        allShapes.putAll(shapes);
        gro1 = new Group("test6",shapes,allShapes);
        shapes = new HashMap<>();
        shapes.put("test5", rec2);
        shapes.put("test6",gro1);
        HashMap<String,Shape> allShapes2 = new HashMap<>();
        allShapes2.putAll(allShapes);
        allShapes2.put("test5", rec2);
        gro2 = new Group("test7",shapes,allShapes2);

        storage.put("test1",rec1);
        storage.put("test2",squ1);
        storage.put("test7",gro2);
        storage.put("test8",rec3);
        application.storage = storage;

    }
    @Test
    public void FileTest(){
        //test create file
        int number = application.createFile("test1.html","test2.txt");
        int number2 = application.createFile("test1.html","test2.txt");
        assertEquals(1,number);
        assertEquals(0,number2);
        //test write file
        application.writeFileTxt("command1");
        application.writeFileTxt("command2");
        application.writeFileHtml("command1");
        application.writeFileHtml("command2");
    }

    @Test
    public void createRectangleTest(){
        int number1 = application.process("rectan hh 1 2 3 4");
        int number2 = application.process("rectangle hh 1a 2b 3 4");
        int number3 = application.process("rectangle hh 1 2 3 4 5");
        int number4 = application.process("rectangle test3 1 2 3 4");
        int number5 = application.process("rectangle hh 1 2 3 4");
        Rectangle a =(Rectangle)storage.get("hh");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);
        assertEquals(1,a.getX());
        assertEquals(2,a.getY());
        assertEquals(3,a.getW());
        assertEquals(4,a.getH());

    }

    @Test
    public void createLineTest(){
        int number1 = application.process("li hh 1 2 3 4");
        int number2 = application.process("line hh 1a 2b 3 4");
        int number3 = application.process("line hh 1 2 3 4 5");
        int number4 = application.process("line test3 1 2 3 4");
        int number5 = application.process("line hh 1 2 3 4");
        Line a =(Line) storage.get("hh");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);
        assertEquals(1,a.getX1());
        assertEquals(2,a.getY1());
        assertEquals(3,a.getX2());
        assertEquals(4,a.getY2());
    }

    @Test
    public void createCircleTest(){
        int number1 = application.process("cir hh 1 2 3");
        int number2 = application.process("circle hh 1a 2b 3");
        int number3 = application.process("circle hh 1 2 3 4");
        int number4 = application.process("circle test3 1 2 3");
        int number5 = application.process("circle hh 1 2 3");
        Circle a =(Circle) storage.get("hh");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);

        assertEquals(1,a.getX());
        assertEquals(2,a.getY());
        assertEquals(3,a.getR());
    }

    @Test
    public void createSquareTest(){
        int number1 = application.process("squ hh 1 2 3");
        int number2 = application.process("square hh 1a 2b 3");
        int number3 = application.process("square hh 1 2 3 4");
        int number4 = application.process("square test3 1 2 3");
        int number5 = application.process("square hh 1 2 3");
        Square a =(Square) storage.get("hh");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);

        assertEquals(1,a.getX());
        assertEquals(2,a.getY());
        assertEquals(3,a.getL());
    }

    @Test
    public void testCreateGroup(){
        int number1 = application.process("gro hh 1 2 3");
        int number5 = application.process("group hh3 test1 test2");
        int number2 = application.process("group hh aa 2b 3");
        int number3 = application.process("group hh test3 test5");
        int number4 = application.process("group hh2 hh test1");

        Group a =(Group) storage.get("hh");
        Group b = (Group) storage.get("hh2");
        Group c=(Group) storage.get("hh3");

        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);

        assertTrue(c.getAllShapes().containsKey("test1"));
        assertTrue(c.getAllShapes().containsKey("test2"));



    }
    @Test
    public void unGroupTest(){
        int number1 = application.process("ung test7");
        int number2 = application.process("ungroup test1 test2");
        int number3 = application.process("ungroup hh");
        int number4 = application.process("ungroup test7");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(1,number4);

        assertTrue(storage.containsKey("test5"));
        assertTrue(storage.containsKey("test6"));
        application.process("ungroup test6");
        assertTrue(storage.containsKey("test3"));
        assertTrue(storage.containsKey("test4"));
    }

    @Test
    public void deleteTest(){
        int number1 = application.process("del test1");
        int number2 = application.process("delete test1 test2");
        int number3 = application.process("delete test6");
        int number4 = application.process("delete test7");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(1,number4);

        assertFalse(storage.containsKey("test7"));

    }

    @Test
    public void boundingBoxTest(){
        int number1 = application.process("bou test1");
        int number2 = application.process("boundingbox test1 test2");
        int number3 = application.process("boundingbox test6");
        int number4 = application.process("boundingbox test7");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(1,number4);
    }

    @Test
    public void moveTest(){
        int number1 = application.process("mo test1");
        int number2 = application.process("move test1 d2 d3");
        int number3 = application.process("move test1 1 2 3");
        int number4 = application.process("move test6 1 3");
        int number5 = application.process("move test2 1 1");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);

        assertEquals(0,((Square)storage.get("test2")).getX());
        assertEquals(2,((Square)storage.get("test2")).getY());
    }

    @Test
    public void pickMoveTest(){
        int number1 = application.process("pic 0 1 1 1");
        int number2 = application.process("pick-and-move 0x 1x 1 1");
        int number3 = application.process("pick-and-move 0 1 1 1 4 4");
        int number4 = application.process("pick-and-move 0 -5 1 1");
        int number5 = application.process("pick-and-move 0 1 1 1");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);

        assertEquals(1,( (Circle)((Group)storage.get("test7")).getAllShapes().get("test3")).getX()  );
        assertEquals(1,((Circle)((Group)storage.get("test7")).getAllShapes().get("test3")).getY() );
    }

    @Test
    public void intersect(){
        int number1 = application.process("int 0 1 1 1");
        int number2 = application.process("intersect a b c");
        int number3 = application.process("intersect a b");
        int number4 = application.process("intersect test1 test8");
        int number5 = application.process("intersect test1 test2");

        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(0,number4);
        assertEquals(1,number5);
    }

    @Test
    public void listTest(){
        int number1 = application.process("li test1");
        int number2 = application.process("list test1 test2");
        int number3 = application.process("list test9");
        int number4 = application.process("list test1");

        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(0,number3);
        assertEquals(1,number4);
    }

    @Test
    public void listAllTest(){
        int number1 = application.process("li");
        int number2 = application.process("listAll a");
        int number3 = application.process("listAll");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(1,number3);
    }

    @Test
    public void quitTest(){
        int number1 = application.process("q");
        int number2 = application.process("quit a");
        int number3 = application.process("quit");
        assertEquals(0,number1);
        assertEquals(0,number2);
        assertEquals(2,number3);
    }

}
