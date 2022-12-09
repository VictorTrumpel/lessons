public class Queue<T> {
  private Node<T> head;
  private Node<T> tail;
  private int length;

  public Queue() {
    head = null;
    tail = null;
    length = 0;
  }

  public void enqueue(T item) {
    Node<T> newNode = new Node<T>(item);
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

  class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T _value) {
      value = _value;
      next = null;
    }
  }
}
