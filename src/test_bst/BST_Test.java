package test_bst;

import org.junit.Assert.*;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import bst.*;

public class BST_Test {

  @Test
  public void test_1() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    BSTFind<Integer> bstFind = bst.FindNodeByKey(3);

    assertEquals(node3, bstFind.Node);
    assertEquals(true, bstFind.NodeHasKey);

    bstFind = bst.FindNodeByKey(1);

    assertEquals(node1, bstFind.Node);
    assertEquals(true, bstFind.NodeHasKey);

    bstFind = bst.FindNodeByKey(6);
    assertEquals(node6, bstFind.Node);
    assertEquals(true, bstFind.NodeHasKey);

    bstFind = bst.FindNodeByKey(7);
    assertEquals(node7, bstFind.Node);
    assertEquals(true, bstFind.NodeHasKey);

    bstFind = bst.FindNodeByKey(8);
    assertEquals(node7, bstFind.Node);
    assertEquals(false, bstFind.NodeHasKey);
    assertEquals(false, bstFind.ToLeft);

    bstFind = bst.FindNodeByKey(0);
    assertEquals(node1, bstFind.Node);
    assertEquals(false, bstFind.NodeHasKey);
    assertEquals(true, bstFind.ToLeft);
  }

  @Test
  public void test_2() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);
    BSTNode<Integer> node10 = new BSTNode<Integer>(10, 10, node6);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node6);

    node6.RightChild = node10;
    node6.LeftChild = node3;

    root.RightChild = node6;

    BST<Integer> bst = new BST<Integer>(root);

    BSTFind<Integer> bstFind = bst.FindNodeByKey(7);
    assertEquals(node10, bstFind.Node);
    assertEquals(false, bstFind.NodeHasKey);
    assertEquals(true, bstFind.ToLeft);

    bstFind = bst.FindNodeByKey(11);
    assertEquals(node10, bstFind.Node);
    assertEquals(false, bstFind.NodeHasKey);
    assertEquals(false, bstFind.ToLeft);

    bstFind = bst.FindNodeByKey(2);
    assertEquals(root, bstFind.Node);
    assertEquals(false, bstFind.NodeHasKey);
    assertEquals(true, bstFind.ToLeft);

    bstFind = bst.FindNodeByKey(4);
    assertEquals(root, bstFind.Node);
    assertEquals(true, bstFind.NodeHasKey);

    root = new BSTNode<Integer>(4, 4, null);
    bst = new BST<Integer>(root);

    bstFind = bst.FindNodeByKey(6);
    assertEquals(root, bstFind.Node);
    assertEquals(false, bstFind.NodeHasKey);
    assertEquals(false, bstFind.ToLeft);

    bst = new BST<Integer>(null);
    bstFind = bst.FindNodeByKey(6);
    assertEquals(null, bstFind.Node);
    assertEquals(false, bstFind.NodeHasKey);
    assertEquals(false, bstFind.ToLeft);
  }

  @Test
  public void test_3() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    boolean isAdded = bst.AddKeyValue(6, 6);
    assertEquals(isAdded, false);

    isAdded = bst.AddKeyValue(7, 7);
    assertEquals(isAdded, false);

    isAdded = bst.AddKeyValue(1, 1);
    assertEquals(isAdded, false);

    isAdded = bst.AddKeyValue(8, 8);
    assertEquals(isAdded, true);
    assertEquals(8, node7.RightChild.NodeKey);

    isAdded = bst.AddKeyValue(0, 0);
    assertEquals(isAdded, true);
    assertEquals(0, node1.LeftChild.NodeKey);

    BSTFind<Integer> bstFind = bst.FindNodeByKey(8);
    assertEquals(node7.RightChild, bstFind.Node);
    assertEquals(true, bstFind.NodeHasKey);

    bstFind = bst.FindNodeByKey(0);
    assertEquals(node1.LeftChild, bstFind.Node);
    assertEquals(true, bstFind.NodeHasKey);

    bst = new BST<Integer>(null);
    isAdded = bst.AddKeyValue(0, 0);
    assertEquals(true, isAdded);
    bstFind = bst.FindNodeByKey(0);
    assertEquals(0, bstFind.Node.NodeKey);
    assertEquals(true, bstFind.NodeHasKey);
  }

  @Test
  public void test_4() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    BSTNode<Integer> maxNode = bst.FinMinMax(root, true);

    assertEquals(7, maxNode.NodeKey);

    BSTNode<Integer> minNode = bst.FinMinMax(root, false);

    assertEquals(1, minNode.NodeKey);

    maxNode = bst.FinMinMax(node2, true);

    assertEquals(3, maxNode.NodeKey);

    minNode = bst.FinMinMax(node2, false);

    assertEquals(1, minNode.NodeKey);

    maxNode = bst.FinMinMax(node6, true);

    assertEquals(7, maxNode.NodeKey);

    minNode = bst.FinMinMax(node6, false);

    assertEquals(5, minNode.NodeKey);
  }

  @Test
  public void test_5() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    int countOfAllNodes = bst.Count();

    assertEquals(7, countOfAllNodes);

    bst = new BST<Integer>(null);

    assertEquals(0, bst.Count());

    root = new BSTNode<Integer>(4, 4, null);

    bst = new BST<Integer>(root);

    assertEquals(1, bst.Count());
  }

  @Test
  public void test_6() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    bst.DeleteNodeByKey(4);

    assertEquals(null, root.LeftChild);
    assertEquals(null, root.RightChild);

    assertEquals(node2, node5.LeftChild);
    assertEquals(node6, node5.RightChild);
    assertEquals(null, node5.Parent);
    assertEquals(null, node6.LeftChild);
    assertEquals(node5, node6.Parent);
    assertEquals(node5, node2.Parent);
    assertEquals(6, bst.Count());
    assertEquals(node7, node6.RightChild);
  }

  @Test
  public void test_7() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    bst.DeleteNodeByKey(4);

    assertEquals(null, root.LeftChild);
    assertEquals(null, root.RightChild);

    assertEquals(null, node6.Parent);
    assertEquals(node2, node6.LeftChild);
    assertEquals(node7, node6.RightChild);
    assertEquals(node6, node7.Parent);
    assertEquals(node6, node2.Parent);
    assertEquals(5, bst.Count());

    bst.DeleteNodeByKey(7);

    assertEquals(null, node6.Parent);
    assertEquals(null, node6.RightChild);
    assertEquals(node2, node6.LeftChild);
    assertEquals(null, node7.Parent);
    assertEquals(4, bst.Count());

    bst.DeleteNodeByKey(6);

    assertEquals(null, node6.Parent);
    assertEquals(null, node6.LeftChild);
    assertEquals(null, node6.RightChild);

    assertEquals(null, node2.Parent);

    assertEquals(node1, node2.LeftChild);
    assertEquals(node3, node2.RightChild);

    assertEquals(node2, node1.Parent);
    assertEquals(node2, node3.Parent);

    assertEquals(3, bst.Count());

    bst.DeleteNodeByKey(2);

    assertEquals(null, node2.Parent);
    assertEquals(null, node2.LeftChild);
    assertEquals(null, node2.RightChild);

    assertEquals(null, node3.RightChild);
    assertEquals(null, node3.Parent);
    assertEquals(node1, node3.LeftChild);
    assertEquals(node3, node1.Parent);

    bst.DeleteNodeByKey(3);

    assertEquals(null, node3.Parent);
    assertEquals(null, node3.LeftChild);
    assertEquals(null, node3.RightChild);

    assertEquals(null, node1.Parent);
    assertEquals(null, node1.LeftChild);
    assertEquals(null, node1.RightChild);

    assertEquals(1, bst.Count());

    bst.DeleteNodeByKey(1);

    assertEquals(null, node1.Parent);
    assertEquals(null, node1.LeftChild);
    assertEquals(null, node1.RightChild);

    assertEquals(0, bst.Count());

    boolean isDeleted = bst.DeleteNodeByKey(1);

    assertEquals(isDeleted, false);
  }

  @Test
  public void test_8() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    ArrayList<BSTNode> list = bst.WideAllNodes();

    assertEquals(7, list.size());

    assertEquals(4, list.get(0).NodeKey);
    assertEquals(2, list.get(1).NodeKey);
    assertEquals(6, list.get(2).NodeKey);
    assertEquals(1, list.get(3).NodeKey);
    assertEquals(3, list.get(4).NodeKey);
    assertEquals(5, list.get(5).NodeKey);
    assertEquals(7, list.get(6).NodeKey);
  }

  @Test
  public void test_9() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    ArrayList<BSTNode> inorderList = bst.DeepAllNodes(0);

    assertEquals(7, inorderList.size());

    for (int i = 0; i < inorderList.size() - 1; i++) {
      assertEquals(true, inorderList.get(i).NodeKey < inorderList.get(i + 1).NodeKey);
    }
  }

  @Test
  public void test_10() {
    BSTNode<Integer> root = new BSTNode<Integer>(4, 4, null);

    BSTNode<Integer> node2 = new BSTNode<Integer>(2, 2, root);
    BSTNode<Integer> node6 = new BSTNode<Integer>(6, 6, root);

    root.LeftChild = node2;
    root.RightChild = node6;

    BSTNode<Integer> node1 = new BSTNode<Integer>(1, 1, node2);
    BSTNode<Integer> node3 = new BSTNode<Integer>(3, 3, node2);

    node2.LeftChild = node1;
    node2.RightChild = node3;

    BSTNode<Integer> node5 = new BSTNode<Integer>(5, 5, node6);
    BSTNode<Integer> node7 = new BSTNode<Integer>(7, 7, node6);

    node6.LeftChild = node5;
    node6.RightChild = node7;

    BST<Integer> bst = new BST<Integer>(root);

    ArrayList<BSTNode> postOrderList = bst.DeepAllNodes(1);

    assertEquals(1, postOrderList.get(0).NodeKey);
    assertEquals(3, postOrderList.get(1).NodeKey);
    assertEquals(2, postOrderList.get(2).NodeKey);
    assertEquals(5, postOrderList.get(3).NodeKey);
    assertEquals(7, postOrderList.get(4).NodeKey);
    assertEquals(6, postOrderList.get(5).NodeKey);
    assertEquals(4, postOrderList.get(6).NodeKey);

  }
}
