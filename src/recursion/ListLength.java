package recursion;

import static org.junit.Assert.*;

import org.junit.Test;

class Node {
  public int value;
  public Node next;
  public Node prev;

  public Node(int _value) {
    value = _value;
    next = null;
    prev = null;
  }
}

class LinkedList {
  public Node head;
  public Node tail;

  public LinkedList() {
    head = null;
    tail = null;
  }

  public void pop() {
    if (head == null)
      return;

    head = head.next;
  }

  public int length() {
    if (head == null)
      return 0;

    pop();

    return 1 + length();
  }

  public void addInTail(Node _item) {
    if (head == null) {
      this.head = _item;
      this.head.next = null;
      this.head.prev = null;
    } else {
      this.tail.next = _item;
      _item.prev = tail;
    }
    this.tail = _item;
  }
}

public class ListLength {
  @Test
  public void test_1() {
    LinkedList list = new LinkedList();
    list.addInTail(new Node(1));
    list.addInTail(new Node(2));
    list.addInTail(new Node(3));

    assertEquals(list.length(), 3);

    assertEquals(list.length(), 0);

    list.addInTail(new Node(1));

    assertEquals(list.length(), 1);

    assertEquals(list.length(), 0);
    assertEquals(list.length(), 0);

    list.addInTail(new Node(1));
    list.addInTail(new Node(2));
    list.addInTail(new Node(3));
    list.addInTail(new Node(4));

    assertEquals(list.length(), 4);
  }
}