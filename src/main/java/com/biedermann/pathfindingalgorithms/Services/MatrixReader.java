package com.biedermann.pathfindingalgorithms.Services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MatrixReader {
    public double[][] readAdjacencyMatrix(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);

        int size = Integer.parseInt(lines.get(0));
        double[][] adjacencyMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            String [] numbers = lines.get(i + 1).split(" ");
            for (int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = numbers[j].equalsIgnoreCase("INF") ? Double.MAX_VALUE : Double.parseDouble(numbers[j]);
            }
        }
        return adjacencyMatrix;
    }
}
