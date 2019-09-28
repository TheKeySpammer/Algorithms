package com.tks.ds;

/**
 * A disjoint-set data structure is a data structure that keeps track of a set of elements
 * partitioned into a number of disjoint (non-overlapping) subsets.
 * A union-find algorithm is an algorithm that performs two useful operations on such a data structure:
 * <ul>
 *  <li>
 *  <strong>Find: </strong> Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
 *  </li>
 *  <li>
 *  <strong> Union: </strong>Join two subsets into a single subset.
 *  </li>
 * </ul>
 *  Uses Rank Union Find to optimize union operation.
 *  Used for Cycle delectation.
 * @author Aamir Mushtaq Siddiqui
 * @see Graph#kruskalMST()
 */
public class UnionFind {
    private int[] parent;
    private int[] rank;

    /**
     * Instantiates UnionFind with a total n sets
     * @param n Number of initial disjointed set
     */
    UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        makeSet();
    }

    private void makeSet() {
        for (int i = 0; i < this.parent.length; i++) {
            this.parent[i] = i;
        }
    }

    /**
     * Finds the parent of i.
     * Caches the parent by making it immediate parent of i.
     * @param i The element whose parent is to be found
     * @return An integer denoting the parent element of i.
     */
    int find(int i) {
        if (this.parent[i] != i) {
            this.parent[i] = find(this.parent[i]);
        }
        return this.parent[i];
    }

    /**
     * Finds the parent of i without caching, thus doesn't modify original
     * parent array.
     * @param i The element whose parent is to be found
     * @return An integer denoting the parent element of i
     */
    int findWithoutCache(int i) {
        if (this.parent[i] == i) return i;
        return findWithoutCache(this.parent[i]);
    }

    /**
     * Checks if two elements are contained in same set.
     * Used in Cycle detection.
     * @param i The element which is required to be checked.
     * @param j Other element which is required to be checked if it is in same set as i.
     * @return Boolean value denoting if they are in same set (true) or not (false)
     */
    private boolean checkIfsameParent(int i, int j) {
        return findWithoutCache(i) == findWithoutCache(j);
    }

    /**
     * Performs Union operation by rank.
     * That is always attaches smaller depth trees under larger depth trees.
     * @param i The element on which union is to be performed
     * @param j Other element on which union is to performed
     * @throws ExceptionCycleDetected When i and j have same parent a cycle detected.
     */
    void union(int i, int j) throws ExceptionCycleDetected {
        if (checkIfsameParent(i, j)) throw new ExceptionCycleDetected();
        int xroot = find(i), yroot = find(j);
        if (xroot == yroot) return;

        if (this.rank[xroot] < this.rank[yroot]) {
            this.parent[xroot] = yroot;
        } else if (this.rank[xroot] > this.rank[yroot]) {
            this.parent[yroot] = xroot;
        } else {
            this.parent[yroot] = xroot;
            this.rank[xroot]++;
        }
    }

    public int[] getParent() {
        return parent;
    }

    public int[] getRank() {
        return rank;
    }
}

