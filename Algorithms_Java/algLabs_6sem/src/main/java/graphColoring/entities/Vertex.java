package graphColoring.entities;

public class Vertex {
    private char name;

    public Vertex(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public String toString() {
        return String.valueOf(name);
    }
}
