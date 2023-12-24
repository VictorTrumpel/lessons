package rmq;

import java.util.ArrayList;

public class Node {
  public Node parent;
  public ArrayList<Node> children = new ArrayList<>();

  public int[] segment;
  public int value;

  public Node(int[] segment) {
    this.segment = segment;
  }
}
