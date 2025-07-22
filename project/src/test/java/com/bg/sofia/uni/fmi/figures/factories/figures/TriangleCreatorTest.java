package test.java.com.bg.sofia.uni.fmi.figures.factories.figures;

import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.FigureCreator;
import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.TriangleCreator;
import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleCreatorTest {

    private static FigureCreator triangleCreator;

    @BeforeAll
    public static void setUp() {
        triangleCreator = new TriangleCreator();
    }

    @Test
    public void testCreateValidTriangle() {
        double[] sides = { 10.5, 20, 29.5 };
        Figure result = triangleCreator.createFigure(sides);
        assertNotNull(result);
    }

    @Test
    void testCreateTriangleParams() {
        assertThrows(IllegalArgumentException.class, () -> triangleCreator.createFigure());
    }

    @Test
    void testCreateTriangleWithMoreThanEnoughParams() {
        double[] params = { 10.5, 20, 29.5, 30.1 };
        assertThrows(IllegalArgumentException.class, () -> triangleCreator.createFigure(params));
    }

}
