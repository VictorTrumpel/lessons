package bst;

public class BSTNode<T> {
  public int NodeKey; // ключ узла
  public T NodeValue; // значение в узле
  public BSTNode<T> Parent; // родитель или null для корня
  public BSTNode<T> LeftChild; // левый потомок
  public BSTNode<T> RightChild; // правый потомок

  public BSTNode(int key, T val, BSTNode<T> parent) {
    NodeKey = key;
    NodeValue = val;
    Parent = parent;
    LeftChild = null;
    RightChild = null;
  }
}