package main.java.com.bg.sofia.uni.fmi.figure.factory.figure;

public enum CreatorType {

    CIRCLE("circle", new CircleCreator()),
    RECTANGLE("rectangle", new RectangleCreator()),
    TRIANGLE("triangle", new TriangleCreator());

    private final String figure;
    private final FigureCreator creator;

    CreatorType(String figure, FigureCreator creator) {
        this.figure = figure;
        this.creator = creator;
    }

    public String getFigure() {
        return figure;
    }

    public FigureCreator getCreator() {
        return creator;
    }

}
