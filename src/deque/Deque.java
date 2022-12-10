package deque;

import java.util.*;

public class Deque<T> {
  private Node head;
  private Node tail;
  private int length;

  public Deque() {
    head = null;
    tail = null;
    length = 0;
  }

  public void addFront(T item) {
    length += 1;
    Node new_head = new Node(item);

    if (head == null) {
      head = new_head;
      tail = new_head;

      return;
    }

    new_head.next = head;
    head.prev = new_head;
    head = new_head;
  }

  public void addTail(T item) {
    length += 1;
    Node new_tail = new Node(item);

    if (tail == null) {
      head = new_tail;
      tail = new_tail;

      return;
    }

    tail.next = new_tail;
    new_tail.prev = tail;
    tail = new_tail;
  }

  public T removeFront() {
    if (head == null)
      return null;

    length -= 1;

    T head_value = head.value;
    head = head.next;
    head.prev = null;
    // удаление из головы
    return null;
  }

  public T removeTail() {
    // удаление из хвоста
    return null;
  }

  public int size() {
    return 0; // размер очереди
  }

  class Node {
    public T value;
    public Node next;
    public Node prev;

    public Node(T _value) {
      value = _value;
      next = null;
      prev = null;
    }
  }
}