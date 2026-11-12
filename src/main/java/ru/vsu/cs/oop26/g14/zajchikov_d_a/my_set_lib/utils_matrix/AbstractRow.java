package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix;

public interface AbstractRow {
    void move(int idx, boolean left_or_right);
    Object getValue(int idx);
    void sort();
    void setValue(int idx, Object value);

    // расширения
    int size();
    AbstractCell getCell(int idx);
    Object[] toArray();
}
