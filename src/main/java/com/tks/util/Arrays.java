package com.tks.util;

import java.util.Comparator;

public class Arrays {

    public static <T> boolean isSorted(T a[], Comparator<T> comp) {
        boolean rev = comp.compare(a[a.length - 1], a[0]) < 0;
        for (int i = 1; i < a.length; i++) {
            if (rev) {
                if (comp.compare(a[a.length - 1], a[0]) > 0) return false;
            }
            else {
                if (comp.compare(a[a.length - 1], a[0]) < 0) return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<T>> boolean isSorted(T a[]) {
        return isSorted(a, Comparator.naturalOrder());
    }
}