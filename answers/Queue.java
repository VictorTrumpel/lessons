public class Queue<T> {
  private Node head;
  private Node tail;
  private int length;

  public Queue() {
    head = null;
    tail = null;
    length = 0;
  }

  public void enqueue(T item) {
    Node newNode = new Node(item);
    length += 1;

    if (tail == null) {
      head = newNode;
      tail = newNode;
      return;
    }

    tail.next = newNode;
    tail = newNode;
  }

  public T dequeue() {
    if (head == null) {
      return null;
    }
    length -= 1;
    T headValue = head.value;
    head = head.next;

    return headValue;
  }

  public int size() {
    return length;
  }

  class Node {
    public T value;
    public Node next;

    public Node(T _value) {
      value = _value;
      next = null;
    }
  }
}
