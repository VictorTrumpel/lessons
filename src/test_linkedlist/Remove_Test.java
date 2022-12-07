package test_linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.*;

public class Remove_Test {

  @Test
  public void test1() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));
    list.addInTail(new Node(15));

    list.remove(14);

    Node head = list.head;
    Node n1 = head.next;
    Node n2 = n1.next;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(n1.value, 12);
    assertEquals(n1.prev.value, 11);
    assertEquals(n1.next.value, 15);

    assertEquals(n2.value, 15);
    assertEquals(n2.prev.value, 12);
    assertEquals(n2.next, null);

    // Тест хвоста
    assertEquals(tail, n2);
    assertEquals(tail.prev.value, 12);
    assertEquals(tail.value, 15);
    assertEquals(tail.next, null);
  }

  @Test
  public void test2() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));
    list.addInTail(new Node(15));
    list.addInTail(new Node(16));

    list.remove(12);

    Node head = list.head;
    Node n1 = head.next;
    Node n2 = n1.next;
    Node n3 = n2.next;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 14);

    assertEquals(n1.value, 14);
    assertEquals(n1.prev.value, 11);
    assertEquals(n1.next.value, 15);

    assertEquals(n2.value, 15);
    assertEquals(n2.prev.value, 14);
    assertEquals(n2.next.value, 16);

    assertEquals(n3.value, 16);
    assertEquals(n3.prev.value, 15);
    assertEquals(n3.next, null);

    // Тест хвоста
    assertEquals(tail, n3);
    assertEquals(tail.prev.value, 15);
    assertEquals(tail.value, 16);
    assertEquals(tail.next, null);
  }

  @Test
  public void test3() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));

    list.remove(11);

    Node head = list.head;
    Node n1 = head.next;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head.value, 12);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 14);

    assertEquals(n1.value, 14);
    assertEquals(n1.prev.value, 12);
    assertEquals(n1.next, null);

    // Тест хвоста
    assertEquals(tail, n1);
    assertEquals(tail.prev.value, 12);
    assertEquals(tail.value, 14);
    assertEquals(tail.next, null);
  }

  @Test
  public void test4() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));

    list.remove(14);

    Node head = list.head;
    Node n1 = head.next;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(n1.value, 12);
    assertEquals(n1.prev.value, 11);
    assertEquals(n1.next, null);

    // Тест хвоста
    assertEquals(tail, n1);
    assertEquals(tail.prev.value, 11);
    assertEquals(tail.value, 12);
    assertEquals(tail.next, null);
  }

  @Test
  public void test5() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));

    list.remove(11);

    Node head = list.head;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head, null);

    // Тест хвоста
    assertEquals(tail, null);
  }

  @Test
  public void test6() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));

    list.remove(11);

    Node head = list.head;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head, tail);
    assertEquals(head.value, 12);
    assertEquals(head.prev, null);
    assertEquals(head.next, null);

    // Тест хвоста
    assertEquals(tail.value, 12);
    assertEquals(tail.prev, null);
    assertEquals(tail.next, null);
  }

  @Test
  public void test7() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));

    list.remove(12);

    Node head = list.head;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head, tail);
    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next, null);

    // Тест хвоста
    assertEquals(tail.value, 11);
    assertEquals(tail.prev, null);
    assertEquals(tail.next, null);
  }

  @Test
  public void test8() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(13));

    list.remove(15);

    Node head = list.head;
    Node n1 = head.next;
    Node n2 = n1.next;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head.value, 11);
    assertEquals(head.prev, null);
    assertEquals(head.next.value, 12);

    assertEquals(n1.value, 12);
    assertEquals(n1.prev.value, 11);
    assertEquals(n1.next.value, 13);

    assertEquals(n2.value, 13);
    assertEquals(n2.prev.value, 12);
    assertEquals(n2.next, null);

    // Тест хвоста
    assertEquals(tail, n2);
    assertEquals(tail.value, 13);
    assertEquals(tail.prev.value, 12);
    assertEquals(tail.next, null);
  }

  @Test
  public void test9() {
    LinkedList2 list = new LinkedList2();

    list.remove(12);

    Node head = list.head;
    Node tail = list.tail;

    // Тест головы
    assertEquals(head, null);

    // Тест хвоста
    assertEquals(tail, null);
  }

}
