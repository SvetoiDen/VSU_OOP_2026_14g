package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib;

import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix.MatrixPrinter;
import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.view_matrix.Col;
import ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.view_matrix.Row;

import java.util.*;

public class Matrix {
    private static Object[][] matrix;

    public Matrix(int row, int col) {
        if (row < 0 || col < 0)
            throw new IllegalArgumentException("Размеры не могут быть отрицательными");
        matrix = createMatrixNull(row, col);
    }

    public Matrix(Matrix other) {
        if (other == null) throw new IllegalArgumentException("Источник не может быть null");
        int r = other.getCntRow();
        int c = other.getCntCol();
        matrix = new Object[r][c];
        for (int i = 0; i < r; i++)
            System.arraycopy(other.matrix[i], 0, this.matrix[i], 0, c);
    }

    public int getCntRow() {
        return matrix.length;
    }

    public int getCntCol() {
        return matrix.length == 0 ? 0 : matrix[0].length;
    }

    public void setRow(int newRow) {
        if (newRow < 0) throw new IllegalArgumentException("row < 0");
        int currentRow = getCntRow();
        int currentCol = getCntCol();
        Object[][] newMatrix = new Object[newRow][currentCol];
        int copyRows = Math.min(newRow, currentRow);
        for (int i = 0; i < copyRows; i++)
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, currentCol);
        this.matrix = newMatrix;
    }

    public void setCol(int newCol) {
        if (newCol < 0) throw new IllegalArgumentException("col < 0");
        int currentRow = getCntRow();
        int currentCol = getCntCol();
        Object[][] newMatrix = new Object[currentRow][newCol];
        int copyCols = Math.min(newCol, currentCol);
        for (int i = 0; i < currentRow; i++)
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, copyCols);
        matrix = newMatrix;
    }

    public Object[][] getMatrix() {
        return matrix;
    }

    private Object[][] createMatrixNull(int row, int col) {
        return new Object[row][col];
    }

    private boolean isOutOfBounds(int r, int c) {
        return r < 0 || r >= getCntRow() || c < 0 || c >= getCntCol();
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

    public Row getRow(int r) {
        int row = getCntRow();
        if (r < 0 || r >= row) throw new IndexOutOfBoundsException("row=" + r);
        return new Row(this, r);
    }

    public Col getColumn(int c) {
        int col = getCntCol();
        if (c < 0 || c >= col) throw new IndexOutOfBoundsException("col=" + c);
        return new Col(this, c);
    }

    public Matrix getRows(int fromInclusive, int toExclusive) {
        return subMatrix(fromInclusive, toExclusive, 0, getCntCol());
    }

    public Matrix getColumns(int fromInclusive, int toExclusive) {
        return subMatrix(0, getCntRow(), fromInclusive, toExclusive);
    }

    public Matrix subMatrix(int rowFrom, int rowTo, int colFrom, int colTo) {
        int row = getCntRow();
        int col = getCntCol();
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
        int row = getCntRow();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < row; i++)
            if (Objects.equals(matrix[i][c], value)) res.add(i);
        return res;
    }

    public List<Integer> findColumnsByRowValue(int r, Object value) {
        int col = getCntCol();
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < col; j++)
            if (Objects.equals(matrix[r][j], value)) res.add(j);
        return res;
    }

    public void sortByColumn(int c, boolean ascending) {
        if (c < 0 || c >= getCntCol()) throw new IndexOutOfBoundsException("col=" + c);
        final int dir = ascending ? 1 : -1;
        final int cc = c;
        Arrays.sort(matrix, (r1, r2) -> dir * compareValues(r1[cc], r2[cc]));
    }

    public void sortByRow(int r, boolean ascending) {
        int row = getCntRow();
        int col = getCntCol();
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
        if (c < 0 || c >= getCntCol()) throw new IndexOutOfBoundsException("col=" + c);
        int row = getCntRow();
        Map<Object, List<Integer>> groups = new LinkedHashMap<>();
        for (int i = 0; i < row; i++) {
            groups.computeIfAbsent(matrix[i][c], k -> new ArrayList<>()).add(i);
        }
        return groups;
    }

    public Map<Object, List<Integer>> groupColumnsByRow(int r) {
        if (r < 0 || r >= getCntRow()) throw new IndexOutOfBoundsException("row=" + r);
        int col = getCntCol();
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