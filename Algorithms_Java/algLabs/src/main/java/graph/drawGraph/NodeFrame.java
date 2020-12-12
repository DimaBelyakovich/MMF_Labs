package graph.drawGraph;

import graph.Vertex;

class NodeFrame {
    int x;
    int y;
    String name;

    public NodeFrame(Vertex vertex, int xCoord, int yCoord) {
        this.x = xCoord;
        this.y = yCoord;
        name = String.valueOf(vertex.getValue());
    }
}
