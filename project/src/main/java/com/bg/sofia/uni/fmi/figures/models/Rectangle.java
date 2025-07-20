package main.java.com.bg.sofia.uni.fmi.figures.models;

public class Rectangle implements Figure {

    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        if (sideA + sideB > Double.MAX_VALUE / 2) {
            throw new ArithmeticException("perimeter would overflow for the given sides");
        }

        setSideA(sideA);
        setSideB(sideB);
    }

    private void setSideA(double sideA) {
        validateSide(sideA);
        this.sideA = sideA;
    }

    private void setSideB(double sideB) {
        validateSide(sideB);
        this.sideB = sideB;
    }

    private void validateSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("all sides must be greater than zero");
        }
    }

    @Override
    public double perimeter() {
        return (sideA + sideB) * 2;
    }

}
