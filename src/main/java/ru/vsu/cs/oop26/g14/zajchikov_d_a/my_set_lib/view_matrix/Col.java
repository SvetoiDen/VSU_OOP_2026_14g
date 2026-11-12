package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.view_matrix;

import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.Matrix;
import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix.AbstractCell;
import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix.AbstractCol;

public class Col implements AbstractCol {

    private final Matrix matrix;
    private final int col;

    public Col(Matrix matrix, int col) {
        if (matrix == null) throw new IllegalArgumentException("Matrix не может быть null");
        if (col < 0 || col >= matrix.getCntCol()) throw new IndexOutOfBoundsException("col=" + col);
        this.matrix = matrix;
        this.col = col;
    }

    private void check(int idx) {
        if (idx < 0 || idx >= size()) throw new IndexOutOfBoundsException("idx=" + idx);
    }

    @Override
    public int size() {
        return matrix.getCntRow();
    }

    @Override
    public Object getValue(int idx) {
        check(idx);
        return matrix.getValue(idx, col);
    }

    @Override
    public void setValue(int idx, Object value) {
        check(idx);
        matrix.setValue(idx, col, value);
    }

    @Override
    public AbstractCell getCell(int idx) {
        return new Cell(matrix, idx, col);
    }

    @Override
    public void move(int idx, boolean raiseUp) {
        check(idx);
        int target = raiseUp ? col + idx : col - idx;
        if (target < 0 || target >= size()) return;
        swapRows(idx, target);
    }

    private void swapRows(int r1, int r2) {
        int cols = matrix.getCntCol();
        for (int j = 0; j < cols; j++) {
            Object a = matrix.getValue(r1, j);
            Object b = matrix.getValue(r2, j);
            matrix.setValue(r1, j, b);
            matrix.setValue(r2, j, a);
        }
    }

    @Override
    public void sort() {
        matrix.sortByColumn(col, true);
    }

    @Override
    public Object[] toArray() {
        Object[] colArray = new Object[size()];
        for (int i = 0; i < col; i++) {
            colArray[i] = matrix.getValue(col, i);
        }
        return colArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            sb.append(getValue(i));
            if (i < size() - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}