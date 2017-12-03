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
