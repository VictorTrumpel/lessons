package simple_tree_node;

import java.util.*;

public class SimpleTreeNode<T> {
  public T NodeValue; // значение в узле
  public SimpleTreeNode<T> Parent; // родитель или null для корня
  public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

  public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
    NodeValue = val;
    Parent = parent;
    Children = null;
  }
}