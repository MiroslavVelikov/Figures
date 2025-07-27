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
                        selectFactory(parts);
                    }
                    case "duplicate" -> {
                        if (parts.length == 2) {
                            try {
                                int index = Integer.getInteger(parts[1]);
                                Figure duplicate = duplicateFigure(index);
                                System.out.println(duplicate);
                            } catch (Exception e) {
                                System.out.println("duplication failed");
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
                                System.out.println("deleting failed");
                            }
                        }
                    }
                    case "print" -> print();
                    case "save" -> {
                        boolean isSaved = false;

                        if (parts.length == 2) {
                            isSaved = saveToFile(parts[1]);
                        }

                        if (isSaved) {
                            System.out.println("successfully saved");
                        } else {
                            System.out.println("figures were not saved");
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

    private void selectFactory(String... args) throws IOException {
        if (args.length == 3 && args[1].equals(FactoryType.FILE_FIGURE_FACTORY.getType())) {
            Stream<String> lines = Files.lines(Paths.get(args[2]));
            figureFactory = new StreamFigureFactory(lines);
        } else if (args.length == 2 && args[1].equals(FactoryType.RANDOM_FIGURE_FACTORY.getType())) {
            figureFactory = new RandomFigureFactory();
        } else if (args.length == 2 && args[1].equals(FactoryType.STDIN_FIGURE_FACTORY.getType())) {
            Stream<String> stdinLines = new BufferedReader(new InputStreamReader(System.in)).lines();
            figureFactory = new StreamFigureFactory(stdinLines);
        } else {
            System.out.println("factory was not selected");
            return;
        }

        System.out.printf("%s factory was selected%n", args[1]);
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
