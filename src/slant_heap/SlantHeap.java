package slant_heap;

public class SlantHeap {
  public class Node {
    public Integer value;
    public Node left;
    public Node right;
  }

  public void merge(Node k1, Node k2) {
    if (k1 == null || k2 == null) {
      return;
    }

    Node leftChild = k1.left;
    Node rightChild = k1.right;

    k1.left = rightChild;
    k1.right = leftChild;

    merge(k2, k1.left);
  }
}
