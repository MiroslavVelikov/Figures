package main.java.com.bg.sofia.uni.fmi.figure.engine;

import main.java.com.bg.sofia.uni.fmi.figure.factory.FactoryType;
import main.java.com.bg.sofia.uni.fmi.figure.factory.FigureFactory;
import main.java.com.bg.sofia.uni.fmi.figure.factory.RandomFigureFactory;
import main.java.com.bg.sofia.uni.fmi.figure.factory.StreamFigureFactory;
import main.java.com.bg.sofia.uni.fmi.figure.model.Figure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FigureFactoryProvider {

    private static FigureFactoryProvider figureFactoryProvider = null;

    private List<Figure> figures;
    private FigureFactory figureFactory;
    private FactoryType type;
    private final Iterator<String> input;

    private FigureFactoryProvider() {
        figures = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.input = reader.lines().iterator();
    }

    public static FigureFactoryProvider getInstance() {
        if (figureFactoryProvider == null) {
            figureFactoryProvider = new FigureFactoryProvider();
        }

        return figureFactoryProvider;
    }

    public void run() {
        while (input.hasNext()) {
            String line = input.next().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "create" -> {
                        Figure newFigure = create();
                        System.out.println(newFigure.toString());
                    }
                    case "select" -> {
                        if (parts.length == 3 && parts[1].equals(FactoryType.FILE_FIGURE_FACTORY.getType())) {
                            selectFactory(FactoryType.FILE_FIGURE_FACTORY, parts[2]);
                        } else if (parts.length == 2 && parts[1].equals(FactoryType.RANDOM_FIGURE_FACTORY.getType())) {
                            selectFactory(FactoryType.RANDOM_FIGURE_FACTORY, "");
                        } else if (parts.length == 2 && parts[1].equals(FactoryType.STDIN_FIGURE_FACTORY.getType())) {
                            selectFactory(FactoryType.STDIN_FIGURE_FACTORY, "");
                        }
                    }
                    case "duplicate" -> {
                        if (parts.length == 2) {
                            try {
                                int index = Integer.getInteger(parts[1]);
                                Figure duplicate = duplicateFigure(index);
                                System.out.println(duplicate);
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                    case "delete" -> {
                        if (parts.length == 2) {
                            try {
                                int index = Integer.parseInt(parts[1]);
                                Figure deleted = deleteFigure(index);
                                System.out.println(deleted);
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                    case "print" -> print();
                    case "save" -> {
                        if (parts.length == 2) {
                            saveToFile(parts[1]);
                        }
                    }
                    case "exit" -> { return; }
                    default -> System.out.println("Unknown command: " + command);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void selectFactory(FactoryType type, String input) throws IOException {
        switch (type) {
            case FILE_FIGURE_FACTORY -> {
                Stream<String> lines = Files.lines(Paths.get(input));
                figureFactory = new StreamFigureFactory(lines);
                this.type = FactoryType.FILE_FIGURE_FACTORY;
            }
            case RANDOM_FIGURE_FACTORY -> {
                figureFactory = new RandomFigureFactory();
                this.type = FactoryType.RANDOM_FIGURE_FACTORY;
            }
            case STDIN_FIGURE_FACTORY -> {
                Stream<String> stdinLines = new BufferedReader(new InputStreamReader(System.in)).lines();
                figureFactory = new StreamFigureFactory(stdinLines);
                this.type = FactoryType.STDIN_FIGURE_FACTORY;
            }
        }
    }

    private Figure create() {
        Figure newFigure = figureFactory.create();
        figures.add(newFigure);

        return newFigure;
    }

    private void print() {
        for (Figure figure : figures) {
            System.out.println(figure.toString());
        }
    }

    private Figure deleteFigure(int index) {
        if (figures.size() < index || index < 0) {
            return null;
        }

        return figures.remove(index);
    }

    private Figure duplicateFigure(int index) {
        if (figures.size() < index || index < 0) {
            return null;
        }

        Figure newFigure = figures.get(index).clone();
        figures.add(newFigure);

        return newFigure;
    }

    private boolean saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Figure f : figures) {
                writer.write(f.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("something whent wrong");
            return false;
        }

        return  true;
    }

}
