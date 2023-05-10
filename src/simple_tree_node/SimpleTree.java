package simple_tree_node;

import java.util.*;

class TreeEvenParams<T> {
  int childCount;
  ArrayList<T> EvenTrees;

  public TreeEvenParams(int count, ArrayList<T> nodes) {
    childCount = count;
    EvenTrees = nodes;
  }
}

public class SimpleTree<T> {
  public SimpleTreeNode<T> Root; // корень, может быть null

  public SimpleTree(SimpleTreeNode<T> root) {
    Root = root;
  }

  public ArrayList<T> EvenTrees() {
    TreeEvenParams<T> treeEvenParams = exploreTreeEvenParams(Root);

    if ((treeEvenParams.childCount + 1) % 2 == 0)
      return treeEvenParams.EvenTrees;

    return new ArrayList<>();
  }

  TreeEvenParams<T> exploreTreeEvenParams(SimpleTreeNode<T> node) {
    TreeEvenParams<T> treeEvenParams = new TreeEvenParams<T>(0, new ArrayList<T>());

    if (node.Children == null)
      return treeEvenParams;

    int childCount = 0;

    ArrayList<T> EvenTrees = new ArrayList<T>();

    for (int i = 0; i < node.Children.size(); i++) {
      childCount += 1;
      SimpleTreeNode<T> childNode = node.Children.get(i);

      TreeEvenParams<T> subTreeEvenParams = exploreTreeEvenParams(childNode);

      childCount += subTreeEvenParams.childCount;

      if ((subTreeEvenParams.childCount + 1) % 2 == 0 && subTreeEvenParams.childCount != 0) {
        EvenTrees.add(node.NodeValue);
        EvenTrees.add(childNode.NodeValue);
      }

      EvenTrees.addAll(subTreeEvenParams.EvenTrees);
    }

    treeEvenParams.childCount = childCount;
    treeEvenParams.EvenTrees = EvenTrees;

    return treeEvenParams;
  }

  public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
    if (ParentNode == NewChild)
      return;

    NewChild.Parent = ParentNode;

    if (ParentNode.Children == null) {
      ParentNode.Children = new ArrayList<SimpleTreeNode<T>>();
    }

    ParentNode.Children.add(NewChild);
    return;
  }

  public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
    if (Root == NodeToDelete)
      return;

    SimpleTreeNode<T> parentOfDeleteNode = NodeToDelete.Parent;

    parentOfDeleteNode.Children.remove(NodeToDelete);

    if (parentOfDeleteNode.Children.size() == 0)
      parentOfDeleteNode.Children = null;

    NodeToDelete.Parent = null;
  }

  public List<SimpleTreeNode<T>> GetAllNodes() {
    return GetAllNodes(Root);
  }

  List<SimpleTreeNode<T>> GetAllNodes(SimpleTreeNode<T> node) {
    List<SimpleTreeNode<T>> list = new ArrayList<SimpleTreeNode<T>>();

    list.add(node);

    if (node.Children == null)
      return list;

    for (int i = 0; i < node.Children.size(); i++) {
      SimpleTreeNode<T> childNode = node.Children.get(i);
      List<SimpleTreeNode<T>> childNodeList = GetAllNodes(childNode);
      list.addAll(childNodeList);
    }

    return list;
  }

  public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
    return FindNodesByValue(Root, val);
  }

  List<SimpleTreeNode<T>> FindNodesByValue(SimpleTreeNode<T> node, T val) {
    List<SimpleTreeNode<T>> list = new ArrayList<SimpleTreeNode<T>>();

    if (node.NodeValue == val)
      list.add(node);

    if (node.Children == null)
      return list;

    for (int i = 0; i < node.Children.size(); i++) {
      SimpleTreeNode<T> childNode = node.Children.get(i);
      List<SimpleTreeNode<T>> childNodeList = FindNodesByValue(childNode, val);
      list.addAll(childNodeList);
    }

    return list;
  }

  public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
    DeleteNode(OriginalNode);
    AddChild(NewParent, OriginalNode);
  }

  public int Count() {
    return Count(Root);
  }

  int Count(SimpleTreeNode<T> node) {
    int countOfAllNodes = 0;

    countOfAllNodes += 1;

    if (node.Children == null)
      return countOfAllNodes;

    for (int i = 0; i < node.Children.size(); i++) {
      SimpleTreeNode<T> childNode = node.Children.get(i);
      int countOfChildren = Count(childNode);
      countOfAllNodes += countOfChildren;
    }

    return countOfAllNodes;
  }

  public int LeafCount() {
    return LeafCount(Root);
  }

  int LeafCount(SimpleTreeNode<T> node) {
    int countOfAllLeaf = 0;

    if (node.Children == null)
      return 1;

    for (int i = 0; i < node.Children.size(); i++) {
      SimpleTreeNode<T> childNode = node.Children.get(i);
      int childrenLiafCount = LeafCount(childNode);
      countOfAllLeaf += childrenLiafCount;
    }

    return countOfAllLeaf;
  }
}