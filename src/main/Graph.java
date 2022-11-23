// Created by Dayu Wang (dwang@stchas.edu) on 2022-11-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-11-23


package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A graph presented by adjacency list */
public abstract class Graph {
    /** A vertex in a graph */
    private static class Vertex {
        // Data fields
        final int id;  // Stores the unique ID of the vertex.
        final List<Vertex> adj;  // Stores the adjacency list of the vertex.

        // Constructor
        Vertex(int id) {  // Constructs a vertex with a given ID.
            this.id = id;
            adj = new LinkedList<>();
        }  // Time complexity: O(1)
    }

    // Data field
    private final List<Vertex> vertices;  // Stores all the vertices (with their adjacency lists).

    // Default constructor
    protected Graph() { vertices = new ArrayList<>(); }  // Time complexity: O(1)

    // Methods

    /** Inserts a vertex with given ID to the graph.
        @param id: ID of the vertex to insert to the graph
    */
    protected final void addVertex(int id) { vertices.add(new Vertex(id)); }  // Time complexity: O(1)

    /** Inserts an edge from a given vertex to another given vertex.
        @param s: ID of the first vertex the edge connects
        @param d: ID of the second vertex the edge connects
    */
    protected final void addEdge(int s, int d) {
        vertices.get(s - 1).adj.add(vertices.get(d - 1));
    }  // Time complexity: O(1)

    /** Customizes the output format of the graph.
        @return: a string representing the output format of the graph
    */
    @Override
    public final String toString() {
        // Output the number of vertices.
        StringBuilder output = new StringBuilder().append(vertices.size()).append('\n');
        // Output the pointers of the starting point of the adjacency lists of the vertices.
        int pointer = 1 + vertices.size();
        for (int vId = 1; vId <= vertices.size(); vId++) {
            output.append(pointer).append('\n');
            pointer += vertices.get(vId - 1).adj.size() + (vertices.get(vId - 1).adj.isEmpty() ? 1 : 0);
        }
        // Output all the adjacency lists of the vertices.
        for (int vId = 1; vId <= vertices.size(); vId++) {
            if (vertices.get(vId - 1).adj.isEmpty()) { output.append("null\n"); }
            Iterator<Vertex> it = vertices.get(vId - 1).adj.iterator();
            while (it.hasNext()) { output.append(it.next().id).append('\n'); }
        }
        return output.deleteCharAt(output.length() - 1).toString();
    }  // Time complexity: O(n + m)
}