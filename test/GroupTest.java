import Code.model.shape.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {
    Group a;
    Group b;

    @BeforeEach
    public void before(){
        HashMap<String,Shape> shapes = new HashMap<>();
        shapes.put("test1",new Rectangle("test1",-1,1,2,2));
        shapes.put("test2", new Square("test2",-1,1,2));
        shapes.put("test3", new Circle("test3",0,0,1));
        shapes.put("test4", new Line("test4",0,0,2,0));
        HashMap<String,Shape> allShapes = new HashMap<>();
        allShapes.putAll(shapes);
        a = new Group("test6",shapes,allShapes);
        shapes = new HashMap<>();
        shapes.put("test5", new Rectangle("test5",1,1,2,2));
        shapes.put("test6",a);
        HashMap<String,Shape> allShapes2 = new HashMap<>();
        allShapes2.putAll(allShapes);
        allShapes2.put("test5", new Rectangle("test5",1,1,2,2));
        b = new Group("test7",shapes,allShapes2);
    }

    @Test
    public void boundingBoxTest(){
        Rectangle boundingBoxa = a.boundingBox();
        Rectangle boundingBoxb = b.boundingBox();
        assertEquals(-1,boundingBoxa.getX());
        assertEquals(1,boundingBoxa.getY());
        assertEquals(3,boundingBoxa.getW());
        assertEquals(2,boundingBoxa.getH());

        assertEquals(-1,boundingBoxb.getX());
        assertEquals(1,boundingBoxb.getY());
        assertEquals(4,boundingBoxb.getW());
        assertEquals(2,boundingBoxb.getH());
    }

    @Test
    public void isContainPointTest(){
        assertTrue(a.isContainPoint(1,1));
        assertTrue(a.isContainPoint(0,1));
        assertFalse(a.isContainPoint(0,0.5));

        assertTrue(b.isContainPoint(1,1));
        assertTrue(b.isContainPoint(0,1));
        assertFalse(b.isContainPoint(0,0.5));

    }
    @Test
    public void move(){
        a.move(2,2);
        assertEquals(2,((Circle)a.getAllShapes().get("test3")).getX());
        assertEquals(2,((Circle)a.getAllShapes().get("test3")).getY());
        assertEquals(2,((Circle)b.getAllShapes().get("test3")).getX());
        assertEquals(2,((Circle)b.getAllShapes().get("test3")).getY());
        assertEquals(3,((Rectangle)b.getAllShapes().get("test5")).getX());
        assertEquals(3,((Rectangle)b.getAllShapes().get("test5")).getY());
    }

}
