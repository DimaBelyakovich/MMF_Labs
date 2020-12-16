package graph;

import graph.algorithms.CycleDetection;
import graph.algorithms.DepthFirstTraversal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<Vertex> vertices = new ArrayList<>();
        Vertex v1 = new Vertex(1);
        vertices.add(v1);
        Vertex v2 = new Vertex(2);
        vertices.add(v2);
        Vertex v3 = new Vertex(3);
        vertices.add(v3);
        Vertex v4 = new Vertex(4);
        vertices.add(v4);
        Vertex v5 = new Vertex(5);
        vertices.add(v5);


        final List<Edge> edges = new ArrayList<>();
        Edge e1 = new Edge(5,v1,v2).addEdgeFromVertex();
        edges.add(e1);
        Edge e2 = new Edge(10,v2,v4).addEdgeFromVertex();
        edges.add(e2);
        Edge e3 = new Edge(7,v1,v4).addEdgeFromVertex();
        edges.add(e3);
        Edge e4 = new Edge(8,v1,v5).addEdgeFromVertex();
        edges.add(e4);
        Edge e5 = new Edge(6,v5,v3).addEdgeFromVertex();
        edges.add(e5);
        Edge e6 = new Edge(2,v3,v4).addEdgeFromVertex();
        edges.add(e6);

        Graph graph = new Graph(vertices,edges);

        graph.printAdjacencyMatrix();
        System.out.println();

        graph.addEdge(new Edge(1, new Vertex(6), v5));
        graph.printAdjacencyMatrix();
        System.out.println();
        System.out.println(graph.getEdgeList());

        graph.addEdge(new Edge(1, new Vertex(6), v1));
        graph.printAdjacencyMatrix();
        System.out.println();
        System.out.println(graph.getEdgeList());

        graph.removeEdge(new Edge(1, new Vertex(6), v1));
        graph.printAdjacencyMatrix();
        System.out.println();
        System.out.println(graph.getEdgeList());

        graph.removeVertex(new Vertex(6));
        graph.printAdjacencyMatrix();
        System.out.println();

        System.out.println(graph.getEdgeList());

        for (int i: DepthFirstTraversal.depthFirstTraversal(graph.getVertexList().size(), graph.toAdjacencyMatrix(), 0)) {
            System.out.println(i);
        }

        System.out.println(CycleDetection.detectCycle(graph));
    }
}
