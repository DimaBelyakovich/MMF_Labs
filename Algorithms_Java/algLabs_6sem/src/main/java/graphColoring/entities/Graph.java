package graphColoring.entities;


import graphColoring.validators.EdgeValidator;
import graphColoring.validators.GraphValidator;

import java.util.*;

public class Graph {
    private final char[] LETTERS = {
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'G', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private Vertex[] vertices;
    private Edge[] edges;
    private boolean[] isVisited;

    public Graph(Graph graph) {
        this.vertices = graph.getVertices();
        this.edges = graph.getEdges();
    }

    public Graph(Vertex[] vertices, Edge[] edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Graph(int[][] adjacencyMatrix, int n) {
        if (GraphValidator.isAdjacencyMatrixValid(adjacencyMatrix)) {
            this.vertices = new Vertex[n];
            this.edges = new Edge[0];

            for (int i = 0; i < n; i++) {
                this.vertices[i] = new Vertex(LETTERS[i]);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        boolean flag = false;

                        for (Edge edge : this.edges) {
                            if (EdgeValidator.isEdgesSame(edge, new Edge(this.vertices[i], this.vertices[j]))) {
                                flag = true;
                                break;
                            }
                        }

                        if (!flag) {
                            this.edges = Arrays.copyOf(this.edges, this.edges.length + 1);
                            this.edges[this.edges.length - 1] = new Edge(this.vertices[i], this.vertices[j]);
                        }
                    }
                }
            }
        }
    }

    public Graph(int[][] incidenceMatrix, int n, int k) {
        this.vertices = new Vertex[k];
        this.edges = new Edge[0];

        for (int i = 0; i < k; i++) {
            this.vertices[i] = new Vertex(LETTERS[i]);
        }

        for (int i = 0; i < k; i++) {
            Vertex vertexOne = null;
            Vertex vertexTwo = null;
            for (int j = 0; j < n; j++) {
                if (incidenceMatrix[i][j] == 1) {
                    if (vertexOne == null) {
                        vertexOne = this.vertices[j];
                    } else {
                        vertexTwo = this.vertices[j];
                        break;
                    }
                }
            }

            this.edges = Arrays.copyOf(this.edges, this.edges.length + 1);
            this.edges[this.edges.length - 1] = new Edge(vertexOne, vertexTwo);
        }
    }

    public Graph(int[][] adjacencyList) {
        this.vertices = new Vertex[adjacencyList.length];
        this.edges = new Edge[0];

        for (int i = 0; i < this.vertices.length; i++) {
            this.vertices[i] = new Vertex(LETTERS[i]);
        }

        for (int i = 0; i < this.vertices.length; i++) {
            for (int j = 1; j < adjacencyList[i].length; j++) {
                boolean flag = false;
                for (Edge edge : this.edges) {
                    if (EdgeValidator.isEdgesSame(edge, new Edge(this.vertices[i], this.vertices[adjacencyList[i][j] - 1]))) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    this.edges = Arrays.copyOf(this.edges, this.edges.length + 1);
                    this.edges[this.edges.length - 1] = new Edge(this.vertices[i], this.vertices[adjacencyList[i][j] - 1]);
                }
            }
        }
    }

    public Graph(Edge[] edges) {
        this.edges = edges;
        this.vertices = new Vertex[0];
        for (Edge edge : this.edges) {
            boolean flag1 = false;
            boolean flag2 = false;

            for (Vertex vertex : this.vertices) {
                if (vertex.getName() == edge.getFromVertex().getName()) {
                    flag1 = true;
                }
                if (vertex.getName() == edge.getToVertex().getName()) {
                    flag2 = true;
                }
            }

            if (!flag1) {
                this.vertices = Arrays.copyOf(this.vertices, this.vertices.length + 1);
                this.vertices[this.vertices.length - 1] = edge.getFromVertex();
            }
            if (!flag2) {
                this.vertices = Arrays.copyOf(this.vertices, this.vertices.length + 1);
                this.vertices[this.vertices.length - 1] = edge.getToVertex();
            }
        }
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    public void addVertex(Vertex vertex) {
        this.vertices = Arrays.copyOf(this.vertices, this.vertices.length + 1);
        this.vertices[this.vertices.length - 1] = vertex;
    }

    public void deleteVertex(Vertex vertex) {
        Vertex[] temporaryVerticesArray = new Vertex[0];

        for (Vertex value : this.vertices) {
            if (!EdgeValidator.isVertexSame(value, vertex)) {
                temporaryVerticesArray = Arrays.copyOf(temporaryVerticesArray, temporaryVerticesArray.length + 1);
                temporaryVerticesArray[temporaryVerticesArray.length - 1] = value;
            }
        }

        this.vertices = temporaryVerticesArray;

        Edge[] temporaryEdgesArray = new Edge[0];

        for (Edge edge : this.edges) {
            if (!(EdgeValidator.isVertexSame(vertex, edge.getFromVertex()) ||
                    EdgeValidator.isVertexSame(vertex, edge.getToVertex()))) {
                temporaryEdgesArray = Arrays.copyOf(temporaryEdgesArray, temporaryEdgesArray.length + 1);
                temporaryEdgesArray[temporaryEdgesArray.length - 1] = edge;
            }
        }
        this.edges = temporaryEdgesArray;
    }

    public void addEdge(Edge edge) {
        boolean flag = false;

        for (Edge value : this.edges) {
            if (EdgeValidator.isEdgesSame(edge, value)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            this.edges = Arrays.copyOf(this.edges, this.edges.length + 1);
            this.edges[this.edges.length - 1] = edge;
        }
    }

    public void deleteEdge(Edge a) {
        Edge[] edges = new Edge[0];

        for (Edge edge : this.edges) {
            if (!EdgeValidator.isEdgesSame(edge, a)) {
                edges = Arrays.copyOf(edges, edges.length + 1);
                edges[edges.length - 1] = edge;
            }
        }
        this.edges = edges;
    }

    public boolean isVertexAdjacency(Vertex vertexOne, Vertex vertexTwo) {
        for (int i = 0; i < this.edges.length; i++) {
            if (EdgeValidator.isEdgesSame(new Edge(vertexOne, vertexTwo), this.edges[i])) {
                return true;
            }
        }
        return false;
    }

    public int[][] writeAdjacencyMatrix() {
        int[][] adjacencyMatrix = new int[this.vertices.length][this.vertices.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    if (isVertexAdjacency(this.vertices[i], this.vertices[j])) {
                        adjacencyMatrix[i][j] = 1;
                    } else {
                        adjacencyMatrix[i][j] = 0;
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

    public int[][] writeIncidenceMatrix() {
        int[][] incidenceMatrix = new int[this.vertices.length][this.edges.length];

        for (int i = 0; i < incidenceMatrix[0].length; i++) {
            Vertex v1 = this.edges[i].getFromVertex();
            Vertex v2 = this.edges[i].getToVertex();
            for (int j = 0; j < incidenceMatrix.length; j++) {
                if (EdgeValidator.isVertexSame(this.vertices[j], v1) || EdgeValidator.isVertexSame(this.vertices[j], v2)) {
                    incidenceMatrix[j][i] = 1;
                } else {
                    incidenceMatrix[j][i] = 0;
                }
            }
        }
        return incidenceMatrix;
    }

    public int[][] writeAdjacencyList() {
        int[][] adjacencyList = new int[this.vertices.length][0];

        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = Arrays.copyOf(adjacencyList[i], adjacencyList[i].length + 1);
            adjacencyList[i][0] = i + 1;

            for (int j = 0; j < this.vertices.length; j++) {
                if (i != j && isVertexAdjacency(this.vertices[i], this.vertices[j])) {
                    adjacencyList[i] = Arrays.copyOf(adjacencyList[i], adjacencyList[i].length + 1);
                    adjacencyList[i][adjacencyList[i].length - 1] = j + 1;
                }
            }
        }
        return adjacencyList;
    }

    public Edge[] writeEdges() {
        return edges;
    }

    private int getIndexNextVertex(int indexVertex, int[][] adjacencyMatrix) {
        int resultIndex = -1;
        for (int index = 0; index < adjacencyMatrix[indexVertex].length; index++) {
            if (adjacencyMatrix[indexVertex][index] == 1 && !this.isVisited[index]) {
                resultIndex = index;
                break;
            }
        }
        return resultIndex;
    }

    public char[] breadthFirstSearchAlgorithm(int[][] adjacencyMatrix) {
        char[] result = new char[this.vertices.length];
        int[] indexes = new int[this.vertices.length];
        int[][] matrix = adjacencyMatrix;
        this.isVisited = new boolean[this.vertices.length];
        Arrays.fill(this.isVisited, false);
        int cursor = 0;
        int count = 0;
        int nextIndex;
        this.isVisited[count] = true;
        indexes[cursor] = 0;
        result[count] = this.vertices[count++].getName();
        while (count < result.length) {
            while ((nextIndex = getIndexNextVertex(indexes[cursor], matrix)) != -1) {
                this.isVisited[nextIndex] = true;
                indexes[count] = nextIndex;
                result[count++] = this.vertices[nextIndex].getName();
            }
            cursor++;
        }

        return result;
    }

    public void printGraph() {
        System.out.print("Vertex's: ");
        for (Vertex vertex : this.vertices) {
            System.out.print(vertex.getName() + ";");
        }
        System.out.println();

        System.out.print("Edge's: ");
        for (Edge edge : this.edges) {
            System.out.print("(" + edge.getFromVertex().getName() + "," + edge.getToVertex().getName() + ");");
        }
        System.out.println();
    }

    public void edgeColoring(Edge[] graphEdges) {
        int[] colors;
        int dimension = graphEdges.length;
        int[][] newMatrix = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == j) {
                    newMatrix[i][j] = 0;
                } else {
                    if (isEdgesAdjacency(graphEdges[i], graphEdges[j])) {
                        newMatrix[i][j] = 1;
                    } else {
                        newMatrix[i][j] = 0;
                    }
                }
            }
        }

        Graph newGraph = new Graph(newMatrix, newMatrix.length);
        colors = newGraph.greedyColoringReturnColors(newGraph.writeAdjacencyList());
        for (int i = 0; i < graphEdges.length; i++){
            System.out.println("Edge " + graphEdges[i].toString() + " - color ---> " + colors[i]);
        }
    }

    private boolean isEdgesAdjacency(Edge firstEdge, Edge secondEdge) {
        if (firstEdge.getFromVertex().getName() == secondEdge.getFromVertex().getName() ||
                firstEdge.getToVertex().getName() == secondEdge.getFromVertex().getName() ||
                firstEdge.getFromVertex().getName() == secondEdge.getToVertex().getName() ||
                firstEdge.getToVertex().getName() == secondEdge.getToVertex().getName()) {
            return true;
        }
        return false;
    }

    public void breadthFirstSearch(int[][] adjacencyListMatrix, int indexVertex) {
        LinkedList<Integer>[] adjacencyList = new LinkedList[this.vertices.length];
        for (int i = 0; i < this.vertices.length; i++) {
            adjacencyList[i] = new LinkedList();
        }
        for (int i = 0; i < this.vertices.length; i++) {
            for (int j = 1; j < adjacencyListMatrix[i].length; j++) {
                adjacencyList[i].add(adjacencyListMatrix[i][j]);
            }
        }
        boolean[] visited = new boolean[this.vertices.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[indexVertex] = true;
        queue.add(indexVertex);
        while (queue.size() != 0) {
            indexVertex = queue.poll();
            System.out.print(this.vertices[indexVertex].getName() + " ");
            Iterator<Integer> i = adjacencyList[indexVertex].listIterator();
            while (i.hasNext()) {
                int nextIndexVertex = i.next() - 1;
                if (!visited[nextIndexVertex]) {
                    visited[nextIndexVertex] = true;
                    queue.add(nextIndexVertex);
                }
            }
        }
    }

    public void depthFirstSearch(int[][] adjacencyListMatrix, int indexVertex) {
        LinkedList<Integer>[] adjacencyList = new LinkedList[this.vertices.length];
        for (int i = 0; i < this.vertices.length; i++) {
            adjacencyList[i] = new LinkedList();
        }
        for (int i = 0; i < this.vertices.length; i++) {
            for (int j = 1; j < adjacencyListMatrix[i].length; j++) {
                adjacencyList[i].add(adjacencyListMatrix[i][j]);
            }
        }
        boolean[] visited = new boolean[this.vertices.length];
        depthFirstSearchUtil(indexVertex, visited, adjacencyList);
    }

    private void depthFirstSearchUtil(int vertex, boolean[] visited, LinkedList<Integer>[] adjacencyList) {
        visited[vertex] = true;
        System.out.print(this.vertices[vertex].getName() + " ");

        Iterator<Integer> i = adjacencyList[vertex].listIterator();
        while (i.hasNext()) {
            int nextIndexVertex = i.next() - 1;
            if (!visited[nextIndexVertex])
                depthFirstSearchUtil(nextIndexVertex, visited, adjacencyList);
        }
    }

    public void coloringByManipulatingRows(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix[0].length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 1;
                }
            }
        }

        boolean[] availableRows = new boolean[this.vertices.length];
        Arrays.fill(availableRows, true);
        int[] colors = new int[this.vertices.length];
        Arrays.fill(colors, -1);
        int color = 0;
        boolean continueCycle = true;

        for (int i = 0; i < this.vertices.length; i++) {
            if (availableRows[i]) {
                while (isExistZeroInRow(adjacencyMatrix, i) && continueCycle) {
                    int j = getIndexZeroInRow(adjacencyMatrix, i);
                    if (availableRows[j]) {
                        for (int k = 0; k < this.vertices.length; k++) {
                            if (adjacencyMatrix[i][k] == 1 && adjacencyMatrix[j][k] == 1) {
                                adjacencyMatrix[i][k] = 1;
                            } else {
                                adjacencyMatrix[i][k] = adjacencyMatrix[i][k] + adjacencyMatrix[j][k];
                            }
                        }
                        availableRows[j] = false;
                        colors[j] = color;
                    } else {
                        continueCycle = false;
                    }
                }
                colors[i] = color;
                availableRows[i] = false;
                color += 1;
            }
        }

        for (int i = 0; i < this.vertices.length; i++) {
            System.out.println("Vertex " + this.vertices[i].getName() + " --->  Color "
                    + colors[i]);
        }

    }

    private boolean isExistZeroInRow(int[][] adjacencyMatrix, int indexRow) {
        for (int i = 0; i < this.vertices.length; i++) {
            if (adjacencyMatrix[indexRow][i] == 0) {
                return true;
            }
        }
        return false;
    }

    public int[] greedyColoringReturnColors(int[][] adjacencyList) {
        int[] colors = new int[this.vertices.length];
        Arrays.fill(colors, -1);
        List<List<Integer>> unavailableColorsVertex;
        unavailableColorsVertex = new ArrayList<>(this.vertices.length);
        for (int i = 0; i < this.vertices.length; i++) {
            unavailableColorsVertex.add(new ArrayList<Integer>());
        }

        int[][] degrees = new int[7][2];
        for (int i = 0; i < this.vertices.length; i++) {
            degrees[i][0] = adjacencyList[i][0];
            degrees[i][1] = adjacencyList[i].length - 1;
        }
        for (int i = 0; i < this.vertices.length; i++) {
            int idMaxVertex = getIdVertexMaxDegree(degrees);
            int color = getFirstAvailableColorVertex(unavailableColorsVertex, idMaxVertex);
            setColorVertex(colors, idMaxVertex, color);
            reduceDegreeAdjacentVertices(degrees, adjacencyList, idMaxVertex);
            setUnavailableColors(unavailableColorsVertex, idMaxVertex, color, adjacencyList);
        }

        return colors;
    }

    public void greedyColoring(int[][] adjacencyList) {
        int[] colors = new int[this.vertices.length];
        Arrays.fill(colors, -1);
        List<List<Integer>> unavailableColorsVertex;
        unavailableColorsVertex = new ArrayList<>(this.vertices.length);
        for (int i = 0; i < this.vertices.length; i++) {
            unavailableColorsVertex.add(new ArrayList<Integer>());
        }

        int[][] degrees = new int[7][2];
        for (int i = 0; i < this.vertices.length; i++) {
            degrees[i][0] = adjacencyList[i][0];
            degrees[i][1] = adjacencyList[i].length - 1;
        }
        for (int i = 0; i < this.vertices.length; i++) {
            int idMaxVertex = getIdVertexMaxDegree(degrees);
            int color = getFirstAvailableColorVertex(unavailableColorsVertex, idMaxVertex);
            setColorVertex(colors, idMaxVertex, color);
            reduceDegreeAdjacentVertices(degrees, adjacencyList, idMaxVertex);
            setUnavailableColors(unavailableColorsVertex, idMaxVertex, color, adjacencyList);
        }

        for (int i = 0; i < this.vertices.length; i++) {
            System.out.println("Vertex " + this.vertices[i].getName() + " --->  Color "
                    + colors[i]);
        }
    }

    private int getIndexZeroInRow(int[][] adjacencyMatrix, int indexRow) {
        int index = -1;
        for (int i = 0; i < this.vertices.length; i++) {
            if (adjacencyMatrix[indexRow][i] == 0) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int getIdVertexMaxDegree(int[][] degrees) {
        int idVertex = -1;
        int maxVertexDegree = -1;
        for (int i = 0; i < this.vertices.length; i++) {
            if (degrees[i][1] >= maxVertexDegree) {
                maxVertexDegree = degrees[i][1];
                idVertex = i;
            }
        }
        return idVertex;
    }

    private void reduceDegreeAdjacentVertices(int[][] degrees, int[][] adjacencyList, int idVertex) {
        for (int i = 0; i < degrees[idVertex][1]; i++) {
            int reducingVertex = adjacencyList[idVertex][i + 1];
            degrees[reducingVertex - 1][1] -= 1;
        }
        degrees[idVertex][1] = -1;
    }


    private void setColorVertex(int[] vertexColoring, int idVertex, int color) {
        vertexColoring[idVertex] = color;
    }

    private void setUnavailableColors(List<List<Integer>> unavailableColors, int idVertex, int color, int[][] adjacencyList) {
        for (int i = 0; i < adjacencyList[idVertex].length - 1; i++) {
            int vertex = adjacencyList[idVertex][i + 1];
            if (!isColorExist(unavailableColors, color, vertex - 1)) {
                unavailableColors.get(vertex - 1).add(color);
            }
        }
    }

    private int getFirstAvailableColorVertex(List<List<Integer>> unavailableColors, int idVertex) {
        int color = 0;
        boolean equal = false;
        while (true) {
            for (int i = 0; i < unavailableColors.get(idVertex).size(); i++) {
                if (color == unavailableColors.get(idVertex).get(i)) {
                    equal = true;
                    break;
                }
            }
            if (equal) {
                color += 1;
                equal = false;
            } else {
                break;
            }
        }
        return color;
    }

    private boolean isColorExist(List<List<Integer>> unavailableColors, int color, int idVertex) {
        boolean exist = false;
        for (int i = 0; i < unavailableColors.get(idVertex).size(); i++) {
            if (color == unavailableColors.get(idVertex).get(i)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
