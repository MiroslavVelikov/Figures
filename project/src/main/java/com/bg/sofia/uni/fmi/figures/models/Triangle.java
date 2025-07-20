package main.java.com.bg.sofia.uni.fmi.figures.models;

public class Triangle implements Figure {

    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        validateTriangle(sideA, sideB, sideC);
        if (sideA + sideB + sideC > Double.MAX_VALUE) {
            throw new ArithmeticException("perimeter would overflow for given sides");
        }

        setSideA(sideA);
        setSideB(sideB);
        setSideC(sideC);
    }

    private void setSideA(double sideA) {
        validateSide(sideA);
        this.sideA = sideA;
    }

    private void setSideB(double sideB) {
        validateSide(sideB);
        this.sideB = sideB;
    }

    private void setSideC(double sideC) {
        validateSide(sideC);
        this.sideC = sideC;
    }

    private void validateSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("all sides must be greater than zero");
        }
    }

    private void validateTriangle(double sideA, double sideB, double sideC) {
        checkSides(sideA, sideB, sideC);
        checkSides(sideA, sideC, sideB);
        checkSides(sideB, sideC, sideA);
    }

    private void checkSides(double sideA, double sideB, double sideC) {
        if (sideA + sideB < sideC) {
            throw new IllegalArgumentException(String.format("invalidly related sides: %f + %f < %f",
                sideA, sideB, sideC));
        }
    }

    @Override
    public double perimeter() {
        return sideA + sideB + sideC;
    }

}
