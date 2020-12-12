package graph;

import java.util.*;

public class Graph {
    private List<Vertex> vertexList = new ArrayList<>();
    private List<Edge> edgeList = new ArrayList<>();

    private Map<Vertex, Integer> vertexIndexes = new HashMap<>();

    public Graph(Graph g){
        for (Vertex v: g.getVertexList()) {
            this.vertexList.add(new Vertex(v));
        }
        for (Edge e: g.getEdgeList()){
            this.edgeList.add(new Edge(e));
        }
        generateVertexIndex();
    }

    public Graph(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList.addAll(vertexList);
        this.edgeList.addAll(edgeList);
        generateVertexIndex();
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void addVertex(Vertex v){
        this.vertexList.add(v);
        generateVertexIndex();
    }

    public void removeVertex(Vertex v){
        for (Edge e: this.edgeList) {
            Vertex from = e.getFromVertex();
            Vertex to = e.getToVertex();

            if (from.equals(v) || to.equals(v)){
                this.edgeList.remove(e);
                break;
            }
        }

        this.vertexList.remove(v);
        generateVertexIndex();
    }

    public void addEdge(Edge e) {
        this.edgeList.add(e);
        if (!this.vertexList.contains(e.getFromVertex())){
            this.vertexList.add(e.getFromVertex());
        }else if (!this.vertexList.contains(e.getToVertex())){
            this.vertexList.add(e.getToVertex());
        }
        generateVertexIndex();
    }

    public void removeEdge(Edge e){
        this.edgeList.remove(e);
        generateVertexIndex();
    }

    public List<List<Integer>> toAdjacencyList(){
        var adjList = new ArrayList<List<Integer>>();

        for (int i = 0; i < this.vertexList.size(); i++){
            adjList.add(new ArrayList<>());
        }

        for (Edge e: this.edgeList){
            int from = getVertexIndex(e.getFromVertex());
            int to = getVertexIndex(e.getToVertex());

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        return adjList;
    }

    public boolean[][] toAdjacencyMatrix(){
        boolean adjMatrix[][] = new boolean[this.vertexList.size()+1][this.vertexList.size()+1];

        for (boolean[] row: adjMatrix) {
            Arrays.fill(row, false);
        }

        for (Edge e: this.edgeList){
            int from = getVertexIndex(e.getFromVertex());
            int to = getVertexIndex(e.getToVertex());

            adjMatrix[from][to] = true;
            adjMatrix[to][from] = true;
        }

        return adjMatrix;
    }

    public void printAdjacencyMatrix(){
        boolean[][] am = this.toAdjacencyMatrix();
        for (int i = 0; i < this.getVertexList().size(); i++) {
            for (int j = 0; j < this.getVertexList().size(); j++) {
                System.out.print(am[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void generateVertexIndex(){
        for (int i = 0; i < this.vertexList.size(); i++){
            this.vertexIndexes.put(this.vertexList.get(i),i);
        }
    }

    private int getVertexIndex(Vertex v){
        return this.vertexIndexes.get(v);
    }

    @Override
    public int hashCode() {
        int code = this.vertexList.size() + this.edgeList.size();
        for (Vertex v : vertexList)
            code *= v.hashCode();
        for (Edge e : edgeList)
            code *= e.hashCode();
        return 31 * code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph graph = (Graph) o;

        if (vertexList != null ? !vertexList.equals(graph.vertexList) : graph.vertexList != null) return false;
        if (edgeList != null ? !edgeList.equals(graph.edgeList) : graph.edgeList != null) return false;
        return vertexIndexes != null ? vertexIndexes.equals(graph.vertexIndexes) : graph.vertexIndexes == null;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Vertex v : vertexList)
            builder.append(v.toString());
        return builder.toString();
    }

}
