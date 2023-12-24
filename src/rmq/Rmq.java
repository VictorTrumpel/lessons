package rmq;

import java.util.ArrayDeque;

public class Rmq {
  int[] segment;

  IFunctor functor;

  public Node root;

  public Rmq(int[] segment, IFunctor functor) {
    this.segment = segment;
    this.functor = functor;
  }

  public void createFromTopToDown() {
    Node root = this.createFromTopToDown(new Node(this.segment), this.segment);
    this.root = root;
  }

  public void createFromBottomToTop() {
    ArrayDeque<Node> queue = new ArrayDeque<>();

    for (int i = 0; i < this.segment.length; i++) {
      int[] subSegment = new int[1];
      subSegment[0] = segment[i];

      Node node = new Node(subSegment);
      node.value = functor.calculate(subSegment);

      queue.push(node);
    }

    while (queue.size() != 1) {
      Node leftChild = null;
      Node rigChild = null;

      if (queue.size() > 0 ) leftChild = queue.pop();
      if (queue.size() > 0 ) rigChild = queue.pop();

      int[] leftSegment = leftChild == null ? new int[0] : leftChild.segment;
      int[] rightSegment = leftChild == null ? new int[0] : rigChild.segment;

      int[] unionSegment = new int[leftSegment.length + rightSegment.length];

      for (int i = 0; i < leftSegment.length; i++) {
        unionSegment[i] = leftSegment[i];
      }
      for (int i = 0; i < rightSegment.length; i++) {
        unionSegment[i + leftSegment.length] = rightSegment[i];
      }

      Node parentNode = new Node(unionSegment);
      parentNode.value = functor.calculate(unionSegment);

      if (leftChild != null) {
        leftChild.parent = parentNode;
        parentNode.children.add(leftChild);
      }

      if (rigChild != null) {
        rigChild.parent = parentNode;
        parentNode.children.add(rigChild);
      }

      queue.push(parentNode);
    }

    this.root = queue.pop();
  }

  private Node createFromTopToDown(Node parent, int[] segment) {
    Node node = new Node(segment);
    node.value = functor.calculate(segment);
    node.parent = parent;

    int segmentLength = segment.length;

    if (segmentLength == 1) return node;

    int leftSegmentLength = (int) Math.floor(segmentLength / 2);
    int rightSegmentLength = segmentLength - (int) Math.floor(segmentLength / 2);

    int[] leftSegment = new int[leftSegmentLength];
    int[] rightSegment = new int[rightSegmentLength];

    for (int i = 0; i < segmentLength; i++) {
      if (i < leftSegmentLength) {
        leftSegment[i] = segment[i];
        continue;
      }

      int rightIdx = i - leftSegmentLength;

      rightSegment[rightIdx] = segment[i];
    }

    Node leftChildNode = createFromTopToDown(node, leftSegment);
    Node rightChildNode = createFromTopToDown(node, rightSegment);

    node.children.add(leftChildNode);
    node.children.add(rightChildNode);

    return node;
  } 
}

