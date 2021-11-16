import Code.model.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RectangleTest {
    Rectangle a;
    @BeforeEach
    public void before(){
        a = new Rectangle("test1",-1,1,2,2);
    }

    @Test
    //need more
    public void boundingBoxTest(){
        Rectangle boundingBox = a.boundingBox();
        assertEquals(-1,boundingBox.getX());
        assertEquals(1,boundingBox.getY());
        assertEquals(2,boundingBox.getW());
        assertEquals(2,boundingBox.getH());
    }

    @Test
    public void isContainPointTest(){
        assertTrue(a.isContainPoint(0,1));
        assertTrue(a.isContainPoint(1,1));
        assertFalse(a.isContainPoint(0,0.5));
    }
    @Test
    public void moveTest(){
        a.move(2,2);
        assertEquals(1,a.getX());
        assertEquals(3,a.getY());
        assertEquals(2,a.getW());
        assertEquals(2,a.getH());
    }
}
