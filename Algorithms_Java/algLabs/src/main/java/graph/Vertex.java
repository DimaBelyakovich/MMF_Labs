package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex {
    private int value = 0;
    private List<Edge> edges = new ArrayList<>();

    public Vertex(int value) {
        this.value = value;
    }

    public Vertex(Vertex vertex){
        this(vertex.value);
        this.edges.addAll(vertex.edges);
    }

    public int getValue() {
        return value;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge e){
        edges.add(e);
    }

    public Edge getEdge(Vertex v) {
        for (Edge e : edges) {
            if (e.getToVertex().equals(v))
                return e;
        }
        return null;
    }


    @Override
    public int hashCode() {
        final int code = this.value + this.edges.size();
        return 31 * code;
    }

    @Override
    public boolean equals(Object v1) {
        if (!(v1 instanceof Vertex))
            return false;

        final Vertex v = (Vertex) v1;

        final boolean edgesSizeEquals = this.edges.size() == v.edges.size();
        if (!edgesSizeEquals)
            return false;

        final boolean valuesEquals = this.value == v.value;

        if (!valuesEquals)
            return false;

        final Iterator<Edge> iter1 = this.edges.iterator();
        final Iterator<Edge> iter2 = v.edges.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {

            final Edge e1 = iter1.next();
            final Edge e2 = iter2.next();
            if (e1.getCost() != e2.getCost())
                return false;
        }

        return true;
    }

    public int compareTo(Vertex v) {
        final int valueComp = this.value == v.value ? 1 : -1;
        if (valueComp != 0)
            return valueComp;

        if (this.edges.size() < v.edges.size())
            return -1;
        if (this.edges.size() > v.edges.size())
            return 1;

        final Iterator<Edge> iter1 = this.edges.iterator();
        final Iterator<Edge> iter2 = v.edges.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            // Only checking the cost
            final Edge e1 = iter1.next();
            final Edge e2 = iter2.next();
            if (e1.getCost() < e2.getCost())
                return -1;
            if (e1.getCost() > e2.getCost())
                return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (Edge e : edges)
            builder.append("\t").append(e.toString());
        return builder.toString();
    }
}
