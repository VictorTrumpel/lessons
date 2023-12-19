package dsu;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

public class Dsu {

  public Set<Node> setTree = new HashSet<>();
  
  public void makeSet(Node node) {
    setTree.add(node);
  }

  public Node find(Node node) {
    return findRoot(node, new ArrayList<Node>());
  }

  private Node findRoot(Node node, ArrayList<Node> visitedNodes) {
    if (node.parent != null) {
      visitedNodes.add(node);
      return findRoot(node.parent, visitedNodes);
    }
    
    for (int i = 0; i < visitedNodes.size(); i++) {
      Node visitedNode = visitedNodes.get(i);
      visitedNode.parent = node;
    }

    return node;
  }

  public void unite(Node node1, Node node2) {
    Node tree1 = find(node1);
    Node tree2 = find(node2);

    if (tree1 == tree2) return;

    Node root = getRandom(tree1, tree2);

    if (root != tree1) {
      tree1.parent = root;
      setTree.remove(tree1);
    }

    if (root != tree2) {
      tree2.parent = root;
      setTree.remove(tree2);
    }
  }

  private Node getRandom(Node node1, Node node2) {
    Random random = new Random();
    int val = random.nextInt(0, 2);

    if (val == 0) return node1;
    return node2;
  }
}