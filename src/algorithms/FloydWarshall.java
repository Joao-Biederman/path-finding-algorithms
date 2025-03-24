package algorithms;

public class FloydWarshall implements AlgorithmInterface {
    private int adjacencyMatrix[][];

    public FloydWarshall(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void setDistance(int i, int j, int distance) {
        adjacencyMatrix[i][j] = distance;
    }

    public int[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    int getDistance(int i, int j) {
        return this.adjacencyMatrix[i][j];
    }

    public int iterate() {
        for (int k = 0; k < adjacencyMatrix.length; k++) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    int distIK = getDistance(i, k);
                    int distKJ = getDistance(k, j);

                    int distance = (distIK == Integer.MAX_VALUE || distKJ == Integer.MAX_VALUE) ? Integer.MAX_VALUE : distIK + distKJ;
                    if (distance < getDistance(i, j)) {
                        setDistance(i, j, distance);
                    }
                }
            }
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (getDistance(i, i) < 0) {
                System.err.println("Error: Ciclo Negativo");
                System.exit(0);
            }
        }

        return 0;
    }
}
