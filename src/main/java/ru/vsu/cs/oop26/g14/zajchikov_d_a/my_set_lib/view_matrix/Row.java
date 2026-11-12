package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.view_matrix;

import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.Matrix;
import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix.AbstractCell;
import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix.AbstractRow;

public class Row implements AbstractRow {

    private final Matrix matrix;
    private final int row;

    public Row(Matrix matrix, int row) {
        if (matrix == null) throw new IllegalArgumentException("Matrix не может быть null");
        if (row < 0 || row >= matrix.getCntRow()) throw new IndexOutOfBoundsException("row=" + row);
        this.matrix = matrix;
        this.row = row;
    }

    @Override
    public int size() {
        return matrix.getCntCol();
    }

    private void check(int idx) {
        if (idx < 0 || idx >= size()) throw new IndexOutOfBoundsException("idx=" + idx);
    }

    @Override
    public Object getValue(int idx) {
        check(idx);
        return matrix.getValue(row, idx);
    }

    @Override
    public void setValue(int idx, Object value) {
        check(idx);
        matrix.setValue(row, idx, value);
    }

    @Override
    public AbstractCell getCell(int idx) {
        return new Cell(matrix, row, idx);
    }

    @Override
    public void move(int idx, boolean raiseUp) {
        check(idx);
        int target = raiseUp ? row + idx : row - idx;
        if (target < 0 || target >= size()) return;
        swapCols(idx, target);
    }

    private void swapCols(int c1, int c2) {
        int rows = matrix.getCntRow();
        for (int i = 0; i < rows; i++) {
            Object a = matrix.getValue(i, c1);
            Object b = matrix.getValue(i, c2);
            matrix.setValue(i, c1, b);
            matrix.setValue(i, c2, a);
        }
    }

    @Override
    public void sort() {
        matrix.sortByRow(row, true);
    }

    @Override
    public Object[] toArray() {
        return matrix.getMatrix()[row];
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