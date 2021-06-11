package graphColoring.entities;

public class Edge {
    private Vertex fromVertex;
    private Vertex toVertex;

    public Edge(Vertex fromVertex, Vertex toVertex) {
        if (fromVertex.getName() != toVertex.getName()) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
        }
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Vertex fromVertex) {
        if (fromVertex.getName() != this.toVertex.getName()) {
            this.fromVertex = fromVertex;
        }
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public void setToVertex(Vertex toVertex) {
        if (toVertex.getName() != this.fromVertex.getName()) {
            this.toVertex = toVertex;
        }
    }

    @Override
    public String toString() {
        return "[" +
                fromVertex.getName() +
                "," + toVertex.getName() +
                ']';
    }

    public String toStringWithoutBrackets() {
        return fromVertex.getName() +
                " " + toVertex.getName();
    }
}

