package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.concurrent.TimeUnit;

import static ru.mail.polis.bench.BenchmarkUtils.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class LSDSortBench {
    private LSDSort<SimpleInteger> intSort;
    private LSDSort<SimpleString> strSort;
    private SimpleInteger[][] intData;
    private SimpleString[][] strData;
    private final int SIZE = 1000;
    
    @Setup(value = Level.Trial)
    public void setupTrial() {
        intSort = new LSDSort<>();
        strSort = new LSDSort<>();
        intData = new SimpleInteger[8][];
        intData[0] = wrapSimpleIntegerArray(generateRandomArray(SIZE));
        intData[1] = wrapSimpleIntegerArray(generateUniqueArray(SIZE));
        intData[2] = wrapSimpleIntegerArray(generateRepetitiveArray(SIZE));
        intData[3] = wrapSimpleIntegerArray(generateSortedArray(SIZE));
        intData[4] = wrapSimpleIntegerArray(generateReversedArray(SIZE));
        intData[5] = wrapSimpleIntegerArray(generateHeap(SIZE));
        intData[6] = wrapSimpleIntegerArray(generateReversedHeap(SIZE));
        intData[7] = wrapSimpleIntegerArray(generateNarrowRangeArray(SIZE));
        strData = new SimpleString[2][];
        strData[0] = wrapSimpleStringArray(generateStringArray(SIZE));
        strData[1] = wrapSimpleStringArray(generateLongStringArray(SIZE));
    }

    @Benchmark
    public void randomArray(Blackhole bh) {
        intSort.sort(intData[0]);
        bh.consume(intData[0]);
    }

    @Benchmark
    public void uniqueArray(Blackhole bh) {
        intSort.sort(intData[1]);
        bh.consume(intData[1]);
    }

    @Benchmark
    public void repetitiveArray(Blackhole bh) {
        intSort.sort(intData[2]);
        bh.consume(intData[2]);
    }

    @Benchmark
    public void sortedArray(Blackhole bh) {
        intSort.sort(intData[3]);
        bh.consume(intData[3]);
    }

    @Benchmark
    public void reversedArray(Blackhole bh) {
        intSort.sort(intData[4]);
        bh.consume(intData[4]);
    }

    @Benchmark
    public void heap(Blackhole bh) {
        intSort.sort(intData[5]);
        bh.consume(intData[5]);
    }

    @Benchmark
    public void reversedHeap(Blackhole bh) {
        intSort.sort(intData[6]);
        bh.consume(intData[6]);
    }

    @Benchmark
    public void narrowRange(Blackhole bh) {
        intSort.sort(intData[7]);
        bh.consume(intData[7]);
    }

    @Benchmark
    public void stringArray(Blackhole bh) {
        strSort.sort(strData[0]);
        bh.consume(strData[0]);
    }

    @Benchmark
    public void longStringArray(Blackhole bh) {
        strSort.sort(strData[1]);
        bh.consume(strData[1]);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LSDSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
