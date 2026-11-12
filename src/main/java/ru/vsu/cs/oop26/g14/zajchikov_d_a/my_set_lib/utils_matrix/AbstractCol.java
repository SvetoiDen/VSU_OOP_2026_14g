package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix;

public interface AbstractCol {
    void move(int idx, boolean up_or_down);
    Object getValue(int idx);
    void sort();
    void setValue(int idx, Object value);

    // расширения
    int size();
    AbstractCell getCell(int idx);
    Object[] toArray();
}
