package algorithms;

import java.util.Arrays;
import java.util.List;

public class BellmanFord {
    static int[] runBellmanFordMatrix(int[][] adjacencyMatrix, int source) {
        int newAdjacencyMatrix[][] = new int[adjacencyMatrix.length+1][adjacencyMatrix.length+1];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
            }
            newAdjacencyMatrix[adjacencyMatrix.length][i] = Integer.MAX_VALUE;
        }

        int[] distance = new int[newAdjacencyMatrix.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int vertex = 0; vertex < newAdjacencyMatrix.length - 1; vertex++) {
            for (int start = 0; start < newAdjacencyMatrix.length; start++) {
                for (int end = 0; end < newAdjacencyMatrix.length; end++) {
                    if (distance[start] != Integer.MAX_VALUE && newAdjacencyMatrix[start][end] != Integer.MAX_VALUE) {
                        int sumDistance = distance[start] + newAdjacencyMatrix[start][end];
                        if (sumDistance < distance[end]) {
                            distance[end] = sumDistance;
                        }
                    }
                }
            }
        }

        for (int start = 0; start < newAdjacencyMatrix.length; start++) {
            for (int end = 0; end < newAdjacencyMatrix.length; end++) {
                if (distance[start] != Integer.MAX_VALUE && newAdjacencyMatrix[start][end] != Integer.MAX_VALUE) {
                    int sumDistance = distance[start] + newAdjacencyMatrix[start][end];
                    if (sumDistance < distance[end]) {
                        System.err.println("Error: Ciclo Negativo");
                        System.exit(0);
                    }
                }
            }
        }

        return distance;
    }

    static int[] runBellmanFordList(List<int[]> edges, int source, int V) {
        int[] distance = new int[V];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            if (i < V-1) {
                edges.add(new int[]{V-1, i, Integer.MAX_VALUE});
            }
        }

        distance[source] = 0;

        for (int vertex = 0; vertex < V - 1; vertex++) {
            for(int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int weight = edge[2];
                int sumDistance = distance[start] + weight;
                if (distance[start] != Integer.MAX_VALUE && sumDistance < distance[end]) {
                    distance[end] = sumDistance;
                }
            }
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];
            if (distance[start] != Integer.MAX_VALUE && distance[start] + weight < distance[end]) {
                System.err.println("Error: Ciclo Negativo");
                System.exit(0);   
            }
        }

        return distance;
    }
}
