package org.klojang.collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 4, jvmArgs = {"-Xms4G", "-Xmx4G", "-XX:-StackTraceInThrowable"})
@Warmup(iterations = 4, time = 3500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
public class WiredList_CutStart {

  public static int LIST_SIZE = 100_000;
  private static int SEGMENT_SIZE = 10_000;

  public Integer[] ints = new Integer[LIST_SIZE];

  public ArrayList<Integer> arrayList;
  public WiredList<Integer> wiredList;
  public CrisprList<Integer> crisprList;

  public int from;
  public int to;

  @Benchmark
  public void arrayList(Blackhole bh) {
    List<Integer> l0 = arrayList.subList(0, from);
    List<Integer> l1 = arrayList.subList(to, arrayList.size());
    List<Integer> l2 = new ArrayList<>(arrayList.size() - to + from);
    l2.addAll(l0);
    l2.addAll(l1);
    bh.consume(l2);
  }

  @Benchmark
  public void wiredList(Blackhole bh) {
    wiredList.cut(from, to);
    bh.consume(wiredList);
  }

  @Benchmark
  public void crisprList(Blackhole bh) {
    crisprList.cut(from, to);
    bh.consume(crisprList);
  }

  public Random rand;

  @Setup(Level.Trial)
  public void bootstrap() {
    rand = new Random();
    for (int i = 0; i < LIST_SIZE; ++i) {
      ints[i] = rand.nextInt();
    }
  }

  @Setup(Level.Invocation)
  public void prepare() {
    for (int i = 0; i < 5; ++i) {
      int idx = rand.nextInt(0, LIST_SIZE);
      int val = rand.nextInt();
      ints[idx] = val;
    }
    arrayList = new ArrayList<>(Arrays.asList(ints));
    wiredList = WiredList.ofElements(ints);
    crisprList = CrisprList.ofElements(ints);
    int x = rand.nextInt(0, 3);
    from = x;
    to = from + SEGMENT_SIZE;
  }

}
