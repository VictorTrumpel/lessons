public class LinkedList2 {
  public Node head;
  public Node tail;

  public LinkedList2() {
    head = null;
    tail = null;
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

  public Node find(int _value) {
    Node node = this.head;

    while (node != null) {
      if (node.value == _value) {
        return node;
      }

      node = node.next;
    }

    return null;
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

  public boolean remove(int _value) {
    Node node = this.head;

    while (node != null) {
      if (node.value == _value) {
        if (node.prev != null & node.next != null) {
          node.prev.next = node.next;
          node.next.prev = node.prev;

          return true;
        }

        if (node.prev == null & node.next == null) {
          this.head = null;
          this.tail = null;

          return true;
        }

        if (node.prev == null) {
          this.head = node.next;
          this.head.prev = null;
        }

        if (node.next == null) {
          this.tail = node.prev;
          this.tail.next = null;
        }

        return true;
      }

      node = node.next;
    }

    return true;
  }

  public void removeAll(int _value) {
    Node prevNode = null;
    Node node = this.head;

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

  public void clear() {
    this.head = null;
    this.tail = null;
  }

  public int count() {
    int counter = 0;
    Node node = this.head;

    while (node != null) {
      counter += 1;
      node = node.next;
    }

    return counter;
  }

  public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
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