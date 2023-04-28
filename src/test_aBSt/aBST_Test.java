package test_aBSt;

import static org.junit.Assert.*;

import org.junit.Test;

import abst.*;

import java.util.*;

public class aBST_Test {

  @Test
  public void test_1() {
    ABST abst1 = new ABST(4);

    assertEquals(15, abst1.Tree.length);

    ABST abst2 = new ABST(1);

    assertEquals(1, abst2.Tree.length);

    ABST abst3 = new ABST(0);

    assertEquals(0, abst3.Tree.length);
  }

  @Test
  public void test_2() {
    ABST abst = new ABST(4);

    Integer keyIdx = abst.FindKeyIndex(50);

    assertEquals((Integer) 0, keyIdx);

    abst.AddKey(50);

    assertEquals((Integer) 0, keyIdx);
    assertEquals((Integer) (-1), abst.FindKeyIndex(25));

    abst.AddKey(25);

    assertEquals((Integer) 0, abst.FindKeyIndex(50));
    assertEquals((Integer) 1, abst.FindKeyIndex(25));
    assertEquals((Integer) (-2), abst.FindKeyIndex(75));

    abst.AddKey(75);

    assertEquals((Integer) 0, abst.FindKeyIndex(50));
    assertEquals((Integer) 1, abst.FindKeyIndex(25));
    assertEquals((Integer) 2, abst.FindKeyIndex(75));
    assertEquals((Integer) (-4), abst.FindKeyIndex(37));

    abst.AddKey(37);

    assertEquals((Integer) 0, abst.FindKeyIndex(50));
    assertEquals((Integer) 1, abst.FindKeyIndex(25));
    assertEquals((Integer) 2, abst.FindKeyIndex(75));
    assertEquals((Integer) 4, abst.FindKeyIndex(37));
    assertEquals((Integer) (-9), abst.FindKeyIndex(31));

    abst.AddKey(31);

    assertEquals((Integer) 0, abst.FindKeyIndex(50));
    assertEquals((Integer) 1, abst.FindKeyIndex(25));
    assertEquals((Integer) 2, abst.FindKeyIndex(75));
    assertEquals((Integer) 4, abst.FindKeyIndex(37));
    assertEquals((Integer) 9, abst.FindKeyIndex(31));
    assertEquals((Integer) (-10), abst.FindKeyIndex(43));
  }
}
