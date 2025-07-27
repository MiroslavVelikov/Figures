package main.java.com.bg.sofia.uni.fmi.figure.factory.figure;

import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;

public interface FigureCreator {

    Figure createFigure(double... params);
    int requiredParameters();

}
