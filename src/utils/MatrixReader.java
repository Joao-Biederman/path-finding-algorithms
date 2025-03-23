package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixReader {
    public int[][] readAdjacencyMatrix(File file) throws IOException {
        List<String> lines = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        bufferedReader.close();

        int size = Integer.parseInt(lines.get(0));
        int[][] adjacencyMatrix = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            String [] numbers = lines.get(i + 1).split(" ");
            for (int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = numbers[j].equalsIgnoreCase("INF") ? Integer.MAX_VALUE : Integer.parseInt(numbers[j]);
            }
        }
        return adjacencyMatrix;
    }
}
