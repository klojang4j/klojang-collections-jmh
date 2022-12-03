package org.klojang.collections;

import org.klojang.util.ClassMethods;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 30, jvmArgs = {"-Xms2G", "-Xmx2G", "-XX:-StackTraceInThrowable"})
@Warmup(iterations = 4, time = 3500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
public class TypeMap_Base_Types_Only_90_Pct_Hits {

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
    keysInMap = getKeysInMap(classes);
    Collections.shuffle(classes);
    Collections.shuffle(classes);
    requestedKeys = getRequestedKeys(classes);
  }

  private List<Class> getKeysInMap(List<Class> classes) {
    List<Class> keys = new ArrayList<>(100);
    for (Class c : classes) {
      if (!c.isInterface()) {
        if (ClassMethods.countAncestors(c) <= 1) {
          keys.add(c);
        } else if (ClassMethods.getAllInterfaces(c).size() == 0) {
          keys.add(c);
        }
      }
    }
    return keys.subList(0, 1 + ((int) (keys.size() * .9)));
  }

  public List<Class> getRequestedKeys(List<Class> classes) {
    List<Class> keys = new ArrayList<>(100);
    for (Class c : classes) {
      if (!c.isInterface()) {
        if (ClassMethods.countAncestors(c) > 1) {
          keys.add(c);
        }
      }
    }
    return keys;
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

}
