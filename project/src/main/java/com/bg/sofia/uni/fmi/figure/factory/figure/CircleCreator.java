package main.java.com.bg.sofia.uni.fmi.figure.factory.figure;

import main.java.com.bg.sofia.uni.fmi.figure.model.Circle;
import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;

public class CircleCreator implements FigureCreator {

    private final static int REQUIRED_PARAMS_COUNT = 1;
    private final static int RADIUS_POS = 0;

    @Override
    public Figure createFigure(double... params) {
        if (params.length != REQUIRED_PARAMS_COUNT) {
            throw new IllegalArgumentException(String.format("expected required parameters (%d), got (%d)",
                REQUIRED_PARAMS_COUNT, params.length));
        }

        double radius = params[RADIUS_POS];
        return new Circle(radius);
    }

    @Override
    public int requiredParameters() {
        return REQUIRED_PARAMS_COUNT;
    }

}
