package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib;

public class MatrixPrinter {
    public String buildTable(Matrix matrixClass) {
        StringBuilder out = new StringBuilder();
        String nl = System.lineSeparator();
        String sep = "\t|\t";

        int col = matrixClass.getCol();
        int row = matrixClass.getRow();
        Object[][] matrix = matrixClass.getMatrix();

        // 1. Ширина каждого столбца
        int[] width = new int[col];
        for (int j = 0; j < col; j++) {
            width[j] = String.valueOf(j).length();
            for (int i = 0; i < row; i++) {
                String s = String.valueOf(matrix[i][j]);
                if (s.length() > width[j]) width[j] = s.length();
            }
        }

        // 2. Ширина колонки с индексами строк
        int rowIndexWidth = row == 0 ? 1 : String.valueOf(row - 1).length();

        // 3. Шапка
        StringBuilder line = new StringBuilder();
        for (int k = 0; k < rowIndexWidth; k++) line.append(' ');
        for (int j = 0; j < col; j++) {
            line.append(sep).append(padLeft(String.valueOf(j), width[j]));
        }
        out.append(line).append(nl);

        // 4. Разделитель
        line.setLength(0);
        for (int k = 0; k < rowIndexWidth; k++) line.append('-');
        for (int j = 0; j < col; j++) {
            line.append(sep);
            for (int k = 0; k < width[j]; k++) line.append('-');
        }
        out.append(line).append(nl);

        // 5. Данные
        for (int i = 0; i < row; i++) {
            line.setLength(0);
            line.append(padLeft(String.valueOf(i), rowIndexWidth));
            for (int j = 0; j < col; j++) {
                line.append(sep);
                Object v = matrix[i][j];
                String s = String.valueOf(v);
                if (v instanceof Number) {
                    line.append(padLeft(s, width[j]));
                } else {
                    line.append(padRight(s, width[j]));
                }
            }
            out.append(line).append(nl);
        }

        return out.toString();
    }

    private static String padLeft(String s, int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < width; i++) sb.append(' ');
        return sb.append(s).toString();
    }

    private static String padRight(String s, int width) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length(); i < width; i++) sb.append(' ');
        return sb.toString();
    }
}