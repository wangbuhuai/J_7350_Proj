// Created by Dayu Wang (dwang@stchas.edu) on 2022-11-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-11-23


package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        // Open the input and output file.
        FileInputStream inputFile = new FileInputStream("inputFiles/partOne/inputPartOne_4.txt");
        Scanner scanner = new Scanner(inputFile);
        FileOutputStream outputFile = new FileOutputStream("outputFiles/partOne/outputPartOne_4.txt");
        PrintWriter writer = new PrintWriter(outputFile);

        Graph graph = null;

        // Read in the type of the graph.
        String type = scanner.next().trim();
        int numOfVertices = scanner.nextInt();

        if (type.equals("COMPLETE")) { graph = new CompleteGraph(numOfVertices); }
        if (type.equals("CYCLE")) { graph = new Cycle(numOfVertices); }
        if (type.equals("RANDOM")) {
            String distribution = scanner.next().trim();
            int numOfEdges = scanner.nextInt();
            if (distribution.equals("UNIFORM")) {
                graph = new RandomGraph(numOfVertices, numOfEdges, RandomGraph.Dist.UNIFORM);
            }
            if (distribution.equals("TIERED")) {
                graph = new RandomGraph(numOfVertices, numOfEdges, RandomGraph.Dist.TIERED);
            }
        }

        if (graph != null) { writer.println(graph); }

        // Close the input and output file.
        writer.close();
        outputFile.close();
        scanner.close();
        inputFile.close();
    }
}