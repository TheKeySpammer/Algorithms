package com.tks;

import com.tks.ds.ExceptionContainsNegativeWeight;
import com.tks.ds.Graph;

import java.io.IOException;

public class Experiment {
    public static void main(String[] args) throws IOException {
        Graph gh = Graph.fromFile("./data/smallGraph");
        System.out.println("Original Graph");
        gh.printGraph();
        System.out.println();
        System.out.println("Dijkstra shortest path from edge 1");
        gh.dijkstra(5).printGraph();
        System.out.println();
        System.out.println("Prim's Minimum Spanning Tree");
        gh.primsMst().printGraph();
        System.out.println();
        System.out.println("Bellman Ford");
        try {
            gh.bellmanFord(5).printGraph();
        }catch (ExceptionContainsNegativeWeight ex) {
            ex.printStackTrace();
        }
//        new RandomGraphDataGen.Builder().filename("./data/smallGraph").density(0.5f).vertexCount(5).minWeight(5).maxWeight(15).build();
    }
}