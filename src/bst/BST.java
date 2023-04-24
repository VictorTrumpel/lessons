package bst;

import java.io.*;
import java.util.*;

public class BST<T> {
  BSTNode<T> Root; // корень дерева, или null

  public BST(BSTNode<T> node) {
    Root = node;
  }

  public BSTFind<T> FindNodeByKey(int key) {
    if (Root == null) {
      BSTFind<T> bstFind = new BSTFind<T>();
      bstFind.Node = null;
      bstFind.NodeHasKey = false;
      return bstFind;
    }
    return FindNodeByKey(key, Root);
  }

  BSTFind<T> FindNodeByKey(int key, BSTNode<T> currNode) {
    if (key == currNode.NodeKey) {
      BSTFind<T> bstFind = new BSTFind<T>();
      bstFind.Node = currNode;
      bstFind.NodeHasKey = true;
      return bstFind;
    }

    if (key < currNode.NodeKey) {
      BSTNode<T> leftChild = currNode.LeftChild;

      if (leftChild == null) {
        BSTFind<T> bstFind = new BSTFind<T>();
        bstFind.Node = currNode;
        bstFind.NodeHasKey = false;
        bstFind.ToLeft = true;

        return bstFind;
      }

      return FindNodeByKey(key, leftChild);
    }

    BSTNode<T> rightChild = currNode.RightChild;

    if (rightChild == null) {
      BSTFind<T> bstFind = new BSTFind<T>();
      bstFind.Node = currNode;
      bstFind.NodeHasKey = false;
      bstFind.ToLeft = false;

      return bstFind;
    }

    return FindNodeByKey(key, rightChild);
  }

  public boolean AddKeyValue(int key, T val) {
    if (Root == null) {
      Root = new BSTNode<T>(key, val, null);
      return true;
    }

    BSTFind<T> bstFind = FindNodeByKey(key, Root);

    if (bstFind.NodeHasKey)
      return false;

    if (bstFind.ToLeft) {
      bstFind.Node.LeftChild = new BSTNode<T>(key, val, bstFind.Node);
    } else {
      bstFind.Node.RightChild = new BSTNode<T>(key, val, bstFind.Node);
    }

    return true;
  }

  public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
    if (FindMax && FromNode.RightChild == null) {
      return FromNode;
    }

    if (!FindMax && FromNode.LeftChild == null) {
      return FromNode;
    }

    if (FindMax) {
      return FinMinMax(FromNode.RightChild, FindMax);
    }

    return FinMinMax(FromNode.LeftChild, FindMax);
  }

  public boolean DeleteNodeByKey(int key) {
    // удаляем узел по ключу
    return false; // если узел не найден
  }

  public int Count() {
    return 0; // количество узлов в дереве
  }

}