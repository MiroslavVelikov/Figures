package test.java.com.bg.sofia.uni.fmi.figures.factories.convertor;

import main.java.com.bg.sofia.uni.fmi.figures.factories.convertor.StringToFigure;
import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringToFigureTest {

    private static StringToFigure stringToFigure;

    @BeforeAll
    public static void setUp() {
        stringToFigure = StringToFigure.getInstance();
    }

    @Test
    public void testCreateValidCircle() {
        String representation = "circle 10.5";
        Figure circle = stringToFigure.createFrom(representation);
        assertNotNull(circle);
    }

    @Test
    public void testCreateValidRectangle() {
        String representation = "rectangle 10.5 40.1";
        Figure rectangle = stringToFigure.createFrom(representation);
        assertNotNull(rectangle);
    }

    @Test
    public void testCreateValidTriangle() {
        String representation = "triangle 10 20 30";
        Figure triangle = stringToFigure.createFrom(representation);
        assertNotNull(triangle);
    }

    @Test
    public void testInvalidStringFormat() {
        String representation = "unknow 10 20 30";
        assertThrows(IllegalArgumentException.class, () -> stringToFigure.createFrom(representation));
    }

    @Test
    public void testEmptyString() {
        String representation = "";
        Figure result = stringToFigure.createFrom(representation);
        assertNull(result);
    }

    @Test
    public void testInvalidStringConverter() {
        String representation = "triangle 10 abc 30";
        assertThrows(IllegalArgumentException.class, () -> stringToFigure.createFrom(representation));
    }

}
