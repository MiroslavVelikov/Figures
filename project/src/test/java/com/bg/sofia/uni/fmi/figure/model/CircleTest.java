package test.java.com.bg.sofia.uni.fmi.figure.model;

import main.java.com.bg.sofia.uni.fmi.figure.model.Circle;
import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CircleTest {

    @Test
    public void testCreateValidCircle() {
        Circle circle = new Circle(10.5);
        assertNotNull(circle);
    }

    @Test
    public void testCreateCircleWithNegativeRadius() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(-10.5));
    }

    @Test
    public void testCreateCircleWithRadiusZero() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(0));
    }

    @Test
    public void testOverflowParameter() {
        assertThrows(ArithmeticException.class, () -> new Circle(Double.MAX_VALUE));
    }

    @Test
    public void testToString() {
        Circle circle = new Circle(10.5);
        assertEquals("circle 10.50", circle.toString());
    }

    @Test
    public void testCloning() {
        Circle expected = new Circle(10.5);
        Figure result = expected.clone();

        assertEquals(expected.toString(), result.toString());
        assertEquals(expected.perimeter(), result.perimeter());
    }

}
