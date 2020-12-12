package graph.drawGraph;

import graph.Edge;
import graph.Vertex;

class GraphPanel {
    public static void main(String[] args) {
        GraphDraw frame = new GraphDraw("Graph Window");
        frame.setSize(400,300);
        frame.setVisible(true);

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        frame.addNode(v1, 50,75);
        frame.addNode(v2, 100,50);
        frame.addNode(v3, 180,170);
        frame.addNode(v4, 300,175);
        frame.addNode(v5, 100,230);


        frame.addEdge(new Edge(5,v1,v2));
        frame.addEdge(new Edge(7,v1,v4));
        frame.addEdge(new Edge(8,v1,v5));
        frame.addEdge(new Edge(10,v2,v4));
        frame.addEdge(new Edge(6,v5,v3));
        frame.addEdge(new Edge(2,v3,v4));
    }
}

