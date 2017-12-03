package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class QSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {
    private static final int THRESHOLD = 30;

    public QSort() {
    }

    public QSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private int partition(T[] data, int left, int right) {
        T pivot = data[ThreadLocalRandom.current().nextInt(left, right + 1)];
        int i = left, j = right;
        while (i <= j) {
            while (lesser(data[i], pivot)) i++;
            while (greater(data[j], pivot)) j--;
            if (i <= j) swap(data, i++, j--);
        }
        return j;
    }

     private void quickSort(T[] data, int left, int right) {
        if (left >= right) return;

        if (right - left < THRESHOLD) {
            insertionSort(data, left, right);
            return;
        }

        int idx = partition(data, left, right);
        quickSort(data, left, idx);
        quickSort(data,idx + 1, right);
    }

    private void insertionSort(T[] data, int left, int right) {
        for (int i = left; i <= right; i++) {
            T curr = data[i];
            int j;
            for (j = i; j > 0 && greater(data[j - 1], curr); j--) {
                data[j] = data[j - 1];
            }
            data[j] = curr;
        }
    }


    public static void main(String[] args) {
        System.out.println(QSort.class.getSimpleName());
        Integer[] arr = SortUtils.generateBoxedArray(60);
        System.out.println(Arrays.toString(arr));
        new QSort<Integer>().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
