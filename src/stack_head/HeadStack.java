package stack_head;

public class HeadStack<T> {
  private Node<T> head;
  private int length;

  public HeadStack() {
    head = null;
    length = 0;
  }

  public int size() {
    return length;
  }

  public T pop() {
    if (head == null)
      return null;

    T head_value = head.value;
    head = head.next;

    length -= 1;

    return head_value;
  }

  public void push(T val) {
    Node<T> new_head = new Node<T>(val);
    new_head.next = head;
    head = new_head;
    length += 1;
  }

  public T peek() {
    if (head == null)
      return null;

    return head.value;
  }
}

class Node<T> {
  public T value;
  public Node<T> next;

  public Node(T _value) {
    value = _value;
    next = null;
  }
}
