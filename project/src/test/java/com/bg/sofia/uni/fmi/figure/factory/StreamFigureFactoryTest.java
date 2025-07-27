package test.java.com.bg.sofia.uni.fmi.figure.factory;

import main.java.com.bg.sofia.uni.fmi.figure.factory.StreamFigureFactory;
import main.java.com.bg.sofia.uni.fmi.figure.factory.convertor.StringToFigure;
import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StreamFigureFactoryTest {

    @Mock
    private StringToFigure mockConverter;

    @BeforeEach
    void setup() {
        StringToFigure.setInstance(mockConverter);
    }

    @Test
    void testCreateValidFigure() {
        Figure mockFigure = mock(Figure.class);

        when(mockConverter.createFrom("circle 5.1"))
            .thenReturn(mockFigure);

        StreamFigureFactory factory = new StreamFigureFactory(Stream.of("circle 5.1"));
        Figure figure = factory.create();

        assertNotNull(figure);
        assertEquals(mockFigure, figure);
    }

    @Test
    void testCreateReturnsNullOnEmptyStream() {
        StreamFigureFactory factory = new StreamFigureFactory(Stream.empty());
        Figure figure = factory.create();

        assertNull(figure);
    }

    @Test
    void testCreateReturnsNullOnInvalidInput() {
        when(mockConverter.createFrom("unknown"))
            .thenThrow(new RuntimeException("Invalid"));

        StreamFigureFactory factory = new StreamFigureFactory(Stream.of("unknown"));

        Figure figure = factory.create();

        assertNull(figure);
    }

    @Test
    void testMultipleLines() {
        Figure mock1 = mock(Figure.class);
        Figure mock2 = mock(Figure.class);

        when(mockConverter.createFrom("circle 5.3")).thenReturn(mock1);
        when(mockConverter.createFrom("triangle 10 20 30")).thenReturn(mock2);

        StreamFigureFactory factory = new StreamFigureFactory(Stream.of("circle 5.3", "triangle 10 20 30"));

        assertEquals(mock1, factory.create());
        assertEquals(mock2, factory.create());
        assertNull(factory.create());
    }

}
