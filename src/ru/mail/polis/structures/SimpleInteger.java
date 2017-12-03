package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleInteger implements Numerical<SimpleInteger> {

    private static final int DIGIT_COUNT = 20;

    private final int data;
    private final int length;

    public SimpleInteger(Integer data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Source must be not null");
        }
        this.data = data;
        int l = String.valueOf(data).length();
        this.length = data >= 0 ? l : l - 1;
    }

    @Override
    public int getDigit(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index " + index);
        } else {
            return data % (int) Math.pow(10, index + 1) / (int) Math.pow(10, index) + 10;
        }
    }

    @Override
    public int getDigitMaxValue() {
        return DIGIT_COUNT;
    }

    @Override
    public int getDigitCount() {
        return length;
    }

    @Override
    public int compareTo(SimpleInteger anotherSimpleInteger) {
        return Integer.compare(data, anotherSimpleInteger.data);
    }

    @Override
    public String toString() {
        return "SimpleInteger ( " + data + " )";
    }
}
