/*
Обработка предыдущих ошибок:

2.1 - Обработка ошибок с предыдущего задания:
В реализации АТД я не добавил все необходимые статусы для методов. Исправил это уже в ParentList.

2.2. - Правильно

2.3. - Правильно



 */

// 3
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

public class ParentList {
  public Node head;
  public Node tail;
  public Node cursor;

  private Integer HEAD_OK = 1;
  private Integer HEAD_ERR = 2;

  private Integer TAIL_OK = 1;
  private Integer TAIL_ERR = 2;

  private Integer REMOVE_OK = 1;
  private Integer REMOVE_ERR = 2;

  private Integer REPLACE_OK = 1;
  private Integer REPLACE_ERR = 2;

  private Integer FIND_OK = 1;
  private Integer FIND_ERR = 2;

  private Integer RIGHT_OK = 1;
  private Integer RIGHT_ERR = 2;

  private Integer remove_status = REMOVE_OK;
  private Integer find_status = FIND_OK;
  private Integer right_status = RIGHT_OK;
  private Integer replace_status = REPLACE_OK;
  private Integer head_status = HEAD_OK;
  private Integer tail_status = TAIL_OK;

  public ParentList() {
    head = null;
    tail = null;
    cursor = null;
  }

  public void head() {
    if (cursor != null) {
      cursor = head;
      head_status = HEAD_OK;
      return;
    }
    head_status = HEAD_ERR;
  }

  public void tail() {
    if (cursor != null) {
      cursor = tail;
      tail_status = TAIL_OK;
      return;
    }
    tail_status = TAIL_ERR;
  }

  public void right() {
    if (cursor.next != null) {
      right_status = RIGHT_OK;
      cursor = cursor.next;
    }
    right_status = RIGHT_ERR;
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
    if (cursor != null) {
      cursor.value = value;
      remove_status = REPLACE_OK;
      return;
    } 
    remove_status = REMOVE_ERR;
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

  public Integer getRemoveStatus() {
    return remove_status;
  }

  public Integer getFindStatus() {
    return find_status;
  }

  public Integer getRightStatus() {
    return right_status;
  }

  public Integer getReplaceStatus() {
    return remove_status;
  }

  public Integer getHeadStatus() {
    return head_status;
  }

  public Integer getTailStatus() {
    return tail_status;
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

class LinkedList extends ParentList {}

class TwoWayList extends ParentList {
  private Integer LEFT_OK = 1;
  private Integer LEFT_ERR = 2;

  private Integer left_status = LEFT_OK;

  public void left() {
    if (this.cursor != null) {
      this.cursor = this.cursor.prev;
      left_status = LEFT_OK;
      return;
    }
    left_status = LEFT_ERR;
  }

  public Integer getLeftStatus() {
    return left_status;
  }
}