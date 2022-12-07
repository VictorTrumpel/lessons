package test_linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.*;

public class Counter_Test {

  @Test
  public void test1() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));
    list.addInTail(new Node(15));

    assertEquals(list.count(), 4);
  }

  @Test
  public void test2() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));

    assertEquals(list.count(), 2);
  }

  @Test
  public void test3() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));

    assertEquals(list.count(), 1);
  }

  @Test
  public void test4() {
    LinkedList2 list = new LinkedList2();

    assertEquals(list.count(), 0);
  }
}
