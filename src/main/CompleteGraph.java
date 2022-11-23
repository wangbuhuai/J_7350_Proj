// Created by Dayu Wang (dwang@stchas.edu) on 2022-11-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-11-23


package main;

/** A complete graph */
public class CompleteGraph extends Graph {
    // Constructor
    public CompleteGraph(int numOfVertices) {  // Constructs a complete graph with given number of vertices.
        super();
        // Add vertices.
        for (int vId = 1; vId <= numOfVertices; vId++) { addVertex(vId); }
        // Add edges.
        for (int vId1 = 1; vId1 <= numOfVertices; vId1++) {
            for (int vId2 = 1; vId2 <= numOfVertices; vId2++) {
                if (vId2 == vId1) { continue; }
                addEdge(vId1, vId2);
            }
        }
    }  // Time complexity: O(n ^ 2)
}