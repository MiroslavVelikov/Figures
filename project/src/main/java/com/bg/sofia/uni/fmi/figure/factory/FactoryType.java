package main.java.com.bg.sofia.uni.fmi.figure.factory;

public enum FactoryType {

    RANDOM_FIGURE_FACTORY("random"),
    FILE_FIGURE_FACTORY("file"),
    STDIN_FIGURE_FACTORY("stdin");

    private final String type;

    FactoryType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
