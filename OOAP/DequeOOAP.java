package deque;

// В предыдущем задании все сделал правильно

public class DequeOOAP<T> {
  private Node head;
  private Node tail;
  private int length;

  private Integer GET_HEAD_OK = 1;
  private Integer GET_HEAD_ERR = 2;

  private Integer GET_TAIL_OK = 1;
  private Integer GET_TAIL_ERR = 2;

  private Integer REMOVE_HEAD_OK = 1;
  private Integer REMOVE_HEAD_ERR = 2;

  private Integer REMOVE_TAIL_OK = 1;
  private Integer REMOVE_TAIL_ERR = 2;

  private Integer getHeadStatus = GET_HEAD_OK;
  private Integer getTailStatus = GET_TAIL_OK;

  private Integer removeHeadStatus = REMOVE_HEAD_OK;
  private Integer removeTailStatus = REMOVE_TAIL_OK;

  public DequeOOAP() {
    head = null;
    tail = null;
    length = 0;
  }

  public T getHead() {
    if (length == 0) {
      getHeadStatus = GET_HEAD_ERR;
      return null;
    }

    getHeadStatus = GET_HEAD_OK;
    return head.value;
  }

  public T getTail() {
    if (length == 0) {
      getTailStatus = GET_TAIL_ERR;
      return null;
    }

    getTailStatus = GET_TAIL_OK;
    return tail.value;
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
    if (head == null) {
      removeHeadStatus = REMOVE_HEAD_ERR;
      return null;
    }

    removeHeadStatus = REMOVE_HEAD_OK;

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
    if (tail == null) {
      removeTailStatus = REMOVE_TAIL_ERR;
      return null;
    }

    removeTailStatus = REMOVE_TAIL_OK;

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

  public Integer getGetHeadStatus() {
    return getHeadStatus;
  }

  public Integer getGetTailStatus() {
    return getTailStatus;
  }

  public Integer getRemoveHeadStatus() {
    return removeHeadStatus;
  }

  public Integer getRemoveTailStatus() {
    return removeTailStatus;
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