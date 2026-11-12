package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.view_matrix;

import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.Matrix;
import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix.AbstractCell;

public class Cell implements AbstractCell {

    private final Matrix matrix;
    private int row;
    private int col;

    public Cell(Matrix matrix, int row, int col) {
        if (matrix == null) throw new IllegalArgumentException("CELL(Matrix не может быть null)");
        if (row < 0 || row >= matrix.getCntRow()) throw new IndexOutOfBoundsException("CELL(row=" + row + ")");
        if (col < 0 || col >= matrix.getCntCol()) throw new IndexOutOfBoundsException("CELL(col=" + col + ")");
        this.matrix = matrix;
        this.row = row;
        this.col = col;
    }

    @Override
    public Object getValue() {
        return matrix.getValue(row, col);
    }

    @Override
    public void setValue(Object value) {
        matrix.setValue(row, col, value);
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    private void setRow(int row) {
        if (row < 0 || row >= getRow()) throw new IndexOutOfBoundsException("CELL(row not found)");
        this.row = row;
    }
    private void setCol(int col) {
        if (col < 0 || col >= getRow()) throw new IndexOutOfBoundsException("CELL(col not found)");
        this.col = col;
    }

    @Override
    public void move(int new_row, int new_col) {
        int old_row = getRow();
        int old_col = getCol();

        setRow(new_row);
        setCol(new_col);
        Object temp;
        Object[][] tempmatrix = matrix.getMatrix();

        temp = tempmatrix[old_row][old_col];
        tempmatrix[old_row][old_col] = tempmatrix[new_row][new_col];
        tempmatrix[new_row][new_col] = temp;
    }

    @Override
    public String toString() {
        return "Cell(" + row + "," + col + ")=" + getValue();
    }
}