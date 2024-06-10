/*
  2.0

  Разбор ошибок из прошлого урока:

  На самом деле могу с чистой совестью сказать, что я все сделал правильно. Все учел.
  Добавить нечего - сделал задание на 100%. Применил конструкции Typescript-а для статусов.
  Мне кажется удачной идеей.
 */


// 2.1
public class LinkedList {
  public Node head;
  public Node tail;
  public Node cursor;

  private Integer REMOVE_OK = 1;
  private Integer REMOVE_ERR = 2;

  private Integer FIND_OK = 1;
  private Integer FIND_ERR = 2;

  private Integer remove_status = REMOVE_OK;
  private Integer find_status = FIND_OK;

  public LinkedList2() {
    head = null;
    tail = null;
    cursor = null;
  }

  public void head() {
    cursor = head;
  }

  public void tail() {
    cursor = tail;
  }

  public void right() {
    cursor = cursor.next;
  }

  public Integer get() {
    return cursor.value;
  }

  public void putRight(Integer value) {
    insertAfter(cursor, new Node(value));
  }

  public void putLeft(Integer value) {
    insertAfter(cursor.prev, new Node(value));
  }

  public void remove() {
    if (cursor == null) { 
      remove_status = REMOVE_ERR;
      return;
    }

    if (head == cursor && tail == cursor) {
      removeAll();
      remove_status = REMOVE_OK;
      return;
    }

    if (head == cursor) {
      head = head.next;
      cursor = head;
      remove_status = REMOVE_OK;
      return;
    }

    if (tail == cursor) {
      tail = tail.prev;
      cursor = tail;
      remove_status = REMOVE_OK;
      return;
    }

    Node prevNode = cursor.prev;
    Node nextNode = cursor.next;

    if (prevNode != null) {
      prevNode.next = nextNode;
    }

    if (nextNode != null) {
      nextNode.prev = prevNode;
    }

    if (head == cursor) {
      head = null;
    }

    if (tail == cursor) {
      tail = null;
    }

    if (nextNode != null) {
      cursor = nextNode;
      remove_status = REMOVE_OK;
      return;
    }

    cursor = prevNode;
  }

  public void clear() {
    this.head = null;
    this.tail = null;
    this.cursor = null;
  }

  public int size() {
    int counter = 0;
    Node node = this.head;

    while (node != null) {
      counter += 1;
      node = node.next;
    }

    return counter;
  }

  public void addToEmpty(Integer value) {
    if (cursor != null) return;

    Node node = new Node(value);

    head = node;
    tail = node;
    cursor = node;
  }

  public void addTail(Node _item) {
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

  public void replace(Integer value) {
    if (cursor) {
      cursor.value = value;
    } 
  }

  public void find(int _value) {
    Node node = this.cursor;

    while (node != null) {
      if (node.value == _value) {
        cursor = node;
        return;
      }

      node = node.next;
    }

    if (node != null && node.value == _value) {
      find_status = FIND_OK;
    } else {
      find_status = FIND_ERR;
    }

    return;
  }

  public void removeAll(int _value) {
    Node prevNode = null;
    Node node = this.head;

    while (cursor != null) {
      if (cursor.value == _value) {
        right();
        continue;
      }
      break;
    }

    while (node != null) {
      if (node.value == _value) {
        if (prevNode == null & node.next == null) {
          this.head = null;
          this.tail = null;
        }

        if (prevNode == null) {
          this.head = node.next;
          if (this.head != null)
            this.head.prev = null;
        }

        if (node.next == null) {
          this.tail = prevNode;
          if (this.tail != null)
            this.tail.next = null;
        }

        if (prevNode != null & node.next != null) {
          prevNode.next = node.next;
          node.next.prev = prevNode;
        }
      }

      if (node.value != _value) {
        prevNode = node;
      }

      node = node.next;
    }
  }

  public Boolean is_head() {
    return head == cursor;
  }

  public Boolean is_tail() {
    return tail == cursor;
  }

  public Boolean is_value() {
    return cursor != null;
  }

  public ArrayList<Node> findAll(int _value) {
    ArrayList<Node> nodes = new ArrayList<Node>();

    Node node = this.head;

    while (node != null) {
      if (node.value == _value) {
        nodes.add(node);
      }

      node = node.next;
    }

    return nodes;
  }

  public getRemoveStatus() {
    return remove_status;
  }

  public getFindStatus() {
    return find_status;
  }

  private void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
    if (_nodeAfter != null) {
      if (_nodeAfter.next != null) {
        _nodeToInsert.next = _nodeAfter.next;
        _nodeToInsert.prev = _nodeAfter;

        _nodeAfter.next.prev = _nodeToInsert;
        _nodeAfter.next = _nodeToInsert;

        return;
      }

      if (_nodeAfter.next == null) {
        _nodeAfter.next = _nodeToInsert;
        _nodeToInsert.prev = _nodeAfter;
        this.tail = _nodeToInsert;

        return;
      }
    }

    if (this.head == null) {
      this.head = _nodeToInsert;
      this.tail = _nodeToInsert;

      return;
    }

    _nodeToInsert.next = this.head;

    if (this.head != null) {
      this.head.prev = _nodeToInsert;
    }

    this.head = _nodeToInsert;
  }
}

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

/*
  2.2
  Если мы сведем операцию tail к другим операциям (например putRight), то tail будет выполняться за O(n).
  А так tail выполняется за О(1).
 */

/*
  2.3

  - Потому что эта операция сводится к другим операциям - putRight, get.
  - Мы теперь взаимодействуем с узлами только через класс LinkedList, нам не нужны сами узлы.
  (это даже опасно для нас)
 */