import graph.algorithms.DepthFirstTraversal;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstTraversalTest {
    private byte[][] adjacencyMatrix;
    private Graph graph;

    @Test
    public void depthFirstTraversalTest(){
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
        this.adjacencyMatrix = this.graph.toAdjacencyMatrix();

        int[] depthScan = DepthFirstTraversal.depthFirstTraversal(graph.getVertexList().size(), graph.toAdjacencyMatrix(), 0);

        Assert.assertEquals(depthScan[1],1);
        Assert.assertEquals(depthScan[2],3);
        Assert.assertEquals(depthScan[3],4);
        Assert.assertEquals(depthScan[4],2);
    }
}
