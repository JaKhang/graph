package org.ngk.graph;

public interface UnDirectedGraph<T> extends Graph<T> {
    int degree(T v);
    int totalDegree();
    boolean isBipartiteGraph();
    boolean isCompletedGraph();
}
