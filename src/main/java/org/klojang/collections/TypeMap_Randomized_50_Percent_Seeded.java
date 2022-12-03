package org.klojang.collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 100, jvmArgs = {"-Xms2G", "-Xmx2G", "-XX:-StackTraceInThrowable"})
@Warmup(iterations = 5, time = 3500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)

public class TypeMap_Randomized_50_Percent_Seeded {

  public List<Class> classes = new ArrayList();
  public TypeMap fixed;
  public TypeMap tree;
  public TypeMap nativ;
  public TypeMap greedy;

  public List<Class> keysInMap;
  public List<Class> requestedKeys;

  public int counter;

  @Benchmark
  public void fixedTypeMap(Blackhole bh) {
    Object val = fixed.get(requestedKeys.get(counter));
    bh.consume(val);
  }

  @Benchmark
  public void greedyTypeMap(Blackhole bh) {
    Object val = greedy.get(requestedKeys.get(counter));
    bh.consume(val);
  }

  @Benchmark
  public void treeTypeMap(Blackhole bh) {
    Object val = fixed.get(requestedKeys.get(counter));
    bh.consume(val);
  }

  @Benchmark
  public void nativeTypeMap(Blackhole bh) {
    Object val = nativ.get(requestedKeys.get(counter));
    bh.consume(val);
  }

  @Setup(Level.Trial)
  public void bootstrap() {
    Class[] members = TypeHierarchy.class.getClasses();
    classes.addAll(Arrays.asList(members));
    Collections.shuffle(classes);
    Collections.shuffle(classes);
    keysInMap = new ArrayList<>(100);
    for (int i = 0; i < 1 + (classes.size() * .5); i++) {
      keysInMap.add(classes.get(i));
    }
    Collections.shuffle(classes);
    Collections.shuffle(classes);
    requestedKeys = new ArrayList<>(100);
    for (int i = 0; i < 1 + (classes.size() * .5); i++) {
      requestedKeys.add(classes.get(i));
    }
  }

  @Setup(Level.Invocation)
  public void beforeIteration() {
    Collections.shuffle(keysInMap);
    Map<Class<?>, Object> src = new HashMap<>();
    for (int i = 0; i < keysInMap.size(); ++i) {
      src.put(keysInMap.get(i), new Object());
    }
    fixed = TypeMap.fixedTypeMap(src);
    tree = TypeMap.treeTypeMap(src);
    greedy = TypeMap.greedyTypeMap(src);
    nativ = TypeMap.nativeTypeMap(src);
    Collections.shuffle(requestedKeys);
  }

  @Setup(Level.Invocation)
  public void prepare() {
    if (++counter >= requestedKeys.size()) {
      counter = 0;
    }
  }

  public static void main(String[] args) {
    TypeMap_Randomized_10_Percent_Seeded x = new TypeMap_Randomized_10_Percent_Seeded();
    x.bootstrap();
    x.beforeIteration();
  }

}
