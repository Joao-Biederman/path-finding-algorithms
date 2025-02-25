package com.biedermann.pathfindingalgorithms.Algorithms;

import java.util.Arrays;

public class BellmanFord {
    static int[] bellmanFord(int[][] adjacencyMatrix, int source) {
        int[] distance = new int[adjacencyMatrix.length];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        for (int k = 0; k < adjacencyMatrix.length - 1; k++) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    if (distance[i] != Integer.MAX_VALUE && adjacencyMatrix[i][j] != Integer.MAX_VALUE) {
                        int sumDistance = distance[i] + adjacencyMatrix[i][j];
                        if (sumDistance < distance[j]) {
                            distance[j] = sumDistance;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (distance[i] != Integer.MAX_VALUE && adjacencyMatrix[i][j] != Integer.MAX_VALUE) {
                    int sumDistance = distance[i] + adjacencyMatrix[i][j];
                    if (sumDistance < distance[j]) {
                        return null;
                    }
                }
            }
        }

        return distance;
    }
}
