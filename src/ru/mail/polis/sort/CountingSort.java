package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


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

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<IntKeyObject<String>> list = Arrays.asList(
                new IntKeyStringValueObject(-1, "abc"),
                new IntKeyStringValueObject(-3, "jkl"),
                new IntKeyStringValueObject(-2, "bcd"),
                new IntKeyStringValueObject(-8, "xxz"),
                new IntKeyStringValueObject(-1, "xyz"),
                new IntKeyStringValueObject(-7, "xyz"));
        IntKeyObject<String>[] array = (IntKeyObject<String>[]) list.toArray();
        System.out.println(Arrays.toString(array));
        new CountingSort<IntKeyObject<String>>().sort(array);
        System.out.println(Arrays.toString(array));
    }
}
