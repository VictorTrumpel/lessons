package test_rmq;

import static org.junit.Assert.*;

import org.junit.Test;

import rmq.*;

public class RMQ_Test {
  @Test
  public void test_1() {
    CalcMinValue calcMinValue = new CalcMinValue();

    int[] segment = {1, 2, 3};
    
    Rmq rmq = new Rmq(segment, calcMinValue);

    rmq.createFromTopToDown();

    assertArrayEquals(rmq.root.segment, segment);
    
    Node leftCh1 = rmq.root.children.get(0);
    Node rightCh1 = rmq.root.children.get(1);

    int[] leftCh1S = { 1 };
    int[] rightCh1S = { 2, 3 };

    assertArrayEquals(leftCh1.segment, leftCh1S);
    assertArrayEquals(rightCh1.segment, rightCh1S);

    assertEquals(leftCh1.parent, rmq.root);
    assertEquals(leftCh1.children.size(), 0);

    int[] leftRightCh2 = { 2 };
    int[] rightRightCh2 = { 3 };

    assertArrayEquals(rightCh1.children.get(0).segment, leftRightCh2);
    assertArrayEquals(rightCh1.children.get(1).segment, rightRightCh2);

    assertEquals(rightCh1.children.get(0).parent, rightCh1);
    assertEquals(rightCh1.children.get(0).children.size(), 0);

    assertEquals(rightCh1.children.get(1).parent, rightCh1);
    assertEquals(rightCh1.children.get(1).children.size(), 0);
  }

  @Test
  public void test_2() {
    CalcMinValue calcMinValue = new CalcMinValue();

    int[] segment = {1, 2, 3};
    
    Rmq rmq = new Rmq(segment, calcMinValue);

    rmq.createFromBottomToTop();

    assertEquals(rmq.root.children.size(), 2);
    assertEquals(rmq.root.children.get(0).children.size(), 2);
    assertEquals(rmq.root.children.get(1).children.size(), 0);


    assertEquals(rmq.root.children.get(0).children.get(0).children.size(), 0);
    assertEquals(rmq.root.children.get(0).children.get(1).children.size(), 0);
  }

  @Test
  public void test_3() {
    CalcMinValue calcMinValue = new CalcMinValue();
    int[] segment1 = { 1, 2, 3 };
    int[] segment2 = { 3, 3, 5, 6, 8};

    assertEquals(calcMinValue.calculate(segment1), 6);

    assertEquals(calcMinValue.calculate(segment2), 25);

  }

}
