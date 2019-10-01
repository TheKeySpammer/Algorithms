package com.tks.ds;

/**
 * @see Graph#dijkstra(int)
 */
public class ExceptionContainsNegativeWeight extends Exception {

    private static final long serialVersionUID = 2L;
    ExceptionContainsNegativeWeight() {
        super("Graph contains a negative weighted edge cycle");
    }
}
