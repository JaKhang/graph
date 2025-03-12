package org.ngk.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class IncMatrixGrap<T> implements Graph<T> {
    private int[][] incMatrix;
    private List<T> data;

    public IncMatrixGrap(int[][] incMatrix, List<T> data) {
        this.incMatrix = incMatrix;
        this.data = data;
    }

    public IncMatrixGrap(int[][] incMatrix) {
        this.incMatrix = incMatrix;
        data = new ArrayList<>(incMatrix.length);
    }

    public IncMatrixGrap(int size) {
        incMatrix = new int[size][size];
        Arrays.fill(incMatrix, new int[size]);
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

    /*
     *
     * */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] matrix : incMatrix) {
            sb.append(Arrays.toString(matrix));
        }

        return sb.toString();
    }
}
