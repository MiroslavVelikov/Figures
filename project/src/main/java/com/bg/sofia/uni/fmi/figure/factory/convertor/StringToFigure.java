package main.java.com.bg.sofia.uni.fmi.figure.factory.convertor;

import main.java.com.bg.sofia.uni.fmi.figure.factory.figure.CreatorType;
import main.java.com.bg.sofia.uni.fmi.figure.factory.figure.FigureCreator;
import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringToFigure {

    private static final int FIGURE_TYPE_POS = 0;

    private static StringToFigure instance;

    private final Map<String, FigureCreator> creators;

    private StringToFigure() {
        creators = new HashMap<>();

        for (CreatorType type : CreatorType.values()) {
            creators.put(type.getFigure(), type.getCreator());
        }
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
            throw new IllegalArgumentException("incorrect input format");
        }
    }

    public static StringToFigure getInstance() {
        if (instance == null) {
            instance = new StringToFigure();
        }

        return instance;
    }

}
