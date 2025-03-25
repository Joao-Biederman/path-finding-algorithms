package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Johnson implements AlgorithmInterface {
    private int adjacencyMatrix[][];
    private char mode;
    List<int[]> edges = new ArrayList<>();


    public Johnson(int[][] adjacencyMatrix, char mode) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.mode = mode;

        for (int start = 0; start < adjacencyMatrix.length; start++) {
            for (int end = 0; end < adjacencyMatrix.length; end++) {
                
                if (adjacencyMatrix[start][end] != Integer.MAX_VALUE) {
                    edges.add(new int[]{start, end, adjacencyMatrix[start][end]});
                }
            }
        }
    }

    public int[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        edges = new ArrayList<>();
        for (int start = 0; start < adjacencyMatrix.length; start++) {
            for (int end = 0; end < adjacencyMatrix.length; end++) {
                
                if (adjacencyMatrix[start][end] != Integer.MAX_VALUE) {
                    edges.add(new int[]{start, end, adjacencyMatrix[start][end]});
                }
            }
        }
    }

    public int iterate() {
        int[] h = (mode == 'M') ? BellmanFord.runBellmanFordMatrix(adjacencyMatrix, adjacencyMatrix.length-1) : BellmanFord.runBellmanFordList(edges, adjacencyMatrix.length, adjacencyMatrix.length + 1);

        int[][] reweightedGraph = new int[this.adjacencyMatrix.length][this.adjacencyMatrix.length];
        for (int i = 0; i < this.adjacencyMatrix.length; i++) {
            for (int j = 0; j < this.adjacencyMatrix[i].length; j++) {
                if (this.adjacencyMatrix[i][j] != Integer.MAX_VALUE) {
                    reweightedGraph[i][j] = this.adjacencyMatrix[i][j] + h[i] - h[j];
                } else {
                    reweightedGraph[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] distance = new int[this.adjacencyMatrix.length][this.adjacencyMatrix.length];
        for (int i = 0; i < this.adjacencyMatrix.length; i++) {
            distance[i] = Dijkstra.runDijkstra(reweightedGraph, i);
        }

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                if (distance[i][j] != Integer.MAX_VALUE) {
                    this.adjacencyMatrix[i][j] = distance[i][j] - h[i] + h[j];
                }
            }
        }

        return 0;
    }

}
