package com.tks.ds;

import java.util.*;

import java.io.*;


// TODO: Make graph generic (Vertex can be generic)
/**
 * Adjacency list representation of Graph using TreeMap data structure
 * The Edges are stored in LinkedList {@link TreeMap} with key as the root vertex<br/>
 * Graph provides following algorithms
 * <ul>
 *     <li>Pure Graph creation (graph with no parallel edges and no loops)</li>
 *     <li>Depth First Search</li>
 *     <li>Kruskal's Minimum spanning tree</li>
 *     <li>Prim's Minimum spanning tree</li>
 * </ul>
 * @author Aamir Mushtaq Siddiqui
 * @version JDK 1.8
 */
public class Graph {

//    Use this variable to toggle DEBUG log
//    TODO: Use a logger to DEBUG log
    private static final boolean DEBUG = false;

    TreeMap<Integer, LinkedList<Edge>> graph;

    /**
     * Constructor that initializes new empty Adjacency List
     */
    Graph() {
        this.graph = new TreeMap<>();
    }

    /**
     * Constructor that Makes a deep copy of another graph
     * @param gh The graph to make a deep copy of
     */
    Graph(Graph gh) {
        this.graph = new TreeMap<>();
        for (Map.Entry<Integer, LinkedList<Edge>> entry : gh.graph.entrySet()) {
            LinkedList<Edge> ll = entry.getValue();
            LinkedList<Edge> copy = new LinkedList<>();
            for (Edge edge : ll) {
                copy.add(new Edge(edge));
            }
            this.graph.put(entry.getKey(), copy);
        }
    }

    /**
     * Edge is a connection between two vertex having a weight
     * Edge can be bidirectional or unidirectional
     */
    static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int weight;
        private boolean bi;

        /**
         * Creates a bidirectional edge with 0 weight and no initial vertex
         * @param to The final vertex to which edge meets
         */
        Edge(int to) {
            this.to = to;
            this.from = 0;
            this.weight = 0;
            this.bi = true;
        }

        /**
         * Creates a bidirectional edge with 0 weight
         * @param to The final vertex to which edge meets
         * @param from Initial vertex from which edge starts
         */
        Edge(int to, int from) {
            this(to);
            this.from = from;
        }

        /**
         * Creates a bidirectional edge from given parameters
         * @param to The final vertex to which edge meets
         * @param from Initial vertex from which edge starts
         * @param weight Weight of the edge
         */
        Edge(int to, int from, int weight) {
            this(to, from);
            this.weight = weight;
        }

        /**
         * Creates an edge with given parameters
         * @param to The final vertex to which edge meets
         * @param from Initial vertex from which edge starts
         * @param weight Weight of the edge
         * @param bi Bidirectional edge or not
         */
        Edge(int to, int from, int weight, boolean bi) {
            this(to, from ,weight);
            this.bi = bi;
        }

        /**
         * Instantiate an edge as deep of another edge
         * @param edge The edge whose copy is to be instantiated
         */
        Edge (Edge edge) {
            this.to = edge.to;
            this.weight = edge.weight;
            this.from = edge.from;
            this.bi = edge.bi;
        }

        /**
         * Compares edge weight first then check if they are in same direction then
         * if edge is bidirectional, if it is then checks if the reverse edges are same
         * @param edge The edge to be compared with
         * @return difference of edge weight if difference is 0 then returns 0 if edge
         * directions are same else 1
         */
        @Override
        public int compareTo(Edge edge) {
            if (this.weight != edge.weight) {
                return this.weight - edge.weight;
            } else {
                // Check if same direction edge are same
                if (this.from == edge.from && this.to == edge.to) {
                    return 0;
                }
                // Ceck if reverse direction edge are same
                if (this.bi && this.from == edge.to && this.to == edge.from) {
                    return 0;
                }
                return 1;
            }
        }

        /**
         * Creates a new instance of given edge that exchanged from and to vertex
         * @return Returns a new instance of Edge with reverse direction
         */
        public Edge getReverse() {
            return new Edge(this.from, this.to, this.weight);
        }

        /**
         * Checks if the current edge is parallel to other edge
         * @param other the edge with which the comparison is to be made
         * @return Boolean of the equality of from and to of both edges
         */
        public boolean isParallel(Edge other) {
            return (this.from == other.from && this.to == other.to);
        }

        /**
         * Checks if an edge is a loop, i.e. if from == to
         * @return Boolean of equality of from and to of this edge
         */
        public boolean isLoop() {
            return this.from == this.to;
        }

        /**
         * @see #getComparator()
         * @return Comparator of type edge
         */
        public static Comparator<Edge> getComparator() {
            return (edge1, edge2) -> {
                if (edge1.weight != edge2.weight) {
                    return edge1.weight - edge2.weight;
                } else {
                    if (edge1.from == edge2.from && edge1.to == edge2.to) {
                        return 0;
                    }
                    if ((edge1.bi || edge2.bi) && edge1.from == edge2.to && edge1.to == edge2.from) {
                        return 0;
                    }
                    return 1;
                }
            };

        }


        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return this.from + " --" + this.weight + "--> " + this.to;
        }
    }

    /**
     * Instantiates a graph from data in a file
     * Data in the file should be formatted as follow
     * VERTEXroot1  VERTEXchild1,WEIGHT VERTEXchild2,WEIGHT ...
     * .
     * .
     * @param filename the filename from which data is to fetched
     *                 NOTE: Must be relative to the location from where the
     *                 program is ran
     * @return Graph instantiated from the data entered in file
     * @throws IOException FileNotFound and other IO related exception
     */
    public static Graph fromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        Graph gh = new Graph();
        while (true) {
            String input = br.readLine();
            if (input == null) break;
            StringTokenizer st = new StringTokenizer(input);
            int root = Integer.parseInt(st.nextToken());
            LinkedList<Edge> ll = new LinkedList<>();
            while (st.hasMoreTokens()) {
                Edge edge = processEdge(st.nextToken());
                edge.setFrom(root);
                ll.add(edge);
            }
            gh.graph.put(root, ll);
        }
        br.close();
        return gh;
    }

    /**
     * Creates a new instance of a graph containing a deep copy of all the vertex
     * and edges of original graph except <strong>Loops</strong> and <strong>Parallel edges</strong>
     * @return New Instance of graph deep copy of current graph without loop and parallel edges
     */
    public Graph makePureGraph() {
        Graph gh = new Graph(this);
        for (Map.Entry<Integer, LinkedList<Edge>> entry : gh.graph.entrySet()) {
            LinkedList<Edge> edges = entry.getValue();
            LinkedList<Edge> toRemove = new LinkedList<>();
            for (int i = 0; i < edges.size(); i++) {
//                Remove Loops
                if (edges.get(i).isLoop()) {
                    toRemove.add(edges.get(i));
                    continue;
                }
//                Remove parallel edges
                Edge edgeA = edges.get(i);
                for (int j = i + 1; j < edges.size(); j++) {
                    Edge edgeB = edges.get(j);
                    if (edgeA.isParallel(edgeB)) {
                        toRemove.add(edges.get(edgeA.weight > edgeB.weight ? i : j));
                    }
                }
            }
            edges.removeAll(toRemove);
        }
        return gh;
    }

    /**
     * Prints the graph to Standard output in Adjacency List style
     */
    public void printGraph() {
        for (Map.Entry<Integer, LinkedList<Edge>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Edge edge : entry.getValue()) {
                System.out.print("(" + edge.getTo() + ", " + edge.getWeight() + "), ");
            }
            System.out.println();
        }
    }

    /**
     * Creates an ArrayList of vertex contained in Depth First Search traversal order
     * @param start The starting vertex from which DFS starts
     * @return ArrayList of Integers containing vertex in DFS,
     *         If start vertex is not in graph returns empty ArrayList
     */
    public ArrayList<Integer> DFS(int start) {
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[this.graph.size()];
        DFS(start, path, visited);
        return path;
    }

    private void DFS(int parent, ArrayList<Integer> path, boolean[] visited) {
        path.add(parent);
        visited[parent - 1] = true;
        if (this.graph.containsKey(parent)) {
            LinkedList<Edge> edges = this.graph.get(parent);
            for (Edge edge : edges) {
                if (!visited[edge.to - 1]) {
                    DFS(edge.to, path, visited);
                }
            }
        }
    }

    /**
     * Generates A Minimum Spanning Tree of current graph.
     * The generated minimum spanning tree is a new instance of graph containing only edges required in
     * minimum spanning tree.
     * Stores all the edges in a TreeSet and iterates over them thus retrieving them in sorted order
     * Uses greedy approach to include all the minimum edges till all vertex are covered.
     * That is till number of edges = number of vertex - 1.
     * Uses UnionFind algorithm to detect cycles {@link UnionFind}.
     * @return  A new instance of Graph containing Minimum Spanning Tree of invoking graph
     * @see TreeSet
     */
    public Graph kruskalMST() {

        if (DEBUG) {
            System.out.println();
            System.out.println("-------------DEBUG LOG (KURSKAL) START-----------------");
        }
        Graph gh = new Graph();
        for (int key : this.graph.keySet()) {
            gh.graph.put(key, new LinkedList<>());
        }

        TreeSet<Edge> ts = new TreeSet<>(Edge.getComparator());

        for (Map.Entry<Integer, LinkedList<Edge>> entry : this.graph.entrySet()) {
            for (Edge edge : entry.getValue()) {
                ts.add(new Edge(edge));
            }
        }

        // ts contains edges ordered according to weight
        // gh is our new graph
        // Kruskal algorithm starts
        int edgeCount = 0;
        int vertexCount = this.graph.size();
        UnionFind uf = new UnionFind(vertexCount);
        // Key Padding for uf to bring it to 0
        int padding = this.graph.firstKey();
        if (DEBUG) {
            System.out.printf("VertexCount: %d\nPadding: %d\n", vertexCount, padding);
            System.out.println("Initial UF: ");
            System.out.println(Arrays.toString(uf.getParent()));
        }
        for (Edge edge : ts) {
            if (edgeCount == vertexCount - 1) break;
            try {

                uf.union(edge.from - padding, edge.to - padding);
                gh.graph.get(edge.getFrom()).add(new Edge(edge));
                // Bidirection support
                Edge revEdge = edge.getReverse();
                gh.graph.get(revEdge.getFrom()).add(new Edge(revEdge));
                edgeCount++;
                if (DEBUG) {
                    System.out.println("Adding edge " + edge);
                    System.out.println("Current Edge Count " + edgeCount);
                    System.out.println("Union Find set: " + Arrays.toString(uf.getParent()));
                }
            } catch (Exception ex) {
                // Skip edge that causes cycle
                if (DEBUG) {
                    System.out.println("Edge: " + edge + " makes a cycle");
                }
            }
        }

        if (DEBUG) {
            System.out.println("Sorted edges order: ");
            System.out.println(ts);
        }


        if (DEBUG) {
            System.out.println("-------------DEBUG LOG (KURSKAL) END-----------------");
            System.out.println();
        }
        return gh;
    }


    /**
     * Creates a Minimum Spanning Tree using Prim's Algorithm.
     * Creates a new Instance of Graph containing only edges of original Graph which forms minimum spanning tree.
     * Algorithm starts by first getting a pure graph {@link #makePureGraph()}.
     * Then it starts from first vertex.
     * Uses PriorityQueue {@link PriorityQueue} to store Standing edges.
     * Keep adding vertex and smallest edge from Standing edges till all vertex are covered
     * Also keep removing edges from the temporary instance of Current graph generated above.
     * @return A new instance of graph containing Minimum Spanning Tree
     */
    public Graph primsMst() {

        if (DEBUG) {
            System.out.println();
            System.out.println("-------------DEBUG LOG (PRIMS) START-----------------");
        }
        Graph gh = new Graph();
//        Remove all the loops and parellel edges
        Graph pg = this.makePureGraph();
//      Keep a set of Standing Edges
        PriorityQueue<Edge> stnd = new PriorityQueue<>(Edge.getComparator());
        int numberOfnodes = pg.graph.size();
        if (DEBUG) {
            System.out.println("Number of nodes: "+numberOfnodes);
        }
        // Initially put first vertex
        gh.graph.put(pg.graph.firstKey(), new LinkedList<>());
        stnd.addAll(pg.graph.firstEntry().getValue());
        
        if (DEBUG)
            System.out.println("Initially Standing edges: "+stnd);
        
        while (stnd.size() > 0 && gh.graph.size() < numberOfnodes) {
            // While removing is possible and loops are formed
            Edge smallest = stnd.remove();

            if (DEBUG)
                System.out.println("Current smallest Edge: "+smallest);

            while (stnd.size() > 0 && gh.graph.containsKey(smallest.to)) {                
                if (DEBUG)
                    System.out.println("Since cycle is formed finding new smallest");
                smallest = stnd.remove();

                if (DEBUG)
                    System.out.println("Current Smallest Edge: "+smallest);
            }
            // Include the smallest edge in new graph
            gh.graph.get(smallest.from).add(new Edge(smallest));
            LinkedList<Edge> temp = new LinkedList<>();
            temp.add(new Edge(smallest.getReverse()));
            gh.graph.put(smallest.to, temp);
            if (DEBUG) {
                System.out.println("Current Graph: ");
                gh.printGraph();
                System.out.println();
            }

            // Remove the smallest edge from the original graph
            Edge copyOFSmall = new Edge(smallest);
            pg.graph.get(smallest.from).removeIf(e -> e.compareTo(copyOFSmall) == 0);
            pg.graph.get(smallest.to).removeIf(e -> e.compareTo(copyOFSmall) == 0);
            
            if (DEBUG) {
                System.out.println("Original Graph: ");
                pg.printGraph();
                System.out.println();
            }

            // Update standing with edges of the new node
            stnd.addAll(pg.graph.get(smallest.to));
            if (DEBUG) {
                System.out.println("Current Standing: "+stnd);
            }
        }

        if (DEBUG) {
            System.out.println("-------------DEBUG LOG (PRIMS) END-----------------");
            System.out.println();
        }
        return gh;
    }

    /**
     * Dijkstra's Algorithm to find minimum path from a start vertex.
     * This function introduces a new class Vertex which stores vertex data and total cost to reach that vertex
     * and parent of the vertex.
     * It first stores the generated path in a temporary ArrayList from which it Generates a doubly sided graph.
     * All the working of this function can be seen when DEBUG is on.
     * @param start The starting vertex.
     * @return  A new instance of Graph which contains all the edges which comprise a shortest path from start
     * to all other vertex.
     */
    public Graph dijkstra(int start) {
//        Finds dijkstra's shortest path from a start vertex and returns a graph containing the route
//        Entry in priority queue
        class Vertex{
            int weight;
            int parent;
            int vertex;
            Vertex(int vertex, int weight, int parent) {
                this.weight = weight;
                this.parent = parent;
                this.vertex = vertex;
            }

            @Override
            public String toString() {
                String wg = Integer.toString(weight);
                if (weight == Integer.MAX_VALUE) {
                    wg = "INF";
                }
                return vertex+" <-- "+wg+" -- "+parent;
            }

        }
        if (!this.graph.containsKey(start)){
            return null;
        }

//        Get a temporary pure graph
        Graph minPath = this.makePureGraph();

//        Initialize a priority queue with INF values and no parents
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(vertex -> vertex.weight));
        for (int node :
                minPath.graph.keySet()) {
            int weight = Integer.MAX_VALUE;
            if (node == start) {
                weight = 0;
            }
            pq.add(new Vertex(node, weight, -1));
        }

        if (DEBUG) {
            System.out.println("Initial priority queue");
            pq.forEach(System.out::println);
        }

//        A stack to store the paths
        ArrayList<Vertex> path = new ArrayList<>();
//        Remove lowest and update weight and parent of each adjacent vertex
        while (pq.size() > 0 && pq.peek().weight < Integer.MAX_VALUE) {

//            Get min vertex and all its adjacent edges
            Vertex min = pq.remove();
            path.add(min);
            LinkedList<Edge> edges = minPath.graph.get(min.vertex);

            if (DEBUG) {
                System.out.println();
                System.out.println("Current minimum vertex: "+min);
                System.out.println("Edges of min: "+edges);
                System.out.println();
            }

            for (Edge edge : edges) {
                for (Vertex vertex : pq) {
//                    Find adjacent vertex in priority queue and update there data
                    if (vertex.vertex == edge.to) {

                        if (DEBUG) {
                            System.out.println("Checking vertex: "+vertex);
                        }

                        if (vertex.weight > edge.weight) {
//                            Update if a shorter path exists
                            if (vertex.weight > edge.weight + min.weight) {
//                                Update Priority Queue
                                pq.removeIf(v -> v.vertex == vertex.vertex);
                                pq.add(new Vertex(vertex.vertex, edge.weight + min.weight, min.vertex));

                                if(DEBUG) {
                                    System.out.println("Check true");
                                    System.out.println("Updated Vertex weight: "+(edge.weight + min.weight));
                                    System.out.println();
                                }
                            }
                            break;
                        }
                    }
                }
            }

        }

//        Dijkstra completed now Make graph according to path
        for (Vertex vertex : path) {
            int child  = vertex.vertex;
            int parent = vertex.parent;
//            Remove parent
            LinkedList<Edge> ll = minPath.graph.get(child);
            ll.removeIf(edge -> edge.to != parent);
        }


//        Make bidirectional
        for (Map.Entry<Integer, LinkedList<Edge>> entry:
            minPath.graph.entrySet()){
            LinkedList<Edge> edges = entry.getValue();
            for (Edge edge : edges) {
                LinkedList<Edge> child = minPath.graph.get(edge.to);
                Edge rev = edge.getReverse();
                if (child.stream().noneMatch(e -> e.from == rev.from && e.to == rev.to)) {
                    child.add(rev);
                }
            }
        }
        return minPath;
    }

    private static Edge processEdge(String rawEdge) {
        int to = 0, weight = 0;
        if (rawEdge.indexOf(',') != -1) {
            to = Integer.parseInt(rawEdge.substring(0, rawEdge.indexOf(',')).trim());
            weight = Integer.parseInt(rawEdge.substring(rawEdge.indexOf(',') + 1).trim());
        }
        return new Edge(to, 0, weight);
    }

}