import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import utils.MatrixReader;
import algorithms.*;

public class Main {
    private static final int EXECUTIONS = 11;
    public static void main(String[] args) {
        int[] file_sizes = {10, 15, 20, 25, 50, 100, 500, 1000};
        char[] method = {'L', 'J', 'F'};

        for (int run = 0; run < method.length; run++) {
            for (int folder_index = 0; folder_index < file_sizes.length; folder_index++) {
                FloydWarshall floydWarshall = new FloydWarshall(new int[0][0]);
                Johnson johnson = new Johnson((new int[0][0]), (method[run] == 'J' ? 'M' : 'L'));
                String file_name = "graph_" + String.valueOf(file_sizes[folder_index]);
                File folder = new File("../inputs/"+file_name);
                File[] files = folder.listFiles();
                String algorithm = "";
                if (method[run] == 'F') {
                    algorithm = "Floyd_Warshal";
                }

                if (method[run] == 'J') {
                    algorithm = "JohnsonMatriz";
                }

                if (method[run] == 'L') {
                    algorithm = "JohnsonLista";
                }

                try {
                    FileWriter csvWriter = new FileWriter("../results/" + algorithm + file_name + "_execution_times.csv");
                    MatrixReader matrixReader = new MatrixReader();
                    
                    @SuppressWarnings("unchecked")
                    ArrayList<Long>[] times = new ArrayList[EXECUTIONS];
                    for (int i = 0; i < files.length; i++) {
                        times[i] = new ArrayList<>();
                    }
                    
                    csvWriter.append("File");
                    for (int i = 1; i < EXECUTIONS; i++) {
                        csvWriter.append(", time(ns)" + i);
                    }
                    csvWriter.append("\n");
                    
                    int i = 0;
                    for (File file : files) {
                        List<Long> time = new ArrayList<>();
                        System.out.println(file.getName());
                        for (int j = 0; j < EXECUTIONS; j++) {
                            int[][] adjacencyMatrix;
                            
                            try {
                                adjacencyMatrix = matrixReader.readAdjacencyMatrix(file);
                            } catch (Exception e) {
                                return;
                            }
                            
                            long startTime;
                            if (method[run] == 'F') {
                                floydWarshall.setAdjacencyMatrix(adjacencyMatrix);
                                startTime = System.nanoTime();
                                floydWarshall.iterate();
                            } else {
                                johnson.setAdjacencyMatrix(adjacencyMatrix);
                                startTime = System.nanoTime();
                                johnson.iterate();
                            }
                            
                            long endTime = System.nanoTime();
                            long elapsedTime = endTime - startTime;
                            time.add(elapsedTime);
                        }
                        
                        times[i].addAll(time);
                        
                        csvWriter.append(file.getName()+", ");
                        for (int j = 1; j < EXECUTIONS; j++) {
                            csvWriter.append(String.valueOf(times[i].get(j))  + ", ");
                        }
                        csvWriter.append("\n");
                        i++;    
                        FileWriter distance_output = new FileWriter("../results/" + file_name + "/" + algorithm + "_"+ file.getName());
                        if (method[run] == 'F') {
                            floydWarshall.writeAdjacencyMatrix(distance_output);
                        } else {
                            johnson.writeAdjacencyMatrix(distance_output);
                        }
                        distance_output.close();
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