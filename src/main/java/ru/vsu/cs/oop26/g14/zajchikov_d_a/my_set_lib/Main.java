package ru.vsu.cs.oop26.g14.zajchikov_d_a.my_set_lib;

public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix(10, 10);

        // Headers
        m.setValue(0, 0, "Fruit");
        m.setValue(0, 1, "Price, RUB/kg");
        m.setValue(0, 2, "Kcal");
        m.setValue(0, 3, "Weight, g");
        m.setValue(0, 4, "Sugar, g");
        m.setValue(0, 5, "Vitamin C, mg");
        m.setValue(0, 6, "Protein, g");
        m.setValue(0, 7, "Fat, g");
        m.setValue(0, 8, "Fiber, g");
        m.setValue(0, 9, "Rating");

        // Apple
        m.setValue(1, 0, "Apple");
        m.setValue(1, 1, 120);
        m.setValue(1, 2, 52);
        m.setValue(1, 3, 180);
        m.setValue(1, 4, 10);
        m.setValue(1, 5, 4.6);
        m.setValue(1, 6, 0.3);
        m.setValue(1, 7, 0.2);
        m.setValue(1, 8, 2.4);
        m.setValue(1, 9, 4.7);

        // Banana
        m.setValue(2, 0, "Banana");
        m.setValue(2, 1, 95);
        m.setValue(2, 2, 89);
        m.setValue(2, 3, 120);
        m.setValue(2, 4, 12);
        m.setValue(2, 5, 8.7);
        m.setValue(2, 6, 1.1);
        m.setValue(2, 7, 0.3);
        m.setValue(2, 8, 2.6);
        m.setValue(2, 9, 4.5);

        // Orange
        m.setValue(3, 0, "Orange");
        m.setValue(3, 1, 150);
        m.setValue(3, 2, 47);
        m.setValue(3, 3, 200);
        m.setValue(3, 4, 9);
        m.setValue(3, 5, 53.2);
        m.setValue(3, 6, 0.9);
        m.setValue(3, 7, 0.1);
        m.setValue(3, 8, 2.4);
        m.setValue(3, 9, 4.8);

        // Pear
        m.setValue(4, 0, "Pear");
        m.setValue(4, 1, 140);
        m.setValue(4, 2, 57);
        m.setValue(4, 3, 160);
        m.setValue(4, 4, 10);
        m.setValue(4, 5, 4.3);
        m.setValue(4, 6, 0.4);
        m.setValue(4, 7, 0.1);
        m.setValue(4, 8, 3.1);
        m.setValue(4, 9, 4.3);

        // Grape
        m.setValue(5, 0, "Grape");
        m.setValue(5, 1, 210);
        m.setValue(5, 2, 69);
        m.setValue(5, 3, 150);
        m.setValue(5, 4, 16);
        m.setValue(5, 5, 3.2);
        m.setValue(5, 6, 0.6);
        m.setValue(5, 7, 0.2);
        m.setValue(5, 8, 0.9);
        m.setValue(5, 9, 4.4);

        // Kiwi
        m.setValue(6, 0, "Kiwi");
        m.setValue(6, 1, 180);
        m.setValue(6, 2, 61);
        m.setValue(6, 3, 90);
        m.setValue(6, 4, 9);
        m.setValue(6, 5, 92.7);
        m.setValue(6, 6, 1.1);
        m.setValue(6, 7, 0.5);
        m.setValue(6, 8, 3.0);
        m.setValue(6, 9, 4.6);

        // Lemon
        m.setValue(7, 0, "Lemon");
        m.setValue(7, 1, 130);
        m.setValue(7, 2, 29);
        m.setValue(7, 3, 100);
        m.setValue(7, 4, 2.5);
        m.setValue(7, 5, 53.0);
        m.setValue(7, 6, 1.1);
        m.setValue(7, 7, 0.3);
        m.setValue(7, 8, 2.8);
        m.setValue(7, 9, 4.2);

        // Peach
        m.setValue(8, 0, "Peach");
        m.setValue(8, 1, 165);
        m.setValue(8, 2, 39);
        m.setValue(8, 3, 150);
        m.setValue(8, 4, 8.4);
        m.setValue(8, 5, 6.6);
        m.setValue(8, 6, 0.9);
        m.setValue(8, 7, 0.3);
        m.setValue(8, 8, 1.5);
        m.setValue(8, 9, 4.4);

        // Pineapple
        m.setValue(9, 0, "Pineapple");
        m.setValue(9, 1, 190);
        m.setValue(9, 2, 50);
        m.setValue(9, 3, 250);
        m.setValue(9, 4, 10);
        m.setValue(9, 5, 47.8);
        m.setValue(9, 6, 0.5);
        m.setValue(9, 7, 0.1);
        m.setValue(9, 8, 1.4);
        m.setValue(9, 9, 4.5);

        System.out.println(m);
    }
}
