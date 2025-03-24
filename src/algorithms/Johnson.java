package algorithms;

public class Johnson implements AlgorithmInterface {
    private int adjacencyMatrix[][];

    public Johnson(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public int[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public int iterate() {
        int[][] newAdjacencyMatrix = new int[this.adjacencyMatrix.length+1][this.adjacencyMatrix.length+1];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                newAdjacencyMatrix[i][j] = this.adjacencyMatrix[i][j];
            }
            newAdjacencyMatrix[this.adjacencyMatrix.length][i] = Integer.MAX_VALUE;
        }

        int[] h = BellmanFord.runBellmanFord(newAdjacencyMatrix, (newAdjacencyMatrix.length-1));
        if (h == null) {
            return 1;
        }

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
