
import Code.model.shape.Circle;
import Code.model.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CircleTest {
    Circle a;
    @BeforeEach
    public void before(){
        a = new Circle("test1",0,0,1);
    }

    @Test
    public void boundingBoxTest(){
        Rectangle b = a.boundingBox();
        assertEquals(2,b.getH());
        assertEquals(2,b.getW());
        assertEquals(-1,b.getX());
        assertEquals(1,b.getY());
    }
    @Test
    public void isContainPointTest(){
        assertTrue(a.isContainPoint(0,1));
        assertTrue(a.isContainPoint(1,0));
        assertFalse(a.isContainPoint(0,0.5));
    }
    @Test
    public void moveTest(){
        a.move(2,4);
        assertEquals(2,a.getX());
        assertEquals(2,a.getY());
        assertEquals(1,a.getR());
    }

}
