package main.java.com.bg.sofia.uni.fmi.figures.models;

import java.util.Objects;

public class Triangle implements Figure, Cloneable {

    private static final String STRING_FORMAT = "triangle %.2f %.2f %.2f";

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

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, sideA, sideB, sideC);
    }

    @Override
    public Triangle clone() {
        try {
            Triangle clone = (Triangle) super.clone();
            clone.sideA = sideA;
            clone.sideB = sideB;
            clone.sideC = sideC;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(sideA, triangle.sideA) == 0 &&
            Double.compare(sideB, triangle.sideB) == 0 && Double.compare(sideC, triangle.sideC) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, sideC);
    }

}
