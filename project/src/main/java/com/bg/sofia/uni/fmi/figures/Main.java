package main.java.com.bg.sofia.uni.fmi.figures;

import main.java.com.bg.sofia.uni.fmi.figures.factories.StreamFigureFactory;
import main.java.com.bg.sofia.uni.fmi.figures.models.Figure;

public class Main {
    public static void main(String[] args) {
        String strInput = """
            circle 50.3
            rectangle 10.3 130.0
            triangle 10 20 30
            """;
        StreamFigureFactory streamFigureFactory = new StreamFigureFactory(strInput.lines());
        Figure figure = streamFigureFactory.create();
        System.out.println(figure.perimeter());

        figure = streamFigureFactory.create();
        System.out.println(figure.perimeter());

        figure = streamFigureFactory.create();
        System.out.println(figure.perimeter());

        figure = streamFigureFactory.create();
        System.out.println(figure.perimeter());
    }
}
