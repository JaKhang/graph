package org.ngk.graph;

import java.util.Collections;
import java.util.LinkedList;

public class DirectedAdjListGraph<T> extends AdjListGraph<T> implements DirectedGraph<T> {
    @Override
    public int outDegree(T v) {
        return adjList().get(v).size();

    }

    @Override
    public int inDegree(T v) {
        return (int) adjList().get(v).stream().filter(e -> e.equals(v)).count();
    }

    @Override
    public int totalOutDegree() {
        return adjList().keySet().parallelStream().reduce(0, (integer, v) -> integer + outDegree(v), Integer::sum);

    }

    @Override
    public int totalInDegree() {
        return adjList().keySet().parallelStream().reduce(0, (integer, v) -> integer + inDegree(v), Integer::sum);
    }

    @Override
    public boolean addEdge(T src, T dest) {
        if (src == null || dest == null)
            return false;
        return linkTo(src, dest);
    }

    @Override
    public boolean removeEdge(T src, T dest) {
        if (src == null || dest == null)
            return false;
        return unlinkTo(src, dest);
    }

    @Override
    public boolean addVertex(T v) {
        if (!adjList().containsKey(v)) {
            adjList().put(v, new LinkedList<>());
            return true;
        }
        return false;
    }

    @Override
    public T removeVertex(T v) {
        adjList().remove(v);
        for (var t : adjList().values()) {
            t.remove(v);
        }
        return v;
    }


    @Override
    public boolean isMultipleGraph() {
        return !isSimpleGraph();
    }


    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int numberOfEdges() {
        return totalInDegree();
    }

    @Override
    public boolean containEdge(T src, T dest) {
        return adjList().getOrDefault(src, Collections.emptyList()).contains(dest);
    }
}
