package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import ru.mail.polis.sort.AbstractSortOnComparisons;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class AbstractBench {
    private Integer[][] intData;
    private String[][] strData;
    private AbstractSortOnComparisons<Integer> intSortImpl;
    private AbstractSortOnComparisons<String> strSortImpl;
    private final int SIZE = 1000;

    protected void setup(AbstractSortOnComparisons<Integer> intSortImpl,
                         AbstractSortOnComparisons<String> strSortImpl) {
        this.intSortImpl = intSortImpl;
        this.strSortImpl = strSortImpl;
        intData = new Integer[8][];
        intData[0] = BenchmarkUtils.generateRandomArray(SIZE);
        intData[1] = BenchmarkUtils.generateUniqueArray(SIZE);
        intData[2] = BenchmarkUtils.generateRepetitiveArray(SIZE);
        intData[3] = BenchmarkUtils.generateSortedArray(SIZE);
        intData[4] = BenchmarkUtils.generateReversedArray(SIZE);
        intData[5] = BenchmarkUtils.generateHeap(SIZE);
        intData[6] = BenchmarkUtils.generateReversedHeap(SIZE);
        intData[7] = BenchmarkUtils.generateNarrowRangeArray(SIZE);
        strData = new String[2][];
        strData[0] = BenchmarkUtils.generateStringArray(SIZE);
        strData[1] = BenchmarkUtils.generateLongStringArray(SIZE);
    }

    @Benchmark
    public void randomArray(Blackhole bh) {
        intSortImpl.sort(intData[0]);
        bh.consume(intData[0]);
    }

    @Benchmark
    public void uniqueArray(Blackhole bh) {
        intSortImpl.sort(intData[1]);
        bh.consume(intData[1]);
    }

    @Benchmark
    public void repetitiveArray(Blackhole bh) {
        intSortImpl.sort(intData[2]);
        bh.consume(intData[2]);
    }

    @Benchmark
    public void sortedArray(Blackhole bh) {
        intSortImpl.sort(intData[3]);
        bh.consume(intData[3]);
    }

    @Benchmark
    public void reversedArray(Blackhole bh) {
        intSortImpl.sort(intData[4]);
        bh.consume(intData[4]);
    }

    @Benchmark
    public void heap(Blackhole bh) {
        intSortImpl.sort(intData[5]);
        bh.consume(intData[5]);
    }

    @Benchmark
    public void reversedHeap(Blackhole bh) {
        intSortImpl.sort(intData[6]);
        bh.consume(intData[6]);
    }

    @Benchmark
    public void narrowRange(Blackhole bh) {
        intSortImpl.sort(intData[7]);
        bh.consume(intData[7]);
    }

    @Benchmark
    public void stringArray(Blackhole bh) {
        strSortImpl.sort(strData[0]);
        bh.consume(strData[0]);
    }

    @Benchmark
    public void longStringArray(Blackhole bh) {
        strSortImpl.sort(strData[1]);
        bh.consume(strData[1]);
    }
}
