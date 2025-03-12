package org.ngk.graph;

import javax.xml.crypto.Data;
import java.util.*;

public abstract class IncMatrixGraph<T> implements Graph<T> {
    protected int[][] incMatrix;
    protected List<T> data;

    public IncMatrixGraph(int[][] incMatrix, List<T> data) {
        this.incMatrix = incMatrix;
        this.data = data;
    }

    public IncMatrixGraph(int[][] incMatrix) {
        this.incMatrix = incMatrix;
        data = new ArrayList<>(incMatrix.length);
    }

    public IncMatrixGraph(int size) {
        incMatrix = new int[size][size];
        for (int i = 0; i < incMatrix.length; i++) {
            incMatrix[i] = new int[size];
        }
        data = new ArrayList<>(size);
    }

    protected boolean linkTo(T src, T dest) {
        int srcIndex = data.indexOf(src);
        int destIndex = data.indexOf(dest);
        if (srcIndex == -1) {
            data.add(src);
            srcIndex = data.size() - 1;
        }

        if (destIndex == -1) {
            data.add(dest);
            destIndex = data.size() - 1;
        }

        incMatrix[srcIndex][destIndex] += 1;
        return true;
    }

    @Override
    public int size() {
        return data.size();
    }

    protected boolean unlinkTo(T src, T dest) {
        int srcIndex = data.indexOf(src);
        int destIndex = data.indexOf(dest);
        if (srcIndex == -1) {
            return false;
        }
        if (destIndex == -1) {
            return false;
        }
        if (incMatrix[srcIndex][destIndex] > 0) {
            incMatrix[srcIndex][destIndex] -= 1;
            return false;
        }
        return true;
    }

    @Override
    public boolean containVertex(T v) {
        return data.contains(v);
    }

    @Override
    public boolean containEdge(T src, T dest) {
        int srcIndex = data.indexOf(src);
        int destIndex = data.indexOf(dest);
        if (srcIndex == -1 || destIndex == -1) {
            return false;
        }
        return incMatrix[srcIndex][destIndex] > 0;
    }

    @Override
    public Set<T> getAdjSet(T v) {
        int row = data.indexOf(v);
        Set<T> set = new HashSet<>();
        for (int i = 0; i < incMatrix.length; i++) {
            if (incMatrix[row][i] > 0)
                set.add(data.get(i));
        }
        return set;
    }

    @Override
    public boolean setData(int index, T data) {
        return this.data.set(index, data) == null;
    }

    /*
     *
     * */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append(" ");
        for (T d : data) {
            sb.append(d).append("  ");
        }
        sb.append("\n");
        for (int i = 0; i < incMatrix.length; i++) {
            sb.append(data.get(i)).append("\t").append(Arrays.toString(incMatrix[i])).append("\n");
        }

        return sb.toString();
    }
}
