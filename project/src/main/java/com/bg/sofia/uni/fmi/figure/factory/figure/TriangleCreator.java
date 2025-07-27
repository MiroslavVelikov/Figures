package main.java.com.bg.sofia.uni.fmi.figure.factory.figure;

import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;
import main.java.com.bg.sofia.uni.fmi.figure.model.Triangle;

public class TriangleCreator implements FigureCreator {

    private final static int REQUIRED_PARAMS_COUNT = 3;
    private final static int SIDE_A_POS = 0;
    private final static int SIDE_B_POS = 1;
    private final static int SIDE_C_POS = 2;

    @Override
    public Figure createFigure(double... params) {
        if (params.length != REQUIRED_PARAMS_COUNT) {
            throw new IllegalArgumentException(String.format("expected required parameters (%d), got (%d)",
                REQUIRED_PARAMS_COUNT, params.length));
        }

        double sideA = params[SIDE_A_POS], sideB = params[SIDE_B_POS], sideC = params[SIDE_C_POS];
        return new Triangle(sideA, sideB, sideC);
    }

    @Override
    public int requiredParameters() {
        return REQUIRED_PARAMS_COUNT;
    }

}
