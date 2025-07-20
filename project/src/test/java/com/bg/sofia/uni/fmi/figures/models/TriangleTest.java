package test.java.com.bg.sofia.uni.fmi.figures.models;

import main.java.com.bg.sofia.uni.fmi.figures.models.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {

    @Test
    public void testCreateValidTriangle() {
        Triangle triangle = new Triangle(10, 20, 30);
        assertNotNull(triangle);
    }

    @Test
    public void testCreateTriangleWithInvalidSidesRelation() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(100, 10, 10));
    }

    @Test
    public void testCreateTriangleWithNegativeSides() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-10, 20, 30));
    }

    @Test
    public void testOverflowPerimeter() {
        assertThrows(ArithmeticException.class, () -> new Triangle(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));
    }

    @Test
    public void testToString() {
        Triangle triangle = new Triangle(10.4, 20.1, 30.5);
        assertEquals("triangle 10.40 20.10 30.50", triangle.toString());
    }

    @Test
    public void testCloning() {
        Triangle expected = new Triangle(10.4, 20.1, 30.5);
        Triangle result = expected.clone();

        assertEquals(expected.toString(), result.toString());
        assertEquals(expected.perimeter(), result.perimeter());
    }

}
