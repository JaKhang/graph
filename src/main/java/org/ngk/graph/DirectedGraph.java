package org.ngk.graph;

public interface DirectedGraph<T> extends Graph<T> {
    int outDegree(T v);
    int inDegree(T v);
    int totalOutDegree();
    int totalInDegree();
}
