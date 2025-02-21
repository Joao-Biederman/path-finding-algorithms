package com.biedermann.pathfindingalgorithms.Algorithms;

public class FloydWarshall {
    private double adjacencyMatrix[][];

    public FloydWarshall(double[][] adjacencyMatrix) { setAdjacencyMatrix(adjacencyMatrix); }

    public void setAdjacencyMatrix(double[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void printAdjacencyMatrix() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print("| " + (adjacencyMatrix[i][j] == Double.MAX_VALUE ? "INF" : adjacencyMatrix[i][j] ) + " ");
            }
            System.out.println();
        }
    }

    public void setDistance(int i, int j, double distance) {
        adjacencyMatrix[i][j] = distance;
    }

    public double getDistance(int i, int j) {
        return adjacencyMatrix[i][j];
    }

    public int iterate() {
        for (int k = 0; k < adjacencyMatrix.length; k++) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    double distance = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];
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
