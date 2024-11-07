package ShortestPath;

import java.util.Arrays;

public class Dijkstra {
    // Method to find the vertex with minimum distance value that is not yet processed
    private static int findMinDistanceVertex(int[] distances, boolean[] visited, int vertices) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && distances[v] <= minDistance) {
                minDistance = distances[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Dijkstra's algorithm to find the shortest path from source to all vertices
    public static void dijkstra(int[][] graph, int source) {
        int vertices = graph.length;
        int[] distances = new int[vertices]; // Array to hold the shortest distance from source to each vertex
        boolean[] visited = new boolean[vertices]; // Array to track visited vertices

        // Initialize all distances as infinite and visited[] as false
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Find shortest path for all vertices
        for (int i = 0; i < vertices - 1; i++) {
            int u = findMinDistanceVertex(distances, visited, vertices); // Get the minimum distance vertex from the unvisited set
            visited[u] = true;

            // Update distances of adjacent vertices of the picked vertex
            for (int v = 0; v < vertices; v++) {
                // Update distance[v] if it's not visited, there's an edge from u to v, 
                // and the total weight of path from source to v through u is smaller than the current distance[v]
                if (!visited[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE
                        && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                }
            }
        }

        // Print the shortest distances from the source to each vertex
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t\t " + distances[i]);
        }
    }

    public static void main(String[] args) {
        // Example graph represented by an adjacency matrix
        int[][] graph = {
                {0, 10, 0, 0, 0, 15},
                {10, 0, 20, 0, 0, 0},
                {0, 20, 0, 5, 0, 0},
                {0, 0, 5, 0, 10, 0},
                {0, 0, 0, 10, 0, 5},
                {15, 0, 0, 0, 5, 0}
        };

        int source = 0; // Starting vertex
        dijkstra(graph, source);
    }
}
