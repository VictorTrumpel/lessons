package test_OrderedList;

import static org.junit.Assert.*;

import ordered_list.*;

import org.junit.Test;

import java.util.*;

public class OrderedList_Test {

  @Test
  public void test_1() {
    OrderedList<Integer> list = new OrderedList<Integer>(false);

    assertEquals(list.compare(1, 2), -1);
    assertEquals(list.compare(2, 2), 0);
    assertEquals(list.compare(3, 2), 1);

    assertEquals(list.compare(-1, 2), -1);
    assertEquals(list.compare(2, -4), 1);
    assertEquals(list.compare(3, 2), 1);

    OrderedList<String> listString = new OrderedList<String>(false);

    assertEquals(listString.compare("a", "d"), -1);
    assertEquals(listString.compare("a", "a"), 0);
    assertEquals(listString.compare("c", "a"), 1);
  }

  @Test
  public void test_2() {
    OrderedList<Integer> list = new OrderedList<Integer>(true);
    list.add(2);
    list.add(3);
    list.add(0);
    list.add(1);

    assertEquals(list.count(), 4);

    ArrayList<Node<Integer>> arr = list.getAll();

    assertEquals(arr.size(), 4);

    assertEquals(arr.get(0).value, (Integer) 0);
    assertEquals(arr.get(1).value, (Integer) 1);
    assertEquals(arr.get(2).value, (Integer) 2);
    assertEquals(arr.get(3).value, (Integer) 3);
  }

  @Test
  public void test_3() {
    OrderedList<Integer> list = new OrderedList<Integer>(true);
    list.add(3);
    list.add(2);
    list.add(1);
    list.add(0);

    assertEquals(list.count(), 4);

    ArrayList<Node<Integer>> arr = list.getAll();

    assertEquals(arr.size(), 4);

    assertEquals(arr.get(0).value, (Integer) 0);
    assertEquals(arr.get(1).value, (Integer) 1);
    assertEquals(arr.get(2).value, (Integer) 2);
    assertEquals(arr.get(3).value, (Integer) 3);
  }

  @Test
  public void test_4() {
    OrderedList<Integer> list = new OrderedList<Integer>(true);
    list.add(3);
    list.add(-3);
    list.add(1);
    list.add(0);
    list.add(-1);
    list.add(4);

    assertEquals(list.count(), 6);

    ArrayList<Node<Integer>> arr = list.getAll();

    assertEquals(arr.size(), 6);

    assertEquals(arr.get(0).value, (Integer) (-3));
    assertEquals(arr.get(1).value, (Integer) (-1));
    assertEquals(arr.get(2).value, (Integer) 0);
    assertEquals(arr.get(3).value, (Integer) 1);
    assertEquals(arr.get(4).value, (Integer) 3);
    assertEquals(arr.get(5).value, (Integer) 4);
  }

  @Test
  public void test_5() {
    OrderedList<Integer> list = new OrderedList<Integer>(false);
    list.add(3);
    list.add(-3);

    assertEquals(list.head.value, (Integer) 3);
    assertEquals(list.head.next.value, (Integer) (-3));

    list.add(1);

    assertEquals(list.head.value, (Integer) 3);
    assertEquals(list.head.next.value, (Integer) (1));
    assertEquals(list.head.next.next.value, (Integer) (-3));

    list.add(0);

    assertEquals(list.head.value, (Integer) 3);
    assertEquals(list.head.next.value, (Integer) (1));
    assertEquals(list.head.next.next.value, (Integer) (0));
    assertEquals(list.head.next.next.next.value, (Integer) (-3));

    list.add(-1);
    list.add(4);

    assertEquals(list.count(), 6);

    ArrayList<Node<Integer>> arr = list.getAll();

    assertEquals(arr.size(), 6);

    assertEquals(arr.get(0).value, (Integer) 4);
    assertEquals(arr.get(1).value, (Integer) 3);
    assertEquals(arr.get(2).value, (Integer) 1);
    assertEquals(arr.get(3).value, (Integer) 0);
    assertEquals(arr.get(4).value, (Integer) (-1));
    assertEquals(arr.get(5).value, (Integer) (-3));
  }
}
