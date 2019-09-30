package com.tks;

import com.tks.ds.Graph;

import java.io.IOException;

public class Experiment {
    public static void main(String[] args) throws IOException {
        Graph gh = Graph.fromFile("./data/graphData");
        System.out.println("Original Graph");
        gh.printGraph();
        System.out.println();
        System.out.println("Dijkstra shortest path from edge 1");
        gh.dijkstra(1).printGraph();
        System.out.println();
        System.out.println("Dijkstra shortest path from edge 6");
        gh.dijkstra(6).printGraph();
        System.out.println();
        System.out.println("Prim's Minimum Spanning Tree");
        gh.primsMst().printGraph();
        System.out.println();
    }
}