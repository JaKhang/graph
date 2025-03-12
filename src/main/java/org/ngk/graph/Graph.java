package org.ngk.graph;

import java.util.function.Consumer;

public interface Graph<T>{
    boolean addEdge(T src, T dest);

    boolean removeEdge(T src, T dest);

    boolean addVertex(T v);

    T removeVertex(T v);

    boolean isSimpleGraph();

    boolean isMultipleGraph();



    boolean isDirected();

    int numberOfEdges();

    int size();

    void bfs(T start,Consumer<T> callback);

    void dfs(T start, Consumer<T> callback);

    boolean containVertex(T v);

    boolean containEdge(T src,T dest);

}
