package recursion;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class SecondMax {

  public int findSecondMax(List<Integer> list, Integer firstMax, Integer secondMax, int currentIdx) {
    if (currentIdx == list.size())
      return secondMax;

    int currentNumber = list.get(currentIdx);

    Integer newFirstMax = null;
    Integer newSecondMax = null;

    if (currentNumber > firstMax) {
      newFirstMax = currentNumber;
      newSecondMax = firstMax;
    } else if (currentNumber > secondMax) {
      newFirstMax = firstMax;
      newSecondMax = currentNumber;
    } else {
      newFirstMax = firstMax;
      newSecondMax = secondMax;
    }

    return findSecondMax(list, newFirstMax, newSecondMax, currentIdx + 1);
  }

  public int findSecondMax(List<Integer> list) {
    if (list.size() == 0)
      throw new Error("Входящий список не может быть пустым");

    if (list.size() == 1)
      return list.get(0);

    int maxNumber = Math.max(list.get(0), list.get(1));
    int minNumber = Math.min(list.get(0), list.get(1));

    return findSecondMax(list, maxNumber, minNumber, 2);
  }

  @Test
  public void test_1() {
    List<Integer> list = new ArrayList<Integer>();

    list.add(5);
    list.add(4);
    list.add(3);
    list.add(2);
    list.add(5);

    assertEquals(5, findSecondMax(list));
  }

  @Test
  public void test_2() {
    List<Integer> list = new ArrayList<Integer>();

    list.add(5);
    list.add(4);
    list.add(3);
    list.add(2);

    assertEquals(4, findSecondMax(list));
  }

  @Test
  public void test_3() {
    List<Integer> list = new ArrayList<Integer>();

    list.add(5);
    list.add(4);
    list.add(3);
    list.add(233);

    assertEquals(5, findSecondMax(list));
  }

  @Test
  public void test_4() {
    List<Integer> list = new ArrayList<Integer>();

    list.add(233);
    list.add(4);
    list.add(3);
    list.add(233);

    assertEquals(233, findSecondMax(list));
  }

  @Test
  public void test_5() {
    List<Integer> list = new ArrayList<Integer>();

    list.add(233);
    list.add(5);
    list.add(3);
    list.add(233);

    assertEquals(233, findSecondMax(list));
  }

  @Test
  public void test_6() {
    List<Integer> list = new ArrayList<Integer>();

    list.add(233);
    list.add(5);
    list.add(3);
    list.add(1);

    assertEquals(5, findSecondMax(list));
  }

  @Test
  public void test_7() {
    List<Integer> list = new ArrayList<Integer>();

    list.add(233);
    list.add(5);
    list.add(3);
    list.add(277);

    assertEquals(233, findSecondMax(list));
  }
}
