package graph.algorithms;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleDetection {
    public static boolean detectCycle(Graph graph) {
        if (graph == null)
            throw new IllegalArgumentException("Graph is NULL.");


        final Set<Vertex> visitedVerticies = new HashSet<Vertex>();
        final Set<Edge> visitedEdges = new HashSet<Edge>();

        final List<Vertex> verticies = graph.getVertexList();
        if (verticies == null || verticies.size() == 0)
            return false;

        final Vertex root = verticies.get(0);
        return depthFirstSearch(root, visitedVerticies, visitedEdges);
    }

    private static final boolean depthFirstSearch(Vertex vertex, Set<Vertex> visitedVerticies, Set<Edge> visitedEdges) {
        if (!visitedVerticies.contains(vertex)) {
            visitedVerticies.add(vertex);

            final List<Edge> edges = vertex.getEdges();
            if (edges != null) {
                for (Edge edge : edges) {
                    final Vertex to = edge.getToVertex();
                    boolean result = false;
                    if (to != null && !visitedEdges.contains(edge)) {
                        visitedEdges.add(edge);

                        final Edge recip = new Edge(edge.getCost(), edge.getToVertex(), edge.getFromVertex());
                        visitedEdges.add(recip);

                        result = depthFirstSearch(to, visitedVerticies, visitedEdges);
                    }
                    if (result == true)
                        return result;
                }
            }
        } else {
            return true;
        }
        return false;
    }

}
