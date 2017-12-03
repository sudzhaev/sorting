package ru.mail.polis.sort;
import ru.mail.polis.bench.BenchmarkUtils;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    public HeapSort() {
    }

    public HeapSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] data) {
        buildHeap(data);

        int heapSize = data.length;
        for (int i = 0; i < data.length - 1; i++) {
            swap(data, 0, data.length - 1 - i);
            heapSize -= 1;
            siftDown(data, 0, heapSize);
        }
    }

    private void buildHeap(T[] data) {
        for (int i = data.length / 2; i >= 0; i--) {
            siftDown(data, i, data.length);
        }
    }

    private void siftDown(T[] data, int top, int size) {
        int i = top;
        while (true) {
            if (i * 2 + 2 < size) {
                int indexToSwap = lesser(data[i * 2 + 1], data[i * 2 + 2]) ? (i * 2 + 2) : (i * 2 + 1);
                if (lesser(data[i], data[indexToSwap])) {
                    swap(data, i, indexToSwap);
                    i = indexToSwap;
                    continue;
                }
            }
            if (i * 2 + 1 < size && lesser(data[i], data[i * 2 + 1])) {
                swap(data, i, i * 2 + 1);
                i *= 2;
                continue;
            }
            break;
        }
    }

    public static void main(String[] args) {
        System.out.println(HeapSort.class.getSimpleName());
        Integer[] arr = BenchmarkUtils.generateReversedHeap(20);
        System.out.println(Arrays.toString(arr));
        new HeapSort<Integer>().sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(SortUtils.isArraySorted(arr));
    }
}
