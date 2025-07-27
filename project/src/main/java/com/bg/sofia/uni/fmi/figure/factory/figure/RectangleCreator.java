package main.java.com.bg.sofia.uni.fmi.figure.factory.figure;

import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;
import main.java.com.bg.sofia.uni.fmi.figure.model.Rectangle;

public class RectangleCreator implements FigureCreator {

    private final static int REQUIRED_PARAMS_COUNT = 2;
    private final static int SIDE_A_POS = 0;
    private final static int SIDE_B_POS = 1;

    @Override
    public Figure createFigure(double... params) {
        if (params.length != REQUIRED_PARAMS_COUNT) {
            throw new IllegalArgumentException(String.format("expected required parameters (%d), got (%d)",
                REQUIRED_PARAMS_COUNT, params.length));
        }

        double sideA = params[SIDE_A_POS], sideB = params[SIDE_B_POS];
        return new Rectangle(sideA, sideB);
    }

    @Override
    public int requiredParameters() {
        return REQUIRED_PARAMS_COUNT;
    }

}
