package test.java.com.bg.sofia.uni.fmi.figures.models;

import main.java.com.bg.sofia.uni.fmi.figures.models.Triangle;
import org.junit.jupiter.api.Test;

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

}
