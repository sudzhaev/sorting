package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.structures.IntKeyStringValueObject;

import java.util.concurrent.TimeUnit;

import static ru.mail.polis.bench.BenchmarkUtils.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class CountingSortBench {
    private CountingSort<IntKeyStringValueObject> sort;
    private IntKeyStringValueObject[][] data;
    private final int SIZE = 1000;

    @Setup(value = Level.Trial)
    public void setupTrial() {
        sort = new CountingSort<>();
        data = new IntKeyStringValueObject[8][];
        data[0] = wrapIntKeyStringValueObjectArray(generateRandomArray(SIZE));
        data[1] = wrapIntKeyStringValueObjectArray(generateUniqueArray(SIZE));
        data[2] = wrapIntKeyStringValueObjectArray(generateRepetitiveArray(SIZE));
        data[3] = wrapIntKeyStringValueObjectArray(generateSortedArray(SIZE));
        data[4] = wrapIntKeyStringValueObjectArray(generateReversedArray(SIZE));
        data[5] = wrapIntKeyStringValueObjectArray(generateHeap(SIZE));
        data[6] = wrapIntKeyStringValueObjectArray(generateReversedHeap(SIZE));
        data[7] = wrapIntKeyStringValueObjectArray(generateNarrowRangeArray(SIZE));
    }

    @Benchmark
    public void randomArray(Blackhole bh) {
        sort.sort(data[0]);
        bh.consume(data[0]);
    }

    @Benchmark
    public void uniqueArray(Blackhole bh) {
        sort.sort(data[1]);
        bh.consume(data[1]);
    }

    @Benchmark
    public void repetitiveArray(Blackhole bh) {
        sort.sort(data[2]);
        bh.consume(data[2]);
    }

    @Benchmark
    public void sortedArray(Blackhole bh) {
        sort.sort(data[3]);
        bh.consume(data[3]);
    }

    @Benchmark
    public void reversedArray(Blackhole bh) {
        sort.sort(data[4]);
        bh.consume(data[4]);
    }

    @Benchmark
    public void heap(Blackhole bh) {
        sort.sort(data[5]);
        bh.consume(data[5]);
    }

    @Benchmark
    public void reversedHeap(Blackhole bh) {
        sort.sort(data[6]);
        bh.consume(data[6]);
    }

    @Benchmark
    public void narrowRange(Blackhole bh) {
        sort.sort(data[7]);
        bh.consume(data[7]);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CountingSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

/*
Benchmark                          Mode  Cnt    Score     Error  Units
CountingSortBench.heap             avgt    5  490.466 ± 127.031  us/op
CountingSortBench.narrowRange      avgt    5   31.601 ±   0.425  us/op
CountingSortBench.randomArray      avgt    5  468.207 ±  51.754  us/op
CountingSortBench.repetitiveArray  avgt    5  476.514 ±  26.303  us/op
CountingSortBench.reversedArray    avgt    5   28.276 ±   0.815  us/op
CountingSortBench.reversedHeap     avgt    5  483.414 ±  69.839  us/op
CountingSortBench.sortedArray      avgt    5   30.892 ±   1.700  us/op
CountingSortBench.uniqueArray      avgt    5  471.880 ±  34.700  us/op
*/
