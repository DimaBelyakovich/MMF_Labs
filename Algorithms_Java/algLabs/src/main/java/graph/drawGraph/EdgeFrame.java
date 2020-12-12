package graph.drawGraph;

import graph.Edge;

class EdgeFrame {
    int from;
    int to;
    String cost;

    public EdgeFrame(Edge e) {
        this.from = e.getFromVertex().getValue() - 1;
        this.to = e.getToVertex().getValue() - 1;
        this.cost = String.valueOf(e.getCost());
    }
}