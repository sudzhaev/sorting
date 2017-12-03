package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.Comparator;

public class MergeSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    public MergeSort() {
    }

    public MergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] data) {
        T[] result = mergeSort(data, 0, data.length - 1);
        System.arraycopy(result, 0, data, 0, result.length);
    }

    @SuppressWarnings("unchecked")
    private T[] mergeSort(T[] data, int left, int right) {
        if (left == right) {
            T[] res = (T[]) Array.newInstance(data.getClass().getComponentType(), 1);
            res[0] = data[right];
            return res;
        }
        int middle = left + (right - left) / 2;
        T[] l = mergeSort(data, left, middle);
        T[] r = mergeSort(data, middle + 1, right);
        return merge(l, r);
    }

    @SuppressWarnings("unchecked")
    private T[] merge(T[] a, T[] b) {
        T[] result = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
        int i = 0;
        int j = 0;
        int pointer = 0;
        while (i < a.length && j < b.length) {
            if (greater(a[i], b[j])) {
                result[pointer++] = b[j++];
            } else {
                result[pointer++] = a[i++];
            }
        }
        while (i < a.length) {
            result[pointer++] = a[i++];
        }
        while (j < b.length) {
            result[pointer++] = b[j++];
        }

        return result;
    }
}
