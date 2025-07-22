package main.java.com.bg.sofia.uni.fmi.figures.factories;

import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.CircleCreator;
import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.FigureCreator;
import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.RectangleCreator;
import main.java.com.bg.sofia.uni.fmi.figures.factories.figures.TriangleCreator;
import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringToFigure {

    private static final int FIGURE_TYPE_POS = 0;

    private static StringToFigure instance;

    private final Map<String, FigureCreator> creators;

    private StringToFigure() {
        creators = new HashMap<>();
        creators.put("triangle", new TriangleCreator());
        creators.put("circle", new CircleCreator());
        creators.put("rectangle", new RectangleCreator());
    }

    public Figure createFrom(String representation) {
        if (representation.isEmpty()) {
            return null;
        }

        String[] args = representation.split(" ");
        String figureType = args[FIGURE_TYPE_POS];

        try {
            return creators.get(figureType).createFigure(
                Arrays.stream(Arrays.copyOfRange(args, 1, args.length))
                .mapToDouble(Double::parseDouble)
                .toArray()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static StringToFigure getInstance() {
        if (instance.creators.isEmpty()) {
            instance = new StringToFigure();
        }

        return instance;
    }

}
