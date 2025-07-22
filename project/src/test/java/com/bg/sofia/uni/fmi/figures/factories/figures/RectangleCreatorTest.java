package test.java.com.bg.sofia.uni.fmi.figures.factories.figures;

import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.FigureCreator;
import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.RectangleCreator;
import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleCreatorTest {

    private static FigureCreator rectangleCreator;

    @BeforeAll
    public static void setUp() {
        rectangleCreator = new RectangleCreator();
    }

    @Test
    public void testCreateValidRectangle() {
        double[] sides = { 10.5, 20 };
        Figure result = rectangleCreator.createFigure(sides);
        assertNotNull(result);
    }

    @Test
    void testCreateRectangleParams() {
        assertThrows(IllegalArgumentException.class, () -> rectangleCreator.createFigure());
    }

    @Test
    void testCreateRectangleWithMoreThanEnoughParams() {
        double[] params = { 1.2, 10.5, 10.2 };
        assertThrows(IllegalArgumentException.class, () -> rectangleCreator.createFigure(params));
    }

}
