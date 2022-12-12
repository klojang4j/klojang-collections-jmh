package org.klojang.collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 4, jvmArgs = {"-Xmn4G"})
@Warmup(iterations = 4, time = 3500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
public class WiredList_10M_replace_1M_Start {

  public static int LIST_SIZE = 10_000_000;
  private static int SEGMENT_SIZE = 1_000_000;

  public Integer[] ints1 = new Integer[LIST_SIZE];
  public Integer[] ints2 = new Integer[SEGMENT_SIZE];

  public ArrayList<Integer> arrayList1;
  public LinkedList<Integer> linkedList1;
  public WiredList<Integer> wiredList1;
  public CrisprList<Integer> crisprList1;
  public ArrayList<Integer> arrayList2;
  public LinkedList<Integer> linkedList2;
  public WiredList<Integer> wiredList2;
  public CrisprList<Integer> crisprList2;

  public int from;
  public int to;

  @Benchmark
  public void arrayList(Blackhole bh) {
    var l1 = arrayList1.subList(0, from);
    var l2 = arrayList1.subList(to, arrayList1.size());
    List<Integer> l3 = new ArrayList<>(l1.size() + l2.size() + arrayList2.size());
    l3.addAll(l1);
    l3.addAll(arrayList2);
    l3.addAll(l2);
    bh.consume(l3);
  }

  @Benchmark
  public void linkedList(Blackhole bh) {
    var l1 = linkedList1.subList(0, from);
    var l2 = linkedList1.subList(to, linkedList1.size());
    List<Integer> l3 = new LinkedList<>();
    l3.addAll(l1);
    l3.addAll(linkedList2);
    l3.addAll(l2);
    bh.consume(l3);
  }

  @Benchmark
  public void wiredList(Blackhole bh) {
    wiredList1.replaceAll(from, to, wiredList2);
    bh.consume(wiredList1);
  }

  @Benchmark
  public void crisprList(Blackhole bh) {
    crisprList1.replaceAll(from, to, crisprList2);
    bh.consume(crisprList1);
  }

  public Random rand;

  @Setup(Level.Trial)
  public void bootstrap() {
    rand = new Random();
    for (int i = 0; i < LIST_SIZE; ++i) {
      ints1[i] = rand.nextInt();
    }
    for (int i = 0; i < SEGMENT_SIZE; ++i) {
      ints2[i] = rand.nextInt();
    }
  }

  @Setup(Level.Invocation)
  public void prepare() {
    for (int i = 0; i < 4; ++i) {
      int idx = rand.nextInt(0, LIST_SIZE);
      int val = rand.nextInt();
      ints1[idx] = val;
    }
    for (int i = 0; i < 4; ++i) {
      int idx = rand.nextInt(0, SEGMENT_SIZE);
      int val = rand.nextInt();
      ints2[idx] = val;
    }

    arrayList1 = new ArrayList<>(Arrays.asList(ints1));
    linkedList1 = new LinkedList<>(Arrays.asList(ints1));
    wiredList1 = WiredList.ofElements(ints1);
    crisprList1 = CrisprList.ofElements(ints1);

    arrayList2 = new ArrayList<>(Arrays.asList(ints2));
    linkedList2 = new LinkedList<>(Arrays.asList(ints2));
    wiredList2 = WiredList.ofElements(ints2);
    crisprList2 = CrisprList.ofElements(ints2);

    from = rand.nextInt(1, 5);
    to = from + SEGMENT_SIZE + rand.nextInt(1, 4);
  }

}
