package model;

public class Movie {
    private int code;
    private String name;
    private int classification;
    private String genero;

    // Construtor
    public Movie(int code, String name, int classification, String genero) {
        this.code = code;
        this.name = name;
        this.classification = classification;
        this.genero = genero;
    }

    // Getters e Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
