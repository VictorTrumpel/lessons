package linkedlist;

import java.util.*;

// ДОБАВИТЬ СЮДА КЛАСС Node
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

  private Node removeNode(Node cursor) {
    if (cursor == null) return null;

    Node prevNode = cursor.prev;
    Node nextNode = cursor.next;

    cursor.prev = null;
    cursor.next = null;

    if (head == cursor && tail == cursor) {
      head = null;
      tail = null;
      return null;
    }

    if (head == cursor) {
      head = head.next;
      cursor = head;
      return cursor;
    }

    if (tail == cursor) {
      tail = tail.prev;
      if (tail != null) {
        tail.next = null;
      }
      cursor = tail;
      return cursor;
    }

    if (prevNode != null) {
      prevNode.next = nextNode;
    }

    if (nextNode != null) {
      nextNode.prev = prevNode;
    }

    if (nextNode != null) {
      return nextNode;
    }

    return prevNode;
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
