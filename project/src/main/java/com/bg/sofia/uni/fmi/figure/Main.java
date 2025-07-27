package main.java.com.bg.sofia.uni.fmi.figure;

import main.java.com.bg.sofia.uni.fmi.figure.engine.FigureFactoryProvider;

public class Main {
    public static void main(String[] args) {
        FigureFactoryProvider ffp = FigureFactoryProvider.getInstance();
        ffp.run();
    }
}
