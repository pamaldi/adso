package cloud.isaura.adso.bench;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(2)
public class TokenizerBenchmark
{


    @Setup
    public void setup() {



    }

    @Benchmark
    @BenchmarkMode({ Mode.AverageTime})
    public void benchmarkMono() throws IOException
    {
       TokenizerBench tokenizerBench = new TokenizerBench();
       tokenizerBench.benchMono();

    }

    @Benchmark
    @BenchmarkMode({ Mode.AverageTime})
    public void benchMulti() throws IOException
    {
        TokenizerBench tokenizerBench = new TokenizerBench();
        tokenizerBench.benchMulti();

    }

    @Benchmark
    @BenchmarkMode({ Mode.AverageTime})
    public void benchMultiVT() throws IOException
    {
        TokenizerBench tokenizerBench = new TokenizerBench();
        tokenizerBench.benchMultiVT();

    }

    public static void main(String[] args) throws  RunnerException
    {
        var opt = new OptionsBuilder()
                .include(TokenizerBenchmark.class.getSimpleName())
                .detectJvmArgs()
                .build();
        new Runner(opt).run();
    }
}
