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
