package utils;

import algorithms.*;

import java.io.File;
import java.util.Scanner;

public class TerminalInterface{
    private FloydWarshall floydWarshall = new FloydWarshall(new int[0][0]);
    private Johnson johnson = new Johnson((new int[0][0]));
    private int[][] adjacencyMatrix = new int[0][0];
    private final Scanner scanner = new Scanner(System.in);
    private final MatrixReader matrixReader = new MatrixReader();

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
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

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public int[] readIndexes() {
        int[] indexes = new int[2];
        System.out.println("Insert nodes to calculate distance: ");
        System.out.print("1: ");
        indexes[0] = scanner.nextInt();
        scanner.nextLine();

        System.out.print("2: ");
        indexes[1] = scanner.nextInt();
        scanner.nextLine();

        return indexes;
    }

    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("\nChose an option:");
            System.out.println("  1  - Read Matrix");
            System.out.println("  2  - Calculate Floyd-Warshall's distance");
            System.out.println("  3  - Print Floyd-Warshall's adjacency matrix");
            System.out.println("  4  - Calculate Johnson's distance");
            System.out.println("  5  - Print Johnson's adjacency matrix");
            System.out.println("  6  - Print adjacency matrix");
            System.out.println("  7  - Exit");

            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    try {
                        System.out.println("Write the file path: ");
                        String path = scanner.nextLine();
                        File file = new File(path);
                        setAdjacencyMatrix(matrixReader.readAdjacencyMatrix(file));
                        System.out.println("Adjacency matrix has been read.");
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the file: " + e.getMessage());
                    }
                }

                case 2 -> {
                    if (adjacencyMatrix.length != 0) {
                        floydWarshall.setAdjacencyMatrix(adjacencyMatrix);
                        int error = floydWarshall.iterate();
                        if (error == 1) {
                            floydWarshall.printAdjacencyMatrix();
                            System.out.println("A negative cycle has been identified on this graph, Floyd-Warshall could not be calculated");
                            System.out.println("Press enter to continue.");
                            scanner.nextLine();
                            break;
                        }
                        int[] index = readIndexes();

                        while (index[0] < 0 || index[1] < 0 || index[0] >= adjacencyMatrix.length || index[1] >= adjacencyMatrix[0].length) {
                            System.out.println("Onde or more of the indexes informed exceeded the limits of the graph, please inform numbers between " + 0 + " and " + adjacencyMatrix.length);
                            index = readIndexes();
                        }

                        System.out.println("Distance between nodes " + index[0] + " and " + index[1] + ": " + floydWarshall.getDistance(index[0], index[1]));
                    } else {
                        System.out.println("Adjacency matrix is empty.");
                    }
                }

                case 3 -> {
                    if (adjacencyMatrix.length != 0) {
                        floydWarshall.setAdjacencyMatrix(adjacencyMatrix);
                        int error = floydWarshall.iterate();
                        if (error == 1) {
                            System.out.println("There is a negative cycle on that graph, Floyd-Warshall could not be calculated");
                            System.out.println("Press enter to continue.");
                            scanner.nextLine();
                        } else {
                            floydWarshall.printAdjacencyMatrix();
                        }
                    } else {
                        System.out.println("Adjacency matrix is empty.");
                    }
                }

                case 4 -> {
                    if (adjacencyMatrix.length != 0) {
                        johnson.setAdjacencyMatrix(adjacencyMatrix);
                        int error = johnson.iterate();
                        if (error == 1) {
                            johnson.printAdjacencyMatrix();
                            System.out.println("A negative cycle has been identified on this graph, Floyd-Warshall could not be calculated");
                            System.out.println("Press enter to continue.");
                            scanner.nextLine();
                            break;
                        }
                        int[] index = readIndexes();

                        while (index[0] < 0 || index[1] < 0 || index[0] >= adjacencyMatrix.length || index[1] >= adjacencyMatrix[0].length) {
                            System.out.println("Onde or more of the indexes informed exceeded the limits of the graph, please inform numbers between " + 0 + " and " + adjacencyMatrix.length);
                            index = readIndexes();
                        }

                        System.out.println("Distance between nodes " + index[0] + " and " + index[1] + ": " + johnson.getDistance(index[0], index[1]));
                    } else {
                        System.out.println("Adjacency matrix is empty.");
                    }
                }

                case 5 -> {
                    if (adjacencyMatrix.length != 0) {
                        johnson.setAdjacencyMatrix(adjacencyMatrix);
                        int error = johnson.iterate();
                        if (error == 1) {
                            System.out.println("There is a negative cycle on that graph, Johnson could not be calculated");
                            System.out.println("Press enter to continue.");
                            scanner.nextLine();
                        } else {
                            johnson.printAdjacencyMatrix();
                        }
                    } else {
                        System.out.println("Adjacency matrix is empty.");
                    }
                }

                case 6 -> {
                    this.printAdjacencyMatrix();
                }

                case 7 -> {
                    return;
                }
            }
        }
    }
}
