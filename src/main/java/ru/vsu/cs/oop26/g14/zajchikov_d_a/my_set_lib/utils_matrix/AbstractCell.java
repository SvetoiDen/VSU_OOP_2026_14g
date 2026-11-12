package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib.utils_matrix;

public interface AbstractCell {
    Object getValue();
    void setValue(Object value);
    int getRow();
    int getCol();
    void move(int new_row, int new_col);
}
