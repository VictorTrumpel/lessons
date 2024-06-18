/*
  Работа над ошибками:
  Я добавил все необходимые методы и статусы. Но методы мои называются иначе.
  Но идеологически все верно.
*/

public class QueueOOAP<T> {
  private Node head;
  private int length;

  private Integer GET_OK = 1;
  private Integer GET_ERR = 2;

  private Integer POP_OK = 1;
  private Integer POP_ERR = 2;

  private Integer getStatus = GET_OK;
  private Integer popStatus = POP_OK;

  public QueueOOAP() {
    head = null;
    length = 0;
  }

  public T get() {
    if (length == 0) {
      getStatus = GET_ERR;
      return null;
    }
    getStatus = GET_OK;
    return head.value;
  }

  public void push(T value) {
    length += 1;
    Node newNode = new Node(value);
    head.next = newNode;
  }

  public void pop() {
    if (length == 0) {
      popStatus = POP_ERR;
      return;
    }
    popStatus = POP_OK;
    length -= 1;
    head = head.next;
  }

  public Integer size() {
    return length;
  }

  public Integer getGetStatus() {
    return getStatus;
  }

  public Integer getPopStatus() {
    return popStatus;
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

