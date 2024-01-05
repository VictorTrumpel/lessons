import java.util.Arrays;
import java.util.Comparator;

class RangeTree {

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class Node {
    Point point;
    Node left, right;

    public Node(Point point) {
      this.point = point;
    }
  }

  private Node root;

  public RangeTree(Point[] points) {
    Arrays.sort(points, Comparator.comparingInt(p -> p.x));
    this.root = buildRangeTree(points, 0, points.length - 1, true);
  }

  private Node buildRangeTree(Point[] points, int start, int end, boolean byX) {
    if (start == end) {
      return new Node(points[start]);
    }

    int mid = (start + end) / 2;
    Node left = buildRangeTree(points, start, mid, !byX);
    Node right = buildRangeTree(points, mid + 1, end, !byX);

    if (byX) {
      Arrays.sort(points, start, end + 1, Comparator.comparingInt(p -> p.y));
    } else {
      Arrays.sort(points, start, end + 1, Comparator.comparingInt(p -> p.x));
    }

    Node node = new Node(points[mid]);
    node.left = left;
    node.right = right;
    return node;
  }

  public void queryRange(int x1, int y1, int x2, int y2) {
    queryRange(root, x1, y1, x2, y2, true);
  }

  private void queryRange(Node node, int x1, int y1, int x2, int y2, boolean byX) {
    if (node == null) {
      return;
    }

    Point point = node.point;

    if (x1 <= point.x && point.x <= x2 && y1 <= point.y && point.y <= y2) {
      System.out.println("(" + point.x + ", " + point.y + ")");
    }

    if (byX) {
      if (point.x > x1) {
        queryRange(node.left, x1, y1, x2, y2, !byX);
      }
      if (point.x < x2) {
        queryRange(node.right, x1, y1, x2, y2, !byX);
      }
    } else {
      if (point.y > y1) {
        queryRange(node.left, x1, y1, x2, y2, !byX);
      }
      if (point.y < y2) {
        queryRange(node.right, x1, y1, x2, y2, !byX);
      }
    }
  }
}