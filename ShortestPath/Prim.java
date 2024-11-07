package ShortestPath;

import java.util.Arrays;

public class Prim {
    // Method to find the vertex with the minimum key value that has not been included in MST
    private static int minKey(int[] key, boolean[] mstSet, int vertices) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Method to implement Prim's algorithm
    public static void prim(int[][] graph, int vertices) {
        int[] parent = new int[vertices]; // Array to store the MST
        int[] key = new int[vertices];    // Key values to select the minimum weight edge
        boolean[] mstSet = new boolean[vertices]; // Array to track the vertices included in MST

        // Initialize all keys as infinite and mstSet[] as false
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;  // Start from the first vertex (arbitrary choice)
        parent[0] = -1; // First node is the root of MST

        // Build the MST
        for (int count = 0; count < vertices - 1; count++) {
            // Choose the minimum key vertex from the set of vertices not yet processed
            int u = minKey(key, mstSet, vertices);

            // Include the vertex u in the MST set
            mstSet[u] = true;

            // Update the key and parent values of the adjacent vertices of u
            for (int v = 0; v < vertices; v++) {
                // Update the key only if graph[u][v] is smaller than key[v] and v is not in MST
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        // Print the MST
        printMST(parent, graph);
    }

    // Method to print the constructed MST
    private static void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < parent.length; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public static void main(String[] args) {
        // Example graph represented by an adjacency matrix
        int[][] graph = {
            {0, 2, 0, 6, 0, 0},
            {2, 0, 3, 8, 5, 0},
            {0, 3, 0, 0, 7, 0},
            {6, 8, 0, 0, 9, 3},
            {0, 5, 7, 9, 0, 4},
            {0, 0, 0, 3, 4, 0}
        };

        int vertices = graph.length;
        prim(graph, vertices);
    }
}
