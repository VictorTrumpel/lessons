package ordered_list;

import java.util.*;

public class OrderedList<T> {
  // ВЕРНУТЬ КЛАСС НОДЫ В ФАЙЛ
  public Node<T> head, tail;
  private boolean _ascending;
  private int length = 0;

  public OrderedList(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
  }

  public int compare(T v1, T v2) {
    if (v1 instanceof Integer && v2 instanceof Integer) {
      Integer _v1 = (Integer) v1;
      Integer _v2 = (Integer) v2;

      if (_v1 < _v2)
        return -1;
      if (_v1 == _v2)
        return 0;
      if (_v1 > _v2)
        return 1;
    }

    if (v1 instanceof String && v2 instanceof String) {
      String _v1 = ((String) v1).trim();
      String _v2 = ((String) v2).trim();

      if (_v1.compareTo(_v2) < 0)
        return -1;
      if (_v1.compareTo(_v2) == 0)
        return 0;
      if (_v1.compareTo(_v2) > 0)
        return 1;
    }

    return 0;
  }

  public void add(T value) {
    Node<T> newNode = new Node<T>(value);
    length += 1;

    if (head == null) {
      head = newNode;
      tail = newNode;
      return;
    }

    Node<T> insertAfterNode = null;
    Node<T> currNode = head;

    if (_ascending && compare(newNode.value, tail.value) >= 0) {
      insertAfter(tail, newNode);
      return;
    }

    if (_ascending && compare(newNode.value, head.value) <= 0) {
      insertAfter(null, newNode);
      return;
    }

    if (!_ascending && compare(newNode.value, tail.value) <= 0) {
      insertAfter(tail, newNode);
      return;
    }

    if (!_ascending && compare(newNode.value, head.value) >= 0) {
      insertAfter(null, newNode);
      return;
    }

    if (_ascending) {
      while (currNode != null) {
        int compareResult = compare(newNode.value, currNode.value);
        if (compareResult <= 0) {
          insertAfterNode = currNode.prev;
          break;
        }
        currNode = currNode.next;
      }
    }

    if (!_ascending) {
      while (currNode != null) {
        int compareResult = compare(newNode.value, currNode.value);
        if (compareResult >= 0) {
          insertAfterNode = currNode.prev;
          break;
        }
        currNode = currNode.next;
      }
    }

    insertAfter(insertAfterNode, newNode);
  }

  public Node<T> find(T val) {
    Node<T> node = this.head;

    while (node != null) {
      if (node.value == val) {
        return node;
      }

      node = node.next;
    }

    return null;
  }

  public void delete(T val) {
    Node<T> node = this.head;

    while (node != null) {
      if (node.value == val) {
        if (node.prev != null & node.next != null) {
          node.prev.next = node.next;
          node.next.prev = node.prev;

          return;
        }

        if (node.prev == null & node.next == null) {
          this.head = null;
          this.tail = null;

          return;
        }

        if (node.prev == null) {
          this.head = node.next;
          this.head.prev = null;
        }

        if (node.next == null) {
          this.tail = node.prev;
          this.tail.next = null;
        }

        if (length > 0)
          length -= 1;

        return;
      }

      node = node.next;
    }

    return;
  }

  public void clear(boolean asc) {
    _ascending = asc;
    this.head = null;
    this.tail = null;
  }

  public int count() {
    return length;
  }

  // УБРАТЬ PUBLIC
  public ArrayList<Node<T>> getAll() {
    ArrayList<Node<T>> r = new ArrayList<Node<T>>();
    Node<T> node = head;
    while (node != null) {
      r.add(node);
      node = node.next;
    }
    return r;
  }

  private void insertAfter(Node<T> _nodeAfter, Node<T> _nodeToInsert) {
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