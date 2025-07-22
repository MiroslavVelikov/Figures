package test.java.com.bg.sofia.uni.fmi.figures.factories.figures;

import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.CircleCreator;
import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.FigureCreator;
import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CircleCreatorTest {

    private static FigureCreator circleCreator;

    @BeforeAll
    public static void setUp() {
        circleCreator = new CircleCreator();
    }

    @Test
    public void testCreateValidCircle() {
        double radius = 10.5;
        Figure result = circleCreator.createFigure(radius);
        assertNotNull(result);
    }

    @Test
    void testCreateCircleWithoutRadius() {
        assertThrows(IllegalArgumentException.class, () -> circleCreator.createFigure());
    }

    @Test
    void testCreateCircleWithMultipleParams() {
        double[] params = { 1.2, 10.5 };
        assertThrows(IllegalArgumentException.class, () -> circleCreator.createFigure(params));
    }

}
