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

    if (length == 0) {
      head = null;
      tail = null;
      return head_value;
    }

    head = head.next;
    head.prev = null;

    return head_value;
  }

  public T removeTail() {
    if (tail == null)
      return null;

    length -= 1;

    T tail_value = tail.value;

    if (length == 0) {
      head = null;
      tail = null;
      return tail_value;
    }

    tail = tail.prev;
    tail.next = null;

    return tail_value;
  }

  public int size() {
    return length;
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
