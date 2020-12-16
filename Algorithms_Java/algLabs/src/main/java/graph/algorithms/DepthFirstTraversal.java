package graph.algorithms;

import java.util.Stack;

public class DepthFirstTraversal {
    //O(n+e) e - num of edges, n - num of vertex
    public static int[] depthFirstTraversal(int n, byte[][] adjacencyMatrix, int source){
        final int[] visited = new int[n];
        for (int i = 0; i < visited.length; i++)
            visited[i] = -1;

        int element = source;
        int i = source;
        int arr[] = new int[n];
        int k = 0;

        visited[source] = 1;
        arr[k] = element;
        k++;

        final Stack<Integer> stack = new Stack<Integer>();
        stack.push(source);

        while (!stack.isEmpty()){
            element = stack.peek();
            i=0;
            while (i<n){
                if (adjacencyMatrix[element][i] == 1 && visited[i] == -1){
                    stack.push(i);
                    visited[i] = 1;

                    element = i;
                    i = 0;

                    arr[k] = element;
                    k++;
                    continue;
                }
                i++;
            }
            stack.pop();
        }
        return arr;
    }
}
