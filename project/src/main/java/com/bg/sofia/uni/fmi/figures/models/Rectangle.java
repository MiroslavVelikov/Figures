package main.java.com.bg.sofia.uni.fmi.figures.models;

import java.util.Objects;

public class Rectangle implements Figure, Cloneable {

    private static final String STRING_FORMAT = "rectangle %.2f %.2f";

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

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, sideA, sideB);
    }

    @Override
    public Rectangle clone() {
        try {
            Rectangle clone = (Rectangle) super.clone();
            clone.sideA = sideA;
            clone.sideB = sideB;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(sideA, rectangle.sideA) == 0 &&
            Double.compare(sideB, rectangle.sideB) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }

}
