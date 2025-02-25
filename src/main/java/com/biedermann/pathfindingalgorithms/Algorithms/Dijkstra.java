package com.biedermann.pathfindingalgorithms.Algorithms;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    static int[] Dijkstra(int[][] adjacencyMatrix, int source) {
        int[] distance = new int[adjacencyMatrix.length];
        boolean[] visited = new boolean[adjacencyMatrix.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>((i, j) -> Integer.compare(distance[i], distance[j]));
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (visited[current]) continue;
            visited[current] = true;

            for (int i = 0; i < adjacencyMatrix[current].length; i++) {
                if (adjacencyMatrix[current][i] != Integer.MAX_VALUE && !visited[i]) {
                    int newDistance = distance[current] + adjacencyMatrix[current][i];
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                        queue.add(i);
                    }
                }
            }
        }

        return distance;
    }
}
