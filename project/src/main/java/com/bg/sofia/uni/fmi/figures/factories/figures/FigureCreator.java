package main.java.com.bg.sofia.uni.fmi.figures.factories.figures;

import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;

public interface FigureCreator {

    Figure createFigure(double... params);

}
