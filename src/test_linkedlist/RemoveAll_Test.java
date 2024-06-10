package test_linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.*;

public class RemoveAll_Test {

  @Test
  public void test1() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));
    list.addInTail(new Node(14));

    list.removeAll(14);

    Node head = list.head;
    Node n1 = head.next;
    Node tail = list.tail;

    assertEquals(list.count(), 2);

    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(n1.value, 12);
    assertEquals(n1.prev, head);
    assertEquals(n1.next, null);

    assertEquals(tail, n1);
    assertEquals(tail.value, 12);
    assertEquals(tail.prev.value, 11);
    assertEquals(tail.next, null);
  }

  @Test
  public void test2() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(14));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));
    list.addInTail(new Node(14));

    list.removeAll(14);

    Node head = list.head;
    Node tail = list.tail;

    assertEquals(head.value, 12);
    assertEquals(head.prev, null);
    assertEquals(head.next, null);

    assertEquals(tail.value, 12);
    assertEquals(tail.prev, null);
    assertEquals(tail.next, null);
  }

  @Test
  public void test3() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(14));
    list.addInTail(new Node(14));
    list.addInTail(new Node(14));
    list.addInTail(new Node(14));

    list.removeAll(14);

    Node head = list.head;
    Node tail = list.tail;

    assertEquals(head, null);
    assertEquals(tail, null);
  }

  @Test
  public void test4() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(14));

    list.removeAll(14);

    Node head = list.head;
    Node tail = list.tail;

    assertEquals(head, null);
    assertEquals(tail, null);
  }

  @Test
  public void test5() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(14));
    list.addInTail(new Node(13));

    list.removeAll(14);

    Node head = list.head;
    Node tail = list.tail;

    assertEquals(head.value, 13);
    assertEquals(head.prev, null);
    assertEquals(head.next, null);
    assertEquals(tail, head);
  }

  @Test
  public void test6() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(12));
    list.addInTail(new Node(5));
    list.addInTail(new Node(13));
    list.addInTail(new Node(5));
    list.addInTail(new Node(14));

    list.removeAll(5);

    Node head = list.head;
    Node n1 = head.next;
    Node n2 = n1.next;
    Node tail = list.tail;

    assertEquals(head.value, 12);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 13);

    assertEquals(n1.value, 13);
    assertEquals(n1.prev.value, 12);
    assertEquals(n1.next.value, 14);

    assertEquals(n2.value, 14);
    assertEquals(n2.prev.value, 13);
    assertEquals(n2.next, null);

    assertEquals(tail.value, 14);
    assertEquals(tail.prev.value, 13);
    assertEquals(tail.next, null);
    assertEquals(tail, n2);
  }

  @Test
  public void test7() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(5));
    list.addInTail(new Node(12));
    list.addInTail(new Node(5));
    list.addInTail(new Node(13));
    list.addInTail(new Node(5));

    list.removeAll(5);

    Node head = list.head;
    Node n1 = head.next;
    Node tail = list.tail;

    assertEquals(head.value, 12);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 13);

    assertEquals(n1.value, 13);
    assertEquals(n1.prev.value, 12);
    assertEquals(n1.next, null);

    assertEquals(tail.value, 13);
    assertEquals(tail.prev.value, 12);
    assertEquals(tail.next, null);
    assertEquals(tail, n1);
  }

  @Test
  public void test8() {
    LinkedList2 list = new LinkedList2();

    list.removeAll(5);

    Node head = list.head;
    Node tail = list.tail;

    assertEquals(head, null);
    assertEquals(tail, head);
  }

  @Test
  public void test9() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(5));
    list.addInTail(new Node(12));
    list.addInTail(new Node(13));

    list.removeAll(17);

    Node head = list.head;
    Node n1 = head.next;
    Node tail = list.tail;

    assertEquals(head.value, 5);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(n1.value, 12);
    assertEquals(n1.prev.value, 5);
    assertEquals(n1.next.value, 13);

    assertEquals(tail.value, 13);
    assertEquals(tail.prev.value, 12);
    assertEquals(tail.next, null);
  }
}
