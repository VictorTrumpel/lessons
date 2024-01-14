package binomial_tree;

class Node {
  int order;
  int key;  
  Node child;
  Node sibling;

  public Node(int key) {
    this.key = key;
    this.order = 0;
    this.child = null;
    this.sibling = null;
  }
}

class BinomialTree {
  Node head; 

  public BinomialTree() {
    this.head = null;
  }

  public void insert(int key) {
    Node newTree = new Node(key);

    if (head == null) {
      head = newTree;
    } else {
      head = mergeTrees(head, newTree);
    }
  }

  private Node mergeTrees(Node tree1, Node tree2) {
    if (tree1.order != tree2.order) {
      throw new IllegalArgumentException("Order mismatch");
    }

    if (tree1.key < tree2.key) {
      tree2.sibling = tree1.child;
      tree1.child = tree2;
      tree1.order++;
      return tree1;
    } else {
      tree1.sibling = tree2.child;
      tree2.child = tree1;
      tree2.order++;
      return tree2;
    }
  }
}
