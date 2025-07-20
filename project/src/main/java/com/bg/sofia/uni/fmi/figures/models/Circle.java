package main.java.com.bg.sofia.uni.fmi.figures.models;

public class Circle implements Figure, Cloneable {

    private static final String STRING_FORMAT = "circle %.2f";

    private double radius;

    public Circle(double radius) {
        if (radius > Double.MAX_VALUE / (2 * Math.PI)) {
            throw new ArithmeticException("radius too large, perimeter would overflow");
        }

        setRadius(radius);
    }

    private void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be greater than zero");
        }

        this.radius = radius;
    }

    @Override
    public double perimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, radius);
    }

    @Override
    public Circle clone() {
        try {
            Circle clone = (Circle) super.clone();
            clone.radius = radius;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
