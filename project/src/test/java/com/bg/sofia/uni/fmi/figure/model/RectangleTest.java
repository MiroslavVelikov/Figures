package test.java.com.bg.sofia.uni.fmi.figure.model;

import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;
import main.java.com.bg.sofia.uni.fmi.figure.model.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

    @Test
    public void testCreateValidRectangle() {
        Rectangle rectangle = new Rectangle(10, 20);
        assertNotNull(rectangle);
    }

    @Test
    public void testCreateRectangleWithNegativeSide() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-10, 20));
    }

    @Test
    public void testCreateRectangleWithZeroSide() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(10, 0));
    }

    @Test
    public void testOverflowPerimeter() {
        assertThrows(ArithmeticException.class, () -> new Rectangle(Double.MAX_VALUE, 10));
    }

    @Test
    public void testToString() {
        Rectangle rectangle = new Rectangle(10, 20.3);
        assertEquals("rectangle 10.00 20.30", rectangle.toString());
    }

    @Test
    public void testCloning() {
        Rectangle expected = new Rectangle(10, 20.3);
        Figure result = expected.clone();

        assertEquals(expected.toString(), result.toString());
        assertEquals(expected.perimeter(), result.perimeter());
    }

}
