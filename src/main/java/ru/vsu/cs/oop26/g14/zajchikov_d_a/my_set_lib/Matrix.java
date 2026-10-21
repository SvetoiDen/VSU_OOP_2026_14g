package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib;

import java.util.*;

public class Matrix {

    private int row;
    private int col;
    private Object[][] matrix;

    public Matrix(int row, int col) {
        if (row < 0 || col < 0)
            throw new IllegalArgumentException("Размеры не могут быть отрицательными");
        this.row = row;
        this.col = col;
        this.matrix = createMatrixNull();
    }

    public Matrix(Matrix other) {
        if (other == null) throw new IllegalArgumentException("Источник не может быть null");
        this.row = other.row;
        this.col = other.col;
        this.matrix = new Object[this.row][this.col];
        for (int i = 0; i < this.row; i++)
            System.arraycopy(other.matrix[i], 0, this.matrix[i], 0, this.col);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        if (row < 0) throw new IllegalArgumentException("row < 0");
        Object[][] old = this.matrix;
        int oldRow = this.row;
        int oldCol = this.col;
        this.row = row;
        this.matrix = new Object[this.row][this.col];
        int copyRows = Math.min(this.row, oldRow);
        for (int i = 0; i < copyRows; i++)
            System.arraycopy(old[i], 0, this.matrix[i], 0, oldCol);
    }

    public void setCol(int col) {
        if (col < 0) throw new IllegalArgumentException("col < 0");
        Object[][] old = this.matrix;
        int oldRow = this.row;
        int oldCol = this.col;
        this.col = col;
        this.matrix = new Object[this.row][this.col];
        int copyCols = Math.min(this.col, oldCol);
        for (int i = 0; i < oldRow; i++)
            System.arraycopy(old[i], 0, this.matrix[i], 0, copyCols);
    }

    protected Object[][] getMatrix() {
        return matrix;
    }

    private boolean isOutOfBounds(int r, int c) {
        return r < 0 || r >= this.row || c < 0 || c >= this.col;
    }

    public Object getValue(int r, int c) {
        if (isOutOfBounds(r, c)) return null;
        return matrix[r][c];
    }

    public void setValue(int r, int c, Object value) {
        if (isOutOfBounds(r, c))
            throw new IndexOutOfBoundsException("Индекс вне диапазона: (" + r + "," + c + ")");
        matrix[r][c] = value;
    }

    protected Object[][] createMatrixNull() {
        return new Object[row][col];
    }

    public Object[] getRow(int r) {
        if (r < 0 || r >= row) throw new IndexOutOfBoundsException("row=" + r);
        Object[] result = new Object[col];
        System.arraycopy(matrix[r], 0, result, 0, col);
        return result;
    }

    public Object[] getColumn(int c) {
        if (c < 0 || c >= col) throw new IndexOutOfBoundsException("col=" + c);
        Object[] result = new Object[row];
        for (int i = 0; i < row; i++) result[i] = matrix[i][c];
        return result;
    }

    public Matrix getRows(int fromInclusive, int toExclusive) {
        return subMatrix(fromInclusive, toExclusive, 0, col);
    }

    public Matrix getColumns(int fromInclusive, int toExclusive) {
        return subMatrix(0, row, fromInclusive, toExclusive);
    }

    public Matrix subMatrix(int rowFrom, int rowTo, int colFrom, int colTo) {
        if (rowFrom < 0 || rowTo > row || rowFrom > rowTo)
            throw new IndexOutOfBoundsException("Неверный диапазон строк: " + rowFrom + ".." + rowTo);
        if (colFrom < 0 || colTo > col || colFrom > colTo)
            throw new IndexOutOfBoundsException("Неверный диапазон столбцов: " + colFrom + ".." + colTo);
        Matrix res = new Matrix(rowTo - rowFrom, colTo - colFrom);
        for (int i = rowFrom; i < rowTo; i++)
            for (int j = colFrom; j < colTo; j++)
                res.matrix[i - rowFrom][j - colFrom] = matrix[i][j];
        return res;
    }

    public List<Integer> findRowsByColumnValue(int c, Object value) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < row; i++)
            if (Objects.equals(matrix[i][c], value)) res.add(i);
        return res;
    }

    public List<Integer> findColumnsByRowValue(int r, Object value) {
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < col; j++)
            if (Objects.equals(matrix[r][j], value)) res.add(j);
        return res;
    }

    public void sortByColumn(int c, boolean ascending) {
        if (c < 0 || c >= col) throw new IndexOutOfBoundsException("col=" + c);
        final int dir = ascending ? 1 : -1;
        final int cc = c;
        Arrays.sort(matrix, (r1, r2) -> dir * compareValues(r1[cc], r2[cc]));
    }

    public void sortByRow(int r, boolean ascending) {
        if (r < 0 || r >= row) throw new IndexOutOfBoundsException("row=" + r);
        final int dir = ascending ? 1 : -1;
        Integer[] order = new Integer[col];
        for (int j = 0; j < col; j++) order[j] = j;
        Arrays.sort(order, (a, b) -> dir * compareValues(matrix[r][a], matrix[r][b]));

        Object[][] newMatrix = new Object[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                newMatrix[i][j] = matrix[i][order[j]];
        matrix = newMatrix;
    }

    public static int compareValues(Object a, Object b) {
        if (a == b) return 0;
        if (a == null) return 1;
        if (b == null) return -1;
        if (a instanceof Number && b instanceof Number) {
            return Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue());
        }
        if (a instanceof String && b instanceof String) {
            return ((String) a).compareTo((String) b);
        }
        int byClass = a.getClass().getName().compareTo(b.getClass().getName());
        if (byClass != 0) return byClass;
        return String.valueOf(a).compareTo(String.valueOf(b));
    }

    public Map<Object, List<Integer>> groupRowsByColumn(int c) {
        if (c < 0 || c >= col) throw new IndexOutOfBoundsException("col=" + c);
        Map<Object, List<Integer>> groups = new LinkedHashMap<>();
        for (int i = 0; i < row; i++) {
            groups.computeIfAbsent(matrix[i][c], k -> new ArrayList<>()).add(i);
        }
        return groups;
    }

    public Map<Object, List<Integer>> groupColumnsByRow(int r) {
        if (r < 0 || r >= row) throw new IndexOutOfBoundsException("row=" + r);
        Map<Object, List<Integer>> groups = new LinkedHashMap<>();
        for (int j = 0; j < col; j++) {
            groups.computeIfAbsent(matrix[r][j], k -> new ArrayList<>()).add(j);
        }
        return groups;
    }

    @Override
    public String toString() {
        MatrixPrinter matrixPrinter = new MatrixPrinter();
        return matrixPrinter.buildTable(this);
    }
}