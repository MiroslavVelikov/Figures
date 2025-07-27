package test.java.com.bg.sofia.uni.fmi.figure.factory;

import main.java.com.bg.sofia.uni.fmi.figure.factory.RandomFigureFactory;
import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RandomFigureFactoryTest {

    RandomFigureFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RandomFigureFactory();
    }

    @Test
    void testCreateReturnsValidFigure() {
        Figure figure = factory.create();

        assertNotNull(figure);
    }

    @Test
    void testMultipleCreations() {
        for (int i = 0; i < 100; i++) {
            Figure figure = factory.create();
            assertNotNull(figure);
        }
    }

}
