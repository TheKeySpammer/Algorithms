package com.tks.ds;

/**
 * @see UnionFind#union(int, int)
 */
class ExceptionCycleDetected extends Exception {
    private static final long serialVersionUID = 1L;

    ExceptionCycleDetected() {
        super("Cycle detected");
    }
}
