package ru.mail.polis.bench;

import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.*;
import java.util.stream.Collectors;

public class BenchmarkUtils {
    private static Random random = new Random();

    private BenchmarkUtils() {
    }
    
    private static int randomInt() {
        return random.nextInt(200000) - 100000;
    }

    public static Integer[] generateRandomArray(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = randomInt();
        }
        return result;
    }

    public static Integer[] generateUniqueArray(int size) {
        Set<Integer> set = new HashSet<>();
        while (set.size() < size) {
            set.add(randomInt());
        }
        return set.toArray(new Integer[0]);
    }

    public static Integer[] generateRepetitiveArray(int size) {
        final int uniqueNumber = (int) (size * 0.3);
        Integer[] unique = generateUniqueArray(uniqueNumber);
        Integer[] result = new Integer[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = unique[random.nextInt(unique.length)];
        }
        return result;
    }

    public static String[] generateStringArray(int size) {
        String[] result = new String[size];
        final int maxLength = 12;
        for (int i = 0; i < size; i++) {
            int length = random.nextInt(maxLength) + 1;
            result[i] = randomString(length);
        }
        return result;
    }

    private static String randomString(int length) {
        StringBuilder builder = new StringBuilder();
        final int a = 97;
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + a);
            builder.append(c);
        }
        return builder.toString();
    }

    public static SimpleInteger[] wrapSimpleIntegerArray(Integer[] data) {
        List<SimpleInteger> wrapped = Arrays.stream(data)
                .map(SimpleInteger::new)
                .collect(Collectors.toList());
        return wrapped.toArray(new SimpleInteger[0]);
    }

    public static SimpleString[] wrapSimpleStringArray(String[] data) {
        List<SimpleString> wrapped = Arrays.stream(data)
                .map(SimpleString::new)
                .collect(Collectors.toList());
        return wrapped.toArray(new SimpleString[0]);
    }

    public static Integer[] generateSortedArray(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = i;
        }
        return result;
    }

    public static Integer[] generateReversedArray(int size) {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = size - i;
        }
        return result;
    }

    public static Integer[] generateReversedHeap(int size) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < size; i++) {
            queue.add(randomInt());
        }
        return queue.toArray(new Integer[0]);
    }

    public static Integer[] generateHeap(int size) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            queue.add(randomInt());
        }
        return queue.toArray(new Integer[0]);
    }

    public static Integer[] generateNarrowRangeArray(int size) {
        final int range = 12;
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = random.nextInt(range);
        }
        return result;
    }

    public static String[] generateLongStringArray(int size) {
        String[] result = new String[size];
        final int length = 2500;
        for (int i = 0; i < size; i++) {
            result[i] = randomString(length);
        }
        return result;
    }

    public static IntKeyStringValueObject[] wrapIntKeyStringValueObjectArray(Integer[] data) {
        IntKeyStringValueObject[] result = new IntKeyStringValueObject[data.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new IntKeyStringValueObject(data[i], randomString(5));
        }
        return result;
    }

    public static void main(String[] args) {
        CountingSort<IntKeyStringValueObject> sort = new CountingSort<>();
        IntKeyStringValueObject[] array = wrapIntKeyStringValueObjectArray(
                generateRandomArray(1000)
        );
        System.out.println(Arrays.toString(array));
        sort.sort(array);
        System.out.println(Arrays.toString(array));

    }
}
