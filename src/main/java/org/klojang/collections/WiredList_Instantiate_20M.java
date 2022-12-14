package org.klojang.collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 4, jvmArgs = {"-Xmn16G"})
@Warmup(iterations = 4, time = 3500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 3500, timeUnit = TimeUnit.MILLISECONDS)
public class WiredList_Instantiate_20M {

  public static int LIST_SIZE = 20_000_000;

  public Integer[] ints1 = new Integer[LIST_SIZE];

  public ArrayList<Integer> arrayList1;
  public LinkedList<Integer> linkedList1;
  public WiredList<Integer> wiredList1;
  public CrisprList<Integer> crisprList1;

  @Benchmark
  public void arrayList(Blackhole bh) {
    arrayList1 = new ArrayList<>(Arrays.asList(ints1));
    bh.consume(arrayList1);
  }

  @Benchmark
  public void linkedList(Blackhole bh) {
    linkedList1 = new LinkedList<>(Arrays.asList(ints1));
    bh.consume(linkedList1);
  }

  @Benchmark
  public void wiredList(Blackhole bh) {
    wiredList1 = WiredList.ofElements(ints1);
    bh.consume(wiredList1);
  }

  @Benchmark
  public void crisprList(Blackhole bh) {
    crisprList1 = CrisprList.ofElements(ints1);
    bh.consume(crisprList1);
  }

  public Random rand;

  @Setup(Level.Trial)
  public void bootstrap() {
    rand = new Random();
    for (int i = 0; i < LIST_SIZE; ++i) {
      ints1[i] = rand.nextInt();
    }
  }

  @Setup(Level.Invocation)
  public void prepare() {
    for (int i = 0; i < 4; ++i) {
      int idx = rand.nextInt(0, LIST_SIZE);
      int val = rand.nextInt();
      ints1[idx] = val;
    }
  }

}
