package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;


public class CountingSort<T extends IntKeyObject> implements Sort<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(T[] data) {
        int max = getMax(data);
        int min = getMin(data);
        int shift = min < 0 ? Math.abs(min) : 0;
        int[] count = new int[max + shift + 1];
        for (T x : data) {
            count[x.getKey() + shift]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        T[] result = (T[]) Array.newInstance(data.getClass().getComponentType(), data.length);
        for (int i = data.length - 1; i >= 0; i--) {
            result[--count[data[i].getKey() + shift]] = data[i];
        }
        System.arraycopy(result, 0, data, 0, data.length);
    }

    private int getMax(T[] a) {
        return Arrays.stream(a)
                .max(Comparator.comparingInt(IntKeyObject::getKey))
                .map(IntKeyObject::getKey)
                .orElse(0);
    }

    private int getMin(T[] a) {
        return Arrays.stream(a)
                .min(Comparator.comparingInt(IntKeyObject::getKey))
                .map(IntKeyObject::getKey)
                .orElse(0);
    }
}
