package main.java.com.bg.sofia.uni.fmi.figure.factory;

import main.java.com.bg.sofia.uni.fmi.figure.factory.figure.CreatorType;
import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;

import java.util.Random;

public class RandomFigureFactory implements FigureFactory {

    private final static int FIGURES_COUNT = CreatorType.values().length;
    private final static Random random = new Random();

    @Override
    public Figure create() {
        int figurePos = random.nextInt(FIGURES_COUNT);
        CreatorType creator = CreatorType.values()[figurePos];

        double[] params = new double[creator.getCreator().requiredParameters()];
        for (int i = 0; i < creator.getCreator().requiredParameters(); i++) {
            params[i] = random.nextDouble();
        }

        try {
            return creator.getCreator().createFigure(params);
        } catch (Exception e) {
            return create();
        }
    }

}
