package com.biedermann.pathfindingalgorithms.Algorithms;

public class FloydWarshall {
    private int adjacencyMatrix[][];

    public FloydWarshall(int[][] adjacencyMatrix) { setAdjacencyMatrix(adjacencyMatrix); }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void printAdjacencyMatrix() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != Integer.MAX_VALUE) {
                    System.out.printf("| %3d ", adjacencyMatrix[i][j]);
                } else {
                    System.out.print("| INF ");
                }
            }
            System.out.println();
        }
    }

    public void setDistance(int i, int j, int distance) {
        adjacencyMatrix[i][j] = distance;
    }

    public int getDistance(int i, int j) {
        return adjacencyMatrix[i][j];
    }

    public int iterate() {
        for (int k = 0; k < adjacencyMatrix.length; k++) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    int distance = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];
                    if (distance < getDistance(i, j)) {
                        setDistance(i, j, distance);
                    }
                }
            }
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (getDistance(i, i) < 0) {
                return 1;
            }
        }

        return 0;
    }
}
