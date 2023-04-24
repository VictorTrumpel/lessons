package test_bst;

import org.junit.Assert.*;

import static org.junit.Assert.*;

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
}
