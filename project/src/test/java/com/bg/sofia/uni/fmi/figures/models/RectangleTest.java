package test.java.com.bg.sofia.uni.fmi.figures.models;

import main.java.com.bg.sofia.uni.fmi.figures.models.Rectangle;
import org.junit.jupiter.api.Test;

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

}
