package intTree;

public class IntervalTreeNode {
  public int start;
  public int end;

  public IntervalTreeNode left;
  public IntervalTreeNode right;

  public IntervalTreeNode(int start, int end) {
      this.start = start;
      this.end = end;
      this.left = null;
      this.right = null;
  }
}
