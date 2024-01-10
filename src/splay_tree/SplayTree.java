package splay_tree;

import java.util.*;

public class SplayTree {
  public class Node {
    Integer value;
    Node left;
    Node right;
    Node parent;

    public Node(Integer value) {
      this.value = value;
    }
  }

  Node root;

  public Node createNode(Integer value) {
    return new Node(value);
  }

  private void zig(Node node) {
    Node parent = node.parent;

    if (parent.left == node) {
      node.parent = parent.parent;
      parent.left = node.right;
      parent.parent = node;
      node.right = parent;
    }

    if (parent.right == node) {
      node.parent = parent.parent;
      parent.right = node.left;
      node.left = parent;
      parent.parent = node;
    }

    if (node.parent == null) {
      root = node;
    }
  }

  private void zigZig(Node node) {
    zig(node.parent);
    zig(node);
  }

  private void zigZag(Node node) {
    zig(node);
    zig(node);
  }

  public void splay(Node node) {
    if (node.parent == null) return;
    
    Node parent = node.parent;
    Node grandParent = parent.parent;

    if (parent.left == node && grandParent.left == parent) {
      zigZig(node);
      return;
    }

    if (parent.right == node && grandParent.right == parent) {
      zigZig(node);
      return;
    }

    zigZag(node);

    splay(node);
  }

  public void findNode(Integer value) {
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);

    while (queue.size() > 0) {
      Node currentNode = queue.poll();

      if (currentNode.value == value) {
        splay(currentNode);
        return;
      }

      if (currentNode.left != null)
        queue.add(currentNode.left);
      if (currentNode.right != null)
        queue.add(currentNode.right);
    }
  }

  public void addNode(Node node) {
    if (root == null) {
      root = node;
      return;
    }

    addNode(node, root);
  }

  public void merge(Node tree1, Node tree2) {
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(tree1);

    Node maxRoot = null;

    while (queue.size() > 0) {
      Node currentNode = queue.poll();

      if (currentNode.right == null) {
        maxRoot = currentNode;
        break;
      }
      
      queue.add(currentNode.right);
    }

    splay(maxRoot);

    tree1.right = tree2;
  }

  private void addNode(Node node, Node currentNode) {
    if (currentNode.value >= node.value) {
      if (currentNode.right == null) {
        currentNode.right = node;
        splay(node);
        return;
      }
      addNode(node, currentNode.right);
    }

    if (currentNode.value < node.value) {
      if (currentNode.left == null) {
        currentNode.left = node;
        splay(node);
        return;
      }
      addNode(node, currentNode.left);
    }
  }

  public void deleteNode(Node node) {
    splay(node);

    if (node.left == null && node.right != null) {
      root = node.right;
      node.right.parent = null;
      return;
    } 

    if (node.left != null && node.right == null) {
      root = node.left;
      node.left.parent = null;
      return;
    } 

    if (node.left == null && node.right == null) {
      root = null;
    }

    if (node.left.value >= node.right.value) {
      root = node.left;
      node.left.parent = null;
      root.right = node.right;
      node.right.parent = root;
      return;
    }

    root = node.right;
    node.right.parent = null;
    root.left = node.left;
    node.left.parent = root;
    return;
  }
}

