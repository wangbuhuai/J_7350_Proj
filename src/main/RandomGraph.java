// Created by Dayu Wang (dwang@stchas.edu) on 2022-11-23

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-11-23


package main;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/** A graph with random edge distribution */
public class RandomGraph extends Graph {
    public enum Dist { UNIFORM, TIERED }  // Represents the distribution to random edges.

    /** An edge in a graph */
    private static class Edge implements Comparable<Edge> {
        // Data fields
        int s;  // Stores the ID of the first vertex the edge connects.
        int d;  // Stores the ID of the second vertex the edge connects.

        // Constructors

        Edge(int s, int d) {  // Default constructor
            this.s = s;
            this.d = d;
        }  // Time complexity: O(1)

        Edge(Edge other) {  // Copy constructor
            s = other.s;
            d = other.d;
        }  // Time complexity: O(1)

        /** Compares this edge with other edge.
            @param other: another edge to be compared
            @return: Negative if this edge is a predecessor of the other edge;
                     Positive if this edge is a successor of the other edge
        */
        @Override
        public int compareTo(Edge other) {
            if (s != other.s) { return s - other.s; }
            return d - other.d;
        }  // Time complexity: O(1)
    }

    // Constructs a random graph with given number of vertices, number of edges, and distribution.
    public RandomGraph(int numOfVertices, int numOfEdges, Dist distribution) {
        super();
        PriorityQueue<Edge> randomEdges = null;
        if (distribution == Dist.UNIFORM) { randomEdges = randomEdgesUniform(allEdges(numOfVertices), numOfEdges); }
        if (distribution == Dist.TIERED) { randomEdges = randomEdgeTiered(dividedEdges(numOfVertices), numOfEdges); }
        // Add vertices.
        for (int vId = 1; vId <= numOfVertices; vId++) { addVertex(vId); }
        // Add Edges.
        if (randomEdges != null) {
            while (!randomEdges.isEmpty()) {
                Edge edge = randomEdges.poll();
                addEdge(edge.s, edge.d);
                addEdge(edge.d, edge.s);
            }
        }
    }  // Time complexity: O(n ^ 2)

    // Methods

    /** Generates all edges in a complete graph with given number of vertices.
        @param numOfVertices: number of vertices in the complete graph
        @return: a list of edges
    */
    private static List<Edge> allEdges(int numOfVertices) {
        List<Edge> result = new ArrayList<>();
        for (int vId1 = 1; vId1 <= numOfVertices; vId1++) {
            for (int vId2 = vId1 + 1; vId2 <= numOfVertices; vId2++) { result.add(new Edge(vId1, vId2)); }
        }
        return result;
    }  // Time complexity: O(n ^ 2)

    /** Generates two sets of edges, one in first 10% of vertices and the other in rest vertices.
        @param numOfVertices: number of vertices in the graph
        @return: two lists of edges, one in first 10% of vertices and the other in rest vertices.
    */
    private static List<List<Edge>> dividedEdges(int numOfVertices) {
        int maxId = numOfVertices / 10;  // Maximum vertex ID in first 10%
        List<List<Edge>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        for (int vId1 = 1; vId1 <= numOfVertices; vId1++) {
            for (int vId2 = vId1 + 1; vId2 <= numOfVertices; vId2++) {
                if (vId1 <= maxId && vId2 <= maxId) { result.get(0).add(new Edge(vId1, vId2)); }
                if (vId1 >= maxId && vId2 >= maxId) { result.get(1).add(new Edge(vId1, vId2)); }
            }
        }
        return result;
    }

    /** Pick a given number of non-repeating random edges from all edges.
        All edges have roughly the same probability to be picked.
        @param edges: all candidate edges
        @param numOfEdges: number of edges to pick
        @return: a priority queue of all picked edges
    */
    private static PriorityQueue<Edge> randomEdgesUniform(List<Edge> edges, int numOfEdges) {
        PriorityQueue<Edge> result = new PriorityQueue<>();
        for (int i = 0, n = edges.size(); i < numOfEdges; i++) {
            Random generator = new Random(System.currentTimeMillis());
            int next = Math.abs(generator.nextInt()) % n;
            result.offer(new Edge(edges.get(next)));
            edges.set(next, edges.get(n-- - 1));
        }
        return result;
    }  // Time complexity: O(n ^ 2)

    /** Pick a given number of non-repeating random edges from two sets of edges.
        Half edges will be picked from the first set of edges.
        Rest edges will be picked from the second set of edges.
        All edges have roughly the same probability to be picked.
        @param edges: two sets of candidate edges
        @param numOfEdges: number of edges to pick
        @return: a priority queue of all picked edges
    */
    private static PriorityQueue<Edge> randomEdgeTiered(List<List<Edge>> edges, int numOfEdges) {
        int first = numOfEdges / 2, second = numOfEdges - first;
        PriorityQueue<Edge> result = new PriorityQueue<>();
        for (int i = 0, n = edges.get(0).size(); i < first; i++) {
            Random generator = new Random(System.currentTimeMillis());
            int next = Math.abs(generator.nextInt()) % n;
            result.offer(new Edge(edges.get(0).get(next)));
            edges.get(0).set(next, edges.get(0).get(n-- - 1));
        }
        for (int j = 0, n = edges.get(1).size(); j < second; j++) {
            Random generator = new Random(System.currentTimeMillis());
            int next = Math.abs(generator.nextInt()) % n;
            result.offer(new Edge(edges.get(1).get(next)));
            edges.get(1).set(next, edges.get(1).get(n-- - 1));
        }
        return result;
    }  // Time complexity: O(n ^ 2)
}