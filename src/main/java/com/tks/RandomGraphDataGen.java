package com.tks;

import com.tks.ds.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;

public class RandomGraphDataGen {
    private int vertexCount;
    private float density;
    private int minWeight;
    private int maxWeight;
    private String filename;

    private RandomGraphDataGen(int vertexCount, float density, int minWeight, int maxWeight, String filename) {
        this.vertexCount = vertexCount;
        this.density = density;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.filename = filename;
        genGraph();
    }

    static class Builder {
        private int vertexCount = 10;
        private float density = 0.5f;
        private int minWeight = 5;
        private int maxWeight = minWeight + 20;
        private String filename = "./randGraphData";

        public Builder vertexCount(int val) {
            this.vertexCount = val;
            return this;
        }
        public Builder density(float val) {
            this.density = val;
            return this;
        }
        public Builder minWeight(int val) {
            this.minWeight = val;
            return this;
        }

        public Builder maxWeight(int val) {
            if (val <= this.minWeight) {
                this.maxWeight = this.minWeight + val;
            }
            else {
                this.maxWeight = val;
            }
            return this;
        }

        public Builder filename(String val) {
            this.filename = val;
            return this;
        }

        public RandomGraphDataGen build() {
            return new RandomGraphDataGen(this.vertexCount, this.density, this.minWeight, this.maxWeight, this.filename);
        }


    }

    private void genGraph() {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(filename))) {
            Graph gh = new Graph();
            int maxChild = (int) (vertexCount * density);
            for (int i = 1; i <= vertexCount; i++) {
                int children = (int)(Math.random() * (maxChild - 1) + 1);
                LinkedList<Graph.Edge> edges = new LinkedList<>();
                if (gh.graph.containsKey(i)) {
                    edges = gh.graph.get(i);
                }
                for (int j = 0; j < children; j++) {
                    int child = (int)(Math.random() * (vertexCount - 1) + 1);
                    int weight = (int) (Math.random() * (maxWeight - minWeight) + minWeight);
                    Graph.Edge  edge = new Graph.Edge(child, i, weight);
                    edges.add(edge);
                    if (gh.graph.containsKey(child)) {
                        gh.graph.get(child).add(edge.getReverse());
                    }else{
                        LinkedList<Graph.Edge> temp = new LinkedList<>();
                        temp.add(edge.getReverse());
                        gh.graph.put(child, temp);
                    }
                }
                gh.graph.put(i, edges);
            }

            for(Map.Entry<Integer, LinkedList<Graph.Edge>> entry: gh.graph.entrySet()){
                pw.print(entry.getKey()+" ");
                for (Graph.Edge edge :
                        entry.getValue()) {
                    pw.print(edge.getTo()+","+edge.getWeight()+" ");
                }
                pw.println();
            }
            System.out.println("Generated graph: ");
            gh.printGraph();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
