package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.HeapSort;

public class HeapSortBench extends AbstractBench {

    @Setup(value = Level.Trial)
    public void setupTrial() {
        setup(new HeapSort<>(), new HeapSort<>());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HeapSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

/*
Benchmark                      Mode  Cnt    Score    Error  Units
HeapSortBench.heap             avgt    5  244.815 ±  4.095  us/op
HeapSortBench.longStringArray  avgt    5  405.046 ±  8.117  us/op
HeapSortBench.narrowRange      avgt    5  219.550 ±  4.630  us/op
HeapSortBench.randomArray      avgt    5  258.016 ±  8.976  us/op
HeapSortBench.repetitiveArray  avgt    5  258.581 ± 10.786  us/op
HeapSortBench.reversedArray    avgt    5  248.968 ±  9.215  us/op
HeapSortBench.reversedHeap     avgt    5  247.729 ±  8.324  us/op
HeapSortBench.sortedArray      avgt    5  252.546 ± 20.227  us/op
HeapSortBench.stringArray      avgt    5  389.579 ±  7.839  us/op
HeapSortBench.uniqueArray      avgt    5  251.181 ±  5.689  us/op
*/