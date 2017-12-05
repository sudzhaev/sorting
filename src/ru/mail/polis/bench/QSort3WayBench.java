package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.QSort3Way;

public class QSort3WayBench extends AbstractBench {

    @Setup(value = Level.Trial)
    public void setupTrial() {
        setup(new QSort3Way<>(), new QSort3Way<>());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QSort3WayBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

/*
Benchmark                       Mode  Cnt    Score    Error  Units
QSort3WayBench.heap             avgt    5  228.629 ±  7.199  us/op
QSort3WayBench.longStringArray  avgt    5  362.511 ±  5.762  us/op
QSort3WayBench.narrowRange      avgt    5   48.623 ±  0.670  us/op
QSort3WayBench.randomArray      avgt    5  228.053 ±  1.323  us/op
QSort3WayBench.repetitiveArray  avgt    5  176.813 ± 19.229  us/op
QSort3WayBench.reversedArray    avgt    5  186.262 ±  4.692  us/op
QSort3WayBench.reversedHeap     avgt    5  229.406 ± 11.706  us/op
QSort3WayBench.sortedArray      avgt    5  188.435 ±  1.018  us/op
QSort3WayBench.stringArray      avgt    5  391.739 ±  8.911  us/op
QSort3WayBench.uniqueArray      avgt    5  190.602 ±  1.910  us/op
 */