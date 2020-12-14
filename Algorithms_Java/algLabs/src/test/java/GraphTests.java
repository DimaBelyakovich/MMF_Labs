import graph.Edge;
import graph.Graph;
import graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphTests {
    public Graph graph;

    @Test
    public void testVertex() {
        final Vertex p1 = new Vertex(10);
        final Vertex p2 = new Vertex(10);
        final Vertex p3 = new Vertex(20);
        final Vertex p4 = new Vertex(20);
        final Vertex p5 = new Vertex(10);

        Assert.assertTrue(p1.equals(p2));
        Assert.assertFalse(p1.equals(p3));
        Assert.assertFalse(p3.equals(p1));
        Assert.assertFalse(p1.equals(p4));
        Assert.assertFalse(p4.equals(p1));

        Assert.assertTrue(p1.equals(p5) && p1.hashCode()==p5.hashCode());
        Assert.assertTrue(p5.equals(p1) && p5.hashCode()==p1.hashCode());
    }

    @Test
    public void testGraph() {
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
        edges.add(new Edge(5,v1,v2));
        edges.add(new Edge(10,v2,v4));
        edges.add(new Edge(7,v1,v4));
        edges.add(new Edge(8,v1,v5));
        edges.add(new Edge(6,v5,v3));

        this.graph = new Graph(vertices,edges);
        Graph clone = new Graph(this.graph);

        Assert.assertTrue(this.graph.equals(clone));
        Assert.assertTrue( this.graph.hashCode()==clone.hashCode());
    }
}
