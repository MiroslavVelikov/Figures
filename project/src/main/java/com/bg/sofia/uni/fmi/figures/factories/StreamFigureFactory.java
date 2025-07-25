package main.java.com.bg.sofia.uni.fmi.figures.factories;

import main.java.com.bg.sofia.uni.fmi.figures.factories.convertor.StringToFigure;
import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;

import java.util.Iterator;
import java.util.stream.Stream;

public class StreamFigureFactory implements FigureFactory {

    private static final StringToFigure convertor = StringToFigure.getInstance();

    private final Iterator<String> inputIter;

    public StreamFigureFactory(Stream<String> input) {
        this.inputIter = input.iterator();
    }

    @Override
    public Figure create() {
        String line = inputIter.hasNext() ? inputIter.next() : null;

        try {
            return line != null ? convertor.createFrom(line) : null;
        } catch (Exception e) {
            return null;
        }
    }

}
