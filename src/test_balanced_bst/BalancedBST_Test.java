package test_balanced_bst;

import static org.junit.Assert.*;

import org.junit.Test;

import balanced_bst.*;

public class BalancedBST_Test {

  @Test
  public void test_1() {
    BalancedBST bst = new BalancedBST();

    int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

    bst.GenerateTree(data);

    BSTNode root = bst.Root;
    assertEquals(8, root.NodeKey);
    assertEquals(0, root.Level);

    BSTNode node4 = root.LeftChild;
    assertEquals(4, node4.NodeKey);
    assertEquals(1, node4.Level);
    assertEquals(root, node4.Parent);

    BSTNode node12 = root.RightChild;
    assertEquals(12, node12.NodeKey);
    assertEquals(1, node12.Level);
    assertEquals(root, node12.Parent);

    BSTNode node2 = node4.LeftChild;
    assertEquals(2, node2.NodeKey);
    assertEquals(2, node2.Level);
    assertEquals(node4, node2.Parent);

    BSTNode node6 = node4.RightChild;
    assertEquals(6, node6.NodeKey);
    assertEquals(2, node6.Level);
    assertEquals(node4, node6.Parent);

    BSTNode node1 = node2.LeftChild;
    assertEquals(1, node1.NodeKey);
    assertEquals(3, node1.Level);
    assertEquals(node2, node1.Parent);

    BSTNode node3 = node2.RightChild;
    assertEquals(3, node3.NodeKey);
    assertEquals(3, node3.Level);
    assertEquals(node2, node3.Parent);

    BSTNode node5 = node6.LeftChild;
    assertEquals(5, node5.NodeKey);
    assertEquals(3, node5.Level);
    assertEquals(node6, node5.Parent);

    BSTNode node7 = node6.RightChild;
    assertEquals(7, node7.NodeKey);
    assertEquals(3, node7.Level);
    assertEquals(node6, node7.Parent);

    BSTNode node10 = node12.LeftChild;
    assertEquals(10, node10.NodeKey);
    assertEquals(2, node10.Level);
    assertEquals(node12, node10.Parent);

    BSTNode node14 = node12.RightChild;
    assertEquals(14, node14.NodeKey);
    assertEquals(2, node14.Level);
    assertEquals(node12, node14.Parent);

    BSTNode node9 = node10.LeftChild;
    assertEquals(9, node9.NodeKey);
    assertEquals(3, node9.Level);
    assertEquals(node10, node9.Parent);

    BSTNode node11 = node10.RightChild;
    assertEquals(11, node11.NodeKey);
    assertEquals(3, node11.Level);
    assertEquals(node10, node11.Parent);
  }

  @Test
  public void test_2() {
    BSTNode root = new BSTNode(0, null);
    root.Level = 0;

    BSTNode node2 = new BSTNode(2, root);
    node2.Level = 1;
    root.LeftChild = node2;

    BSTNode node6 = new BSTNode(6, root);
    node6.Level = 1;
    root.RightChild = node6;

    BSTNode node1 = new BSTNode(1, node2);
    node1.Level = 2;
    node2.LeftChild = node1;

    BSTNode node3 = new BSTNode(3, node1);
    node3.Level = 3;
    node1.RightChild = node3;

    BalancedBST bst = new BalancedBST();

    assertEquals(false, bst.IsBalanced(root));

    node1.RightChild = null;

    node2.RightChild = node3;
    node3.Parent = node2;
    node3.Level = 1;

    assertEquals(true, bst.IsBalanced(root));

    BSTNode node7 = new BSTNode(7, node6);
    node7.Level = 2;
    node6.RightChild = node7;

    assertEquals(true, bst.IsBalanced(root));

    BSTNode node8 = new BSTNode(8, node7);
    node8.Level = 3;
    node7.RightChild = node8;

    assertEquals(false, bst.IsBalanced(root));
  }
}
