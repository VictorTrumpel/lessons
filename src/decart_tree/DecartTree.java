package decart_tree;

import java.util.*;

public class DecartTree {
  public class Node {
    public Integer key;
    public Integer priority;

    public Node left;
    public Node right;

    public Node(Integer key, Integer priority) {
      this.key = key;
      this.priority = priority;
    }

    public Integer getKey() {
      return key;
    }

    public Integer getPriority() {
      return priority;
    }
  }

  public Node root;

  public Node createNode(Integer key, Integer priority) {
    return new Node(key, priority);
  }

  public void createTree(ArrayList<Node> nodeList) {
    this.root = this._createTree(nodeList);
  }

  private Node _createTree(ArrayList<Node> nodeList) {
    if (nodeList.size() == 0) return null;

    Comparator<Node> keyComparator = Comparator.comparing(Node::getKey);
    Collections.sort(nodeList, keyComparator);

    Integer divIdx = 0;
    Integer maxPriority = 0;

    for (int i = 0; i < nodeList.size(); i++) {
      Integer priority = nodeList.get(i).priority;

      if (priority > maxPriority) {
        maxPriority = priority;
        divIdx = i;
      }
    }

    ArrayList<Node> leftList = new ArrayList<>();
    for (int i = 0; i < divIdx; i++) {
      leftList.add(nodeList.get(i));
    }

    ArrayList<Node> rightList = new ArrayList<>();
    for (int i = divIdx + 1; i < nodeList.size(); i++) {
      rightList.add(nodeList.get(i));
    }

    Node subRoot = nodeList.get(divIdx);

    Node leftChild = _createTree(leftList);
    Node rightChild = _createTree(rightList);

    subRoot.left = leftChild;
    subRoot.right = rightChild;

    return nodeList.get(divIdx);
  }

}
