package balanced_bst;

import java.util.*;

public class BalancedBST {
  public BSTNode Root; // корень дерева

  public BalancedBST() {
    Root = null;
  }

  public void GenerateTree(int[] a) {
    Arrays.sort(a);
    GenerateTree(a, null);
  }

  void GenerateTree(int[] nodes, BSTNode parentNode) {
    if (nodes.length == 0)
      return;

    int middleIdx;

    if (nodes.length == 1)
      middleIdx = 0;
    else
      middleIdx = ((Double) Math.floor(nodes.length / 2)).intValue();

    int nodeKey = nodes[middleIdx];

    BSTNode childNode;

    if (parentNode == null) {
      Root = new BSTNode(nodeKey, null);
      Root.Level = 0;
      childNode = Root;
    } else {
      childNode = new BSTNode(nodeKey, parentNode);
      childNode.Level = parentNode.Level + 1;

      if (childNode.NodeKey < parentNode.NodeKey)
        parentNode.LeftChild = childNode;
      else
        parentNode.RightChild = childNode;
    }

    if (nodes.length == 1)
      return;

    int[] leftNodes = Arrays.copyOfRange(nodes, 0, middleIdx);
    int[] rightNodes = Arrays.copyOfRange(nodes, middleIdx, nodes.length);

    GenerateTree(leftNodes, childNode);
    GenerateTree(rightNodes, childNode);
  }

  public boolean IsBalanced(BSTNode root_node) {
    return IsBalanced(root_node, null).isBalanced;
  }

  BalanceResult IsBalanced(BSTNode node, Integer d) {
    if (node == null)
      return new BalanceResult(true, -1);

    BalanceResult leftBalance = IsBalanced(node.LeftChild, null);
    BalanceResult rightBalance = IsBalanced(node.RightChild, null);

    int leftDepth = leftBalance.depth == -1 ? node.Level : leftBalance.depth;
    int rightDepth = rightBalance.depth == -1 ? node.Level : rightBalance.depth;

    boolean isDepthBalanced = Math.abs(leftDepth - rightDepth) <= 1;
    boolean isSubtreeBalanced = leftBalance.isBalanced && rightBalance.isBalanced;

    int height = Math.max(leftDepth, rightDepth);

    return new BalanceResult(isDepthBalanced && isSubtreeBalanced, height);
  }
}

class BalanceResult {
  boolean isBalanced;
  int depth;

  public BalanceResult(boolean isBalanced, int depth) {
    this.isBalanced = isBalanced;
    this.depth = depth;
  }
}