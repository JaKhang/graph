package org.ngk.graph;

import java.util.*;
import java.util.function.Consumer;

public class UnDirectedAdjListGraph<T> extends AdjListGraph<T> implements UnDirectedGraph<T> {

    @Override
    public int degree(T v) {
        if (!containVertex(v))
            return 0;
        List<T> tmp = adjList().get(v);
        return (int) (tmp.size() + tmp.stream().filter(e -> e.equals(v)).count());
    }

    @Override
    public int totalDegree() {
        return adjList().keySet().parallelStream().reduce(0, (integer, t) -> integer + degree(t), Integer::sum);
    }

    @Override
    public boolean addEdge(T src, T dest) {
        if (src == null || dest == null)
            return false;
        if (src.equals(dest))
            return linkTo(dest, src);
        return linkTo(dest, src) && linkTo(src, dest);
    }

    @Override
    public boolean removeEdge(T src, T dest) {
        if (src == null || dest == null)
            return false;
        if (src.equals(dest))
            return unlinkTo(dest, src);
        return unlinkTo(dest, src) && unlinkTo(src, dest);
    }

    @Override
    public boolean addVertex(T v) {
        if (containVertex(v))
            return false;
        adjList().put(v, new LinkedList<>());
        return true;
    }

    @Override
    public T removeVertex(T v) {
        if (!containVertex(v))
            return null;
        adjList().remove(v);
        adjList().values().forEach(ts -> ts.remove(v));
        return v;
    }



    @Override
    public boolean isMultipleGraph() {
        return adjList().values().stream().allMatch(ts -> ts.stream().distinct().count() != ts.size());
    }

    @Override
    public boolean isBipartiteGraph() {
        int n = size();
        Map<T, Integer> colors = new HashMap<>();
        for (var e : adjList().entrySet()) {
            var current = e.getKey();
            if (!colors.containsKey(current)) {
                Queue<T> queue = new LinkedList<>();
                queue.offer(current);
                colors.put(current, 1);

                while (!queue.isEmpty()) {
                    T v = queue.poll();
                    for (var neighbor : adjList().get(v)) {
                        if (!colors.containsKey(neighbor)) {
                            colors.put(neighbor, -colors.get(v));
                            queue.offer(neighbor);
                        } else if (colors.get(neighbor) == colors.get(v)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    @Override
    public boolean isCompletedGraph() {
        int v = size();
        return numberOfEdges() == v * (v - 1) / 2;
    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int numberOfEdges() {
        return totalDegree() / 2;
    }



    @Override
    public boolean containEdge(T src, T dest) {
        if (!containVertex(src) || !containVertex(dest))
            return false;
        return adjList().get(dest).contains(src) && adjList().get(src).contains(dest);
    }


}
