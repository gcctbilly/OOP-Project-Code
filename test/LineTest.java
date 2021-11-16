import Code.model.shape.Line;
import Code.model.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class LineTest {
    Line a;
    Line b;
    @BeforeEach
    public void before(){
        a = new Line("test",0,0,1,0);
    }

    @Test
    //need more
    public void boundingBoxTest(){
        Rectangle c = a.boundingBox();
        assertEquals(null,c);
    }

    @Test
    public void isContainTest(){
        assertTrue(a.isContainPoint(0,0.5));
        assertTrue(a.isContainPoint(0,0));
        assertFalse(a.isContainPoint(2,0));
    }
    @Test
    public void moveTest(){
        a.move(2,2);
        assertEquals(2,a.getX1());
        assertEquals(2,a.getY1());
        assertEquals(3,a.getX2());
        assertEquals(2,a.getY2());
    }
}
