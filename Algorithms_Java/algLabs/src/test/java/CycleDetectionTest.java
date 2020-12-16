import graph.Graph;
import graph.algorithms.CycleDetection;
import org.junit.Assert;
import org.junit.Test;

import graph.Edge;
import graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class CycleDetectionTest {
    @Test
    public void cycleCheckOnUndirectedTrue() {
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

        boolean result = CycleDetection.detectCycle(graph);
        Assert.assertTrue("Cycle detected", result);
    }

    @Test
    public void cycleCheckOnUndirectedFalse(){
        final List<Vertex> verticesFalse = new ArrayList<>();
        Vertex v1False = new Vertex(1);
        verticesFalse.add(v1False);
        Vertex v2False = new Vertex(2);
        verticesFalse.add(v2False);
        Vertex v3False = new Vertex(3);
        verticesFalse.add(v3False);
        Vertex v4False = new Vertex(4);
        verticesFalse.add(v4False);
        Vertex v5False = new Vertex(5);
        verticesFalse.add(v5False);

        final List<Edge> edgesFalse = new ArrayList<>();
        Edge e1False = new Edge(5,v1False,v2False).addEdgeFromVertex();
        edgesFalse.add(e1False);
        Edge e2False = new Edge(10,v2False,v4False);
        edgesFalse.add(e2False);
        Edge e3False = new Edge(7,v1False,v4False);
        edgesFalse.add(e3False);
        Edge e4False = new Edge(8,v1False,v5False);
        edgesFalse.add(e4False);
        Edge e5False = new Edge(6,v5False,v3False).addEdgeFromVertex();
        edgesFalse.add(e5False);
        Edge e6False = new Edge(2,v3False,v4False).addEdgeFromVertex();
        edgesFalse.add(e6False);

        Graph graphFalse = new Graph(verticesFalse,edgesFalse);
        boolean resultFalse = CycleDetection.detectCycle(graphFalse);
        Assert.assertFalse(resultFalse);
    }
}
