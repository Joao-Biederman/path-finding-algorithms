package com.biedermann.pathfindingalgorithms.Algorithms;

import java.util.Arrays;

public class BellmanFord {
    static int[] bellmanFord(int[][] adjacencyMatrix, int source) {
        int[] distance = new int[adjacencyMatrix.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int k = 0; k < adjacencyMatrix.length-1; k++) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                    if (adjacencyMatrix[i][j] != Integer.MAX_VALUE && distance[i] != Integer.MAX_VALUE) {
                        if (distance[i] + adjacencyMatrix[i][j] < distance[j]) {
                            distance[j] = distance[i] + adjacencyMatrix[i][j];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != Integer.MAX_VALUE && distance[i] != Integer.MAX_VALUE) {
                    if (distance[i] + adjacencyMatrix[i][j] < distance[j]) {
                        return null;
                    }
                }
            }
        }

        return distance;
    }
}
