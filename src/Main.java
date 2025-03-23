import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.MatrixReader;
import algorithms.*;

public class Main {
    private static final int EXECUTIONS = 6;
    public static void main(String[] args) {
        int[] file_sizes = {1000, 10000};
        char[] method = {'F', 'J'};

        for (int run = 0; run < method.length; run++) {
            for (int folder_index = 0; folder_index < file_sizes.length; folder_index++) {
                FloydWarshall floydWarshall = new FloydWarshall(new int[0][0]);
                Johnson johnson = new Johnson((new int[0][0]));
                String file_name = "graph_" + String.valueOf(file_sizes[folder_index]);
                File folder = new File("./"+file_name);
                File[] files = folder.listFiles();
                String algorithm = (method[run] == 'F' ? "Floyd_Warshal" : "Johnson") + "_";
                try {
                    FileWriter csvWriter = new FileWriter(algorithm + file_name + "_execution_times.csv");
                    MatrixReader matrixReader = new MatrixReader();
                    System.out.println("teste");
                    
                    @SuppressWarnings("unchecked")
                    ArrayList<Long>[] times = new ArrayList[EXECUTIONS];
                    for (int i = 0; i < files.length; i++) {
                        times[i] = new ArrayList<>();  // Initialize each element as an empty ArrayList
                    }
                    
                    csvWriter.append("File");
                    for (int i = 1; i < EXECUTIONS; i++) {
                        csvWriter.append(", time(ns)" + i);
                    }
                    csvWriter.append("\n");
                    
                    int i = 0;
                    for (File file : files) {
                        List<Long> time = new ArrayList<>();
                        for (int j = 0; j < EXECUTIONS; j++) {
                            int[][] adjacencyMatrix;
                            
                            try {
                                adjacencyMatrix = matrixReader.readAdjacencyMatrix(file);
                            } catch (Exception e) {
                                return;
                            }
                            
                            long startTime;
                            if (method[run] == 'F') {
                                startTime = System.nanoTime();
                                floydWarshall.setAdjacencyMatrix(adjacencyMatrix);
                                floydWarshall.iterate();
                            } else {
                                startTime = System.nanoTime();
                                johnson.setAdjacencyMatrix(adjacencyMatrix);
                                johnson.iterate();
                            }
                            
                            long endTime = System.nanoTime();
                            long elapsedTime = endTime - startTime;
                            time.add(elapsedTime);
                            System.out.println(time);                            
                        }
                        
                        times[i].addAll(time);
                        
                        csvWriter.append(file.getName()+", ");
                        System.out.println("wrote filename:" + file.getName());
                        for (int j = 1; j < EXECUTIONS; j++) {
                            System.out.println("wrote Number:" + String.valueOf(times[i].get(j)));
                            csvWriter.append(String.valueOf(times[i].get(j))  + ", ");
                        }
                        csvWriter.append("\n");
                        i++;
                    }
                    csvWriter.close();
                            
                } catch (IOException e) {
                    System.out.println("Error writing to CSV file: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error writing to CSV file: " + e.getMessage());
                }
            }
        }
    }
}