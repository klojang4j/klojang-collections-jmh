package org.klojang.collections;

public class TypeHierarchy {
  
  //@formatter:off
  public static  interface Interface_0 {}
  public static  interface Interface_1 {}
  public static  interface Interface_2 {}
  public static  interface Interface_3 {}
  public static  interface Interface_4 {}
  public static  interface Interface_5 {}

  public static  interface Interface_1_0 extends Interface_1 {}
  public static  interface Interface_2_0 extends Interface_2 {}
  public static  interface Interface_2_1 extends Interface_2 {}
  public static  interface Interface_3_0 extends Interface_3 {}
  public static  interface Interface_3_1 extends Interface_3 {}
  public static  interface Interface_3_2 extends Interface_3 {}

  public static  interface Interface_2_0_0 extends Interface_2_0 {}
  public static  interface Interface_2_0_1 extends Interface_2_0 {}
  public static  interface Interface_2_1_0 extends Interface_2_1 {}
  public static  interface Interface_2_1_1 extends Interface_2_1 {}

  public static  interface Interface_3_0_0 extends Interface_3_0 {}
  public static  interface Interface_3_0_1 extends Interface_3_0 {}
  public static  interface Interface_3_0_2 extends Interface_3_0 {}
  public static  interface Interface_3_1_0 extends Interface_3_1 {}
  public static  interface Interface_3_1_1 extends Interface_3_1 {}
  public static  interface Interface_3_1_2 extends Interface_3_1 {}

  public static  interface Interface_3_0_0_0 extends Interface_3_0_0 {}
  public static  interface Interface_3_0_0_1 extends Interface_3_0_0 {}
  public static  interface Interface_3_0_0_2 extends Interface_3_0_0 {}
  public static  interface Interface_3_0_0_3 extends Interface_3_0_0 {}

  public static  class Class_0 {}
  public static  class Class_1 {}
  public static  class Class_2 {}
  public static  class Class_3 implements Interface_3 {}
  public static  class Class_4 implements Interface_2, Interface_3 {}

  public static  class Class_0_0 extends Class_0 {}
  public static  class Class_0_1 extends Class_0 {}
  public static  class Class_0_2 extends Class_0 implements Interface_3 {}
  public static  class Class_0_3 extends Class_0 implements Interface_3_1 {}
  public static  class Class_0_4 extends Class_0 implements Interface_5 {}

  public static  class Class_1_0 extends Class_1 {}
  public static  class Class_1_1 extends Class_1 {}
  public static  class Class_1_2 extends Class_1 {}
  public static  class Class_1_3 extends Class_1 implements Interface_3_2, Interface_2_0 {}
  public static  class Class_1_4 extends Class_1 implements Interface_3_2, Interface_2_1 {}

  public static  class Class_2_0 extends Class_2 {}
  public static  class Class_2_1 extends Class_2 {}
  public static  class Class_2_2 extends Class_2 {}
  public static  class Class_2_3 extends Class_2 implements Interface_1_0, Interface_2_0 {}
  public static  class Class_2_4 extends Class_2 implements Interface_2 {}

  public static  class Class_2_0_0 extends Class_2_0 {}
  public static  class Class_2_0_1 extends Class_2_0 {}
  public static  class Class_2_0_2 extends Class_2_0 implements Interface_2_0 {}
  public static  class Class_2_0_3 extends Class_2_0 implements Interface_2_1,Interface_3_1 {}
  public static  class Class_2_0_4 extends Class_2_0 implements Interface_4, Interface_5 {}
  public static  class Class_2_0_5 extends Class_2_0 implements Interface_3_2, Interface_5 {}
  public static  class Class_2_0_6 extends Class_2_0 implements Interface_3_1_0, Interface_4 {}

  public static  class Class_2_1_0 extends Class_2_1 {}
  public static  class Class_2_1_1 extends Class_2_1 {}
  public static  class Class_2_1_2 extends Class_2_1 {}
  public static  class Class_2_1_3 extends Class_2_1 implements Interface_3_0_0_0 {}
  public static  class Class_2_1_4 extends Class_2_1 implements Interface_3_0_0_1 {}
  public static  class Class_2_1_5 extends Class_2_1 implements Interface_3_0_0_2 {}

  public static  class Class_2_1_1_0 extends Class_2_1_1 {}
  public static  class Class_2_1_1_1 extends Class_2_1_1 {}
  public static  class Class_2_1_1_2 extends Class_2_1_1 {}
  public static  class Class_2_1_1_3 extends Class_2_1_1 implements Interface_5 {}
  public static  class Class_2_1_1_4 extends Class_2_1_1 implements Interface_3_1_2 {}
  public static  class Class_2_1_1_5 extends Class_2_1_1 implements Interface_3_1_2 {}

  public static  class Class_2_1_2_0 extends Class_2_1_2 {}
  public static  class Class_2_1_2_1 extends Class_2_1_2 {}
  public static  class Class_2_1_2_2 extends Class_2_1_2 {}
  public static  class Class_2_1_2_3 extends Class_2_1_2 implements Interface_5 {}
  public static  class Class_2_1_2_4 extends Class_2_1_2 implements Interface_3_0_0_0 {}
  public static  class Class_2_1_2_5 extends Class_2_1_2 implements Interface_3_0_0_1 {}

  public static  class Class_3_0 extends Class_3 {}
  public static  class Class_3_1 extends Class_3 {}
  public static  class Class_3_2 extends Class_3 {}
  public static  class Class_3_3 extends Class_3 {}
  public static  class Class_3_4 extends Class_3 implements Interface_4 {}
  public static  class Class_3_5 extends Class_3 implements Interface_4, Interface_3_0_0_1 {}
  public static  class Class_3_6 extends Class_3 implements Interface_4, Interface_3_0_0_2 {}
  public static  class Class_3_7 extends Class_3 implements Interface_4, Interface_3_0_0_3 {}

  public static  class Class_4_0 extends Class_4 {}
  public static  class Class_4_1 extends Class_4 {}
  public static  class Class_4_2 extends Class_4 {}
  public static  class Class_4_3 extends Class_4 {}
  public static  class Class_4_4 extends Class_4 {}
  public static  class Class_4_5 extends Class_4 implements Interface_4, Interface_5 {}
  public static  class Class_4_6 extends Class_4 implements Interface_4, Interface_2_1_1 {}
  public static  class Class_4_7 extends Class_4 implements Interface_1, Interface_2_0_1 {}

  public static  class Class_5_0 extends Class_4_4 {}
  public static  class Class_5_1 extends Class_4_4 {}
  public static  class Class_5_2 extends Class_4_4 {}
  public static  class Class_5_3 extends Class_4_4 {}
  public static  class Class_5_4 extends Class_4_4 {}
  public static  class Class_5_5 extends Class_4_4 implements Interface_1, Interface_5 {}
  public static  class Class_5_6 extends Class_4_4 implements Interface_1_0, Interface_2_1_1 {}
  public static  class Class_5_7 extends Class_4_4 implements Interface_4, Interface_3_0_0_0 {}

  public static  class Class_6_0 extends Class_4_5 {}
  public static  class Class_6_1 extends Class_4_5 {}
  public static  class Class_6_2 extends Class_4_5 {}
  public static  class Class_6_3 extends Class_4_5 {}
  public static  class Class_6_4 extends Class_4_5 {}
  public static  class Class_6_5 extends Class_4_5 implements Interface_1 {}
  public static  class Class_6_6 extends Class_4_5 implements Interface_1_0, Interface_2_0_0 {}
  public static  class Class_6_7 extends Class_4_5 implements Interface_4, Interface_2_0 , Interface_3_0 {}

  //@formatter:on
}
