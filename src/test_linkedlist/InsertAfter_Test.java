package test_linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.*;

public class InsertAfter_Test {

  @Test
  public void test1() {
    LinkedList2 list = new LinkedList2();

    Node n1 = new Node(11);
    Node n2 = new Node(12);

    Node addedNode = new Node(13);

    list.addInTail(n1);
    list.addInTail(n2);

    list.insertAfter(n2, addedNode);

    Node head = list.head;
    Node N1 = head.next;
    Node tail = list.tail;

    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(N1.value, 12);
    assertEquals(N1.prev.value, 11);
    assertEquals(N1.next.value, 13);

    assertEquals(tail.value, 13);
    assertEquals(tail.prev.value, 12);
    assertEquals(tail.next, null);
  }

  @Test
  public void test2() {
    LinkedList2 list = new LinkedList2();

    Node n1 = new Node(11);
    Node n2 = new Node(12);

    Node addedNode = new Node(10);

    list.addInTail(n1);
    list.addInTail(n2);

    list.insertAfter(null, addedNode);

    Node head = list.head;
    Node N1 = head.next;
    Node tail = list.tail;

    assertEquals(head.value, 10);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 11);

    assertEquals(N1.value, 11);
    assertEquals(N1.prev.value, 10);
    assertEquals(N1.next.value, 12);

    assertEquals(tail.value, 12);
    assertEquals(tail.prev.value, 11);
    assertEquals(tail.next, null);
  }

  @Test
  public void test3() {
    LinkedList2 list = new LinkedList2();

    Node n1 = new Node(11);
    Node n2 = new Node(13);

    Node addedNode = new Node(12);

    list.addInTail(n1);
    list.addInTail(n2);

    list.insertAfter(n1, addedNode);

    Node head = list.head;
    Node N1 = head.next;
    Node tail = list.tail;

    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(N1.value, 12);
    assertEquals(N1.prev.value, 11);
    assertEquals(N1.next.value, 13);

    assertEquals(tail.value, 13);
    assertEquals(tail.prev.value, 12);
    assertEquals(tail.next, null);
  }

  @Test
  public void test4() {
    LinkedList2 list = new LinkedList2();

    Node addedNode = new Node(12);

    list.insertAfter(null, addedNode);

    Node head = list.head;
    Node tail = list.tail;

    assertEquals(head.value, 12);
    assertEquals(head.prev, null);
    assertEquals(head.next, null);
    assertEquals(head, tail);
  }

  @Test
  public void test5() {
    LinkedList2 list = new LinkedList2();

    Node n1 = new Node(10);

    list.addInTail(n1);

    list.insertAfter(n1, new Node(11));

    Node head = list.head;
    Node N1 = head.next;
    Node tail = list.tail;

    assertEquals(head.value, 10);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 11);

    assertEquals(N1.value, 11);
    assertEquals(N1.prev.value, 10);
    assertEquals(N1.next, null);

    assertEquals(tail.value, 11);
    assertEquals(tail.prev.value, 10);
    assertEquals(tail.next, null);
    assertEquals(tail, N1);
  }

  @Test
  public void test6() {
    LinkedList2 list = new LinkedList2();

    Node n1 = new Node(11);

    list.addInTail(n1);

    list.insertAfter(null, new Node(10));

    Node head = list.head;
    Node N1 = head.next;
    Node tail = list.tail;

    assertEquals(head.value, 10);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 11);

    assertEquals(N1.value, 11);
    assertEquals(N1.prev.value, 10);
    assertEquals(N1.next, null);

    assertEquals(tail.value, 11);
    assertEquals(tail.prev.value, 10);
    assertEquals(tail.next, null);
    assertEquals(tail, N1);
  }

  @Test
  public void test7() {
    LinkedList2 list = new LinkedList2();

    Node n1 = new Node(11);
    Node n2 = new Node(12);
    Node n3 = new Node(14);

    list.addInTail(n1);
    list.addInTail(n2);
    list.addInTail(n3);

    list.insertAfter(n2, new Node(13));

    Node head = list.head;
    Node N1 = head.next;
    Node N2 = N1.next;
    Node N3 = N2.next;
    Node tail = list.tail;

    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(N1.value, 12);
    assertEquals(N1.prev.value, 11);
    assertEquals(N1.next.value, 13);

    assertEquals(N2.value, 13);
    assertEquals(N2.prev.value, 12);
    assertEquals(N2.next.value, 14);

    assertEquals(N3.value, 14);
    assertEquals(N3.prev.value, 13);
    assertEquals(N3.next, null);

    assertEquals(N3, tail);
  }
}
