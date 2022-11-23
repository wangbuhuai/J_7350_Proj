// Created by Dayu Wang (dwang@stchas.edu) on 2022-11-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-11-23


package main;

/** A cyclic graph */
public class Cycle extends Graph {
    // Constructor
    public Cycle(int numOfVertices) {  // Constructs a cycle with given number of vertices.
        super();
        // Add vertices.
        for (int vId = 1; vId <= numOfVertices; vId++) { addVertex(vId); }
        // Add edges.
        for (int vId = 1; vId <= numOfVertices; vId++) {
            // Find the two neighbors of the vertex.
            int nId1 = (vId - 2 + numOfVertices) % numOfVertices + 1;
            int nId2 = vId % numOfVertices + 1;
            // Add two edges.
            addEdge(vId, Math.min(nId1, nId2));
            addEdge(vId, Math.max(nId1, nId2));
        }
    }  // Time complexity: O(n)
}