public class LinkedList {
  public Node head;
  public Node tail;

  public LinkedList() {
    head = null;
    tail = null;
  }

  public void addInTail(Node item) {
    if (this.head == null)
      this.head = item;
    else
      this.tail.next = item;
    this.tail = item;
  }

  public Node find(int value) {
    Node node = this.head;
    while (node != null) {
      if (node.value == value)
        return node;
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
    Node prevNode = null;
    Node node = this.head;

    while (node != null) {
      if (node.value == _value) {
        if (prevNode == null) {
          this.head = node.next;
        } else {
          prevNode.next = node.next;
        }

        if (node.next == null) {
          this.tail = prevNode;
        }

        return true;
      }

      prevNode = node;
      node = node.next;
    }

    return true;
  }

  public void removeAll(int _value) {
    Node prevNode = null;
    Node node = this.head;

    while (node != null) {
      if (node.value == _value) {
        if (prevNode != null) {
          prevNode.next = node.next;
        } else {
          this.head = node.next;
        }
      }

      if (node.value != _value) {
        prevNode = node;
      }

      node = node.next;
    }

    this.tail = prevNode;
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
    if (_nodeAfter == null) {
      _nodeToInsert.next = this.head;
      this.head = _nodeToInsert;
    } else {
      _nodeToInsert.next = _nodeAfter.next;
      _nodeAfter.next = _nodeToInsert;
    }

    if (_nodeToInsert.next == null) {
      this.tail = _nodeToInsert;
    }
  }
}

class Node {
  public int value;
  public Node next;

  public Node(int _value) {
    value = _value;
    next = null;
  }
}
