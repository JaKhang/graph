package org.ngk.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class UndirectedIncMatrixGraph<T> extends IncMatrixGraph<T> implements UnDirectedGraph<T>{
    public UndirectedIncMatrixGraph(int[][] incMatrix, List<T> data) {
        super(incMatrix, data);
    }

    public UndirectedIncMatrixGraph(int[][] incMatrix) {
        super(incMatrix);
    }

    public UndirectedIncMatrixGraph(int size) {
        super(size);
    }

    @Override
    public int degree(T v) {
        if (!containVertex(v)) return 0;
        int degree = 0;
        int row = data.indexOf(v);
        degree = Arrays.stream(incMatrix[row]).reduce(0, Integer::sum) + incMatrix[row][row];


        return degree;
    }

    @Override
    public int totalDegree() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return 0;
    }

    @Override
    public boolean isBipartiteGraph() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return false;
    }

    @Override
    public boolean isCompletedGraph() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return false;
    }

    @Override
    public boolean addEdge(T src, T dest) {
        if (!Objects.equals(src, dest))
            return linkTo(src, dest) && linkTo(dest, src);
        return linkTo(src, dest);
    }

    @Override
    public boolean removeEdge(T src, T dest) {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return false;
    }

    @Override
    public boolean addVertex(T v) {
        if (data.size() == incMatrix.length)
            increaseMatrixCapacity();
        return data.add(v);
    }

    private void increaseMatrixCapacity() {
        // todo
    }

    @Override
    public T removeVertex(T v) {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return null;
    }

    @Override
    public boolean isSimpleGraph() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return false;
    }

    @Override
    public boolean isMultipleGraph() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return false;
    }

    @Override
    public boolean isDirected() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return false;
    }

    @Override
    public int numberOfEdges() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return 0;
    }

    @Override
    public int size() {
        // TODO (PC, 12/03/2025): To change the body of an implemented method
        return 0;
    }

    @Override
    public void bfs(T start, Consumer<T> callback) {

    }

    @Override
    public void dfs(T start, Consumer<T> callback) {

    }
}
