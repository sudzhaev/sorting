package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.QSort;

public class QSortBench extends AbstractBench {

    @Setup(value = Level.Trial)
    public void setupTrial() {
        setup(new QSort<>(), new QSort<>());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

/*
Benchmark                   Mode  Cnt    Score   Error  Units
QSortBench.heap             avgt    5   42.486 ± 0.761  us/op
QSortBench.longStringArray  avgt    5  121.050 ± 2.896  us/op
QSortBench.narrowRange      avgt    5   52.495 ± 0.736  us/op
QSortBench.randomArray      avgt    5   41.904 ± 0.187  us/op
QSortBench.repetitiveArray  avgt    5   45.688 ± 0.330  us/op
QSortBench.reversedArray    avgt    5   35.187 ± 4.530  us/op
QSortBench.reversedHeap     avgt    5   42.127 ± 0.356  us/op
QSortBench.sortedArray      avgt    5   35.302 ± 0.850  us/op
QSortBench.stringArray      avgt    5  117.939 ± 1.790  us/op
QSortBench.uniqueArray      avgt    5   41.940 ± 0.594  us/op
 */