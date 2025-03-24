package algorithms;
import java.io.FileWriter;
import java.io.IOException;

public interface AlgorithmInterface {
    int[][] getAdjacencyMatrix();

    default public void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = getAdjacencyMatrix();
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

    default public void writeAdjacencyMatrix(FileWriter writer) throws IOException {
        int[][] adjacencyMatrix = getAdjacencyMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != Integer.MAX_VALUE) {
                    writer.write(String.format("| %3d ", adjacencyMatrix[i][j]));
                } else {
                    writer.write("| INF ");
                }
            }
            writer.write("\n");
        }
    }

    public int iterate();

}
