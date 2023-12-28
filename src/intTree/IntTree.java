package intTree;

public class IntTree {
  public IntervalTreeNode root;

  public IntTree(int start, int end) {
    this.root = buildIntervalTree(start, end);
  }

  IntervalTreeNode buildIntervalTree(int start, int end) {
    if (start > end) return null;

    IntervalTreeNode node = new IntervalTreeNode(start, end);

    if (start < end) {
      int mid = (start + end) / 2;
      node.left = buildIntervalTree(start, mid);
      node.right = buildIntervalTree(mid + 1, end);
    }

    return node;
  }

  public IntervalTreeNode findValue(int value) {
    return this.findValue(this.root, value);
  } 

  IntervalTreeNode findValue(IntervalTreeNode node, int value) {
    if (node == null) return null;

    int start = node.start;
    int end  = node.end;

    boolean isInInterval = value >= start || value <= end;

    if (isInInterval) {
      return node;
    }

    if (value < start) {
      return findValue(node.left, value);
    }

    if (value > start) {
      return findValue(node.right, value);
    }

    return null;
  }
}
