package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.MergeSort;

public class MergeSortBench extends AbstractBench {

    @Setup(value = Level.Trial)
    public void setupTrial() {
        setup(new MergeSort<>(), new MergeSort<>());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MergeSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

/*
Benchmark                       Mode  Cnt    Score    Error  Units
MergeSortBench.heap             avgt    5  135.130 ±  8.789  us/op
MergeSortBench.longStringArray  avgt    5  194.139 ± 13.540  us/op
MergeSortBench.narrowRange      avgt    5  136.594 ±  1.615  us/op
MergeSortBench.randomArray      avgt    5  134.764 ±  3.394  us/op
MergeSortBench.repetitiveArray  avgt    5  138.392 ±  2.003  us/op
MergeSortBench.reversedArray    avgt    5  132.773 ±  1.710  us/op
MergeSortBench.reversedHeap     avgt    5  133.884 ±  4.456  us/op
MergeSortBench.sortedArray      avgt    5  126.086 ±  2.719  us/op
MergeSortBench.stringArray      avgt    5  178.483 ±  8.733  us/op
MergeSortBench.uniqueArray      avgt    5  135.219 ±  7.174  us/op
*/