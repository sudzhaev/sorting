package ru.mail.polis.sort;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class QSort3Way<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    public QSort3Way() {
    }

    public QSort3Way(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(T[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private void quickSort(T[] data, int left, int right) {
        if (left >= right) return;

        swap(data, left, ThreadLocalRandom.current().nextInt(left, right + 1));
        T pivot = data[left];
        int lt = left;
        int gt = right;
        int i = left + 1;

        while (i <= gt) {
            if (lesser(data[i], pivot)) {
                swap(data, i++, lt++);
            } else if (greater(data[i], pivot)) {
                swap(data, i, gt--);
            } else {
                i++;
            }
        }

        quickSort(data, left, lt - 1);
        quickSort(data, gt + 1, right);
    }
}