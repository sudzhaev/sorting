package ru.mail.polis.sort;

import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class LSDSort<T extends Numerical> extends AbstractSortOnComparisons<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(T[] data) {
        final int r = data[0].getDigitMaxValue() + 1;
        int d = findMaxDigit(data);
        for (int k = 0; k < d; k++) {
            int[] count = new int[r];
            for (T x : data)
                count[x.getDigit(k)]++;
            for (int i = 1; i < r; i++) {
                count[i] += count[i - 1];
            }
            T[] result = (T[]) Array.newInstance(data.getClass().getComponentType(), data.length);
            for (int i = data.length - 1; i >= 0; i--) {
                result[--count[data[i].getDigit(k)]] = data[i];
            }
            System.arraycopy(result, 0, data, 0, result.length);
        }
    }

    private int findMaxDigit(T[] a) {
        return Arrays.stream(a)
                .max(Comparator.comparingInt(Numerical::getDigitCount))
                .map(Numerical::getDigitCount)
                .orElse(0);
    }

    public static void main(String[] args) {
        List<SimpleString> list = Arrays.asList(
                new SimpleString("helloz"),
                new SimpleString("longstring"),
                new SimpleString("world")
        );
        SimpleString[] array = (SimpleString[]) list.toArray();
        System.out.println(Arrays.toString(array));
        new LSDSort<SimpleString>().sort(array);
        System.out.println(Arrays.toString(array));

        List<SimpleInteger> integers = Arrays.asList(
                new SimpleInteger(-110),
                new SimpleInteger(176),
                new SimpleInteger(-154),
                new SimpleInteger(87),
                new SimpleInteger(-87),
                new SimpleInteger(99),
                new SimpleInteger(4)
        );
        SimpleInteger[] integers1 = (SimpleInteger[]) integers.toArray();
        System.out.println(Arrays.toString(integers1));
        new LSDSort<SimpleInteger>().sort(integers1);
        System.out.println(Arrays.toString(integers1));
    }
}
