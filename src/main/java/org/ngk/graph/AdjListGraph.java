package org.ngk.graph;

import java.util.*;
import java.util.function.Consumer;

public abstract class AdjListGraph<T> implements Graph<T> {
    private final Map<T, List<T>> adjList;

    protected Map<T, List<T>> adjList(){
        return adjList;
    }

    public AdjListGraph(Map<T, List<T>> adjList) {
        this.adjList = adjList;
    }

    public AdjListGraph() {
        adjList = new HashMap<>();
    }

    @Override
    public void bfs(T start, Consumer<T> callback) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<T>();
        queue.offer(start);
        while (!queue.isEmpty()){
            T current = queue.poll();
            callback.accept(current);
            for (var t : adjList.get(current)){
                if (visited.add(t)) queue.offer(t);
            }
        }
    }

    @Override
    public void dfs(T start, Consumer<T> callback) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()){
            T current = stack.pop();
            callback.accept(current);
            for (var t : adjList.get(current)){
                if (visited.add(t)) stack.push(t);
            }
        }
    }

    @Override
    public boolean containVertex(T v) {
        return adjList.containsKey(v);
    }

    @Override
    public int size() {
        return adjList.size();
    }

    protected boolean linkTo(T src, T dest){
        if (!containVertex(src)) adjList.put(src, new ArrayList<>());
        if (!containVertex(dest)) adjList.put(dest, new ArrayList<>());
        return adjList.get(src).add(dest);
    }
    protected boolean unlinkTo(T src, T dest){
        return adjList.get(src).remove(dest);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        adjList.forEach((t, ts) -> sb.append(t).append(":").append(ts).append("\n"));
        return sb.toString();
    }

    @Override
    public boolean isSimpleGraph() {
        return adjList().values().stream().allMatch(ts -> ts.stream().distinct().count() == ts.size());
    }

    @Override
    public boolean setData(int index, T data) {
        return false;
    }

    @Override
    public Set<T> getAdjSet(T v) {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return Set.of();
    }
}
