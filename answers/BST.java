class BSTNode<T> {
  public int NodeKey; // ключ узла
  public T NodeValue; // значение в узле
  public BSTNode<T> Parent; // родитель или null для корня
  public BSTNode<T> LeftChild; // левый потомок
  public BSTNode<T> RightChild; // правый потомок

  public BSTNode(int key, T val, BSTNode<T> parent) {
    NodeKey = key;
    NodeValue = val;
    Parent = parent;
    LeftChild = null;
    RightChild = null;
  }
}

class BSTFind<T> {
  public BSTNode<T> Node;

  public boolean NodeHasKey;

  public boolean ToLeft;

  public BSTFind() {
    Node = null;
  }
}

class BST<T> {
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

  public ArrayList<BSTNode> WideAllNodes() {
    ArrayList<BSTNode> allNodes = new ArrayList<BSTNode>();
    ArrayList<BSTNode> queue = new ArrayList<BSTNode>();

    queue.add(Root);

    while (queue.size() > 0) {
      BSTNode node = queue.remove(0);
      allNodes.add(node);

      if (node.LeftChild != null)
        queue.add(node.LeftChild);
      if (node.RightChild != null)
        queue.add(node.RightChild);
    }

    return allNodes;
  }

  public ArrayList<BSTNode> DeepAllNodes(int orderKey) {
    return DeepAllNodes(Root, orderKey);
  }

  ArrayList<BSTNode> DeepAllNodes(BSTNode currentNode, int orderKey) {
    ArrayList<BSTNode> list = new ArrayList<BSTNode>();

    if (currentNode == null) {
      return list;
    }

    if (orderKey == 0) {
      ArrayList<BSTNode> leftList = DeepAllNodes(currentNode.LeftChild, orderKey);
      ArrayList<BSTNode> rightList = DeepAllNodes(currentNode.RightChild, orderKey);

      list.addAll(leftList);
      list.add(currentNode);
      list.addAll(rightList);
    }

    if (orderKey == 1) {
      ArrayList<BSTNode> leftList = DeepAllNodes(currentNode.LeftChild, orderKey);
      ArrayList<BSTNode> rightList = DeepAllNodes(currentNode.RightChild, orderKey);

      list.addAll(leftList);
      list.addAll(rightList);
      list.add(currentNode);
    }

    if (orderKey == 2) {
      list.add(currentNode);

      ArrayList<BSTNode> leftList = DeepAllNodes(currentNode.LeftChild, orderKey);
      ArrayList<BSTNode> rightList = DeepAllNodes(currentNode.RightChild, orderKey);

      list.addAll(leftList);
      list.addAll(rightList);
    }

    return list;
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
    if (FindMax && FromNode.RightChild == null)
      return FromNode;

    if (!FindMax && FromNode.LeftChild == null)
      return FromNode;

    if (FindMax)
      return FinMinMax(FromNode.RightChild, FindMax);

    return FinMinMax(FromNode.LeftChild, FindMax);
  }

  public boolean DeleteNodeByKey(int key) {
    if (Root == null)
      return false;

    BSTFind<T> bstFind = FindNodeByKey(key, Root);

    if (!bstFind.NodeHasKey)
      return false;

    if (bstFind.Node == Root &&
        bstFind.Node.LeftChild == null &&
        bstFind.Node.RightChild == null) {
      Root = null;
      return true;
    }

    if (bstFind.Node.LeftChild != null && bstFind.Node.RightChild == null) {
      squeezeNode(bstFind.Node, bstFind.Node.LeftChild);
      return true;
    }

    BSTNode<T> heirNode = findHeirNode(bstFind.Node);

    if (heirNode == null) {
      if (bstFind.Node == bstFind.Node.Parent.RightChild)
        bstFind.Node.Parent.RightChild = null;

      if (bstFind.Node == bstFind.Node.Parent.LeftChild)
        bstFind.Node.Parent.LeftChild = null;

      bstFind.Node.Parent = null;

      return true;
    }

    if (heirNode.RightChild != null) {
      squeezeNode(heirNode, heirNode.RightChild);
    }

    squeezeNode(bstFind.Node, heirNode);

    return true;
  }

  BSTNode<T> findHeirNode(BSTNode<T> parentNode) {
    if (parentNode.LeftChild == null && parentNode.RightChild == null)
      return null;

    if (parentNode.RightChild == null)
      return parentNode.LeftChild;

    return FinMinMax(parentNode.RightChild, false);
  }

  void squeezeNode(BSTNode<T> squeesedNode, BSTNode<T> nodePresser) {
    BSTNode<T> squeezedNodeParent = squeesedNode.Parent;

    if (nodePresser.Parent != null) {
      if (nodePresser.Parent.LeftChild == nodePresser)
        nodePresser.Parent.LeftChild = null;

      if (nodePresser.Parent.RightChild == nodePresser)
        nodePresser.Parent.RightChild = null;
    }

    if (squeezedNodeParent != null) {
      if (squeezedNodeParent.LeftChild == squeesedNode)
        squeezedNodeParent.LeftChild = nodePresser;
      if (squeezedNodeParent.RightChild == squeesedNode)
        squeezedNodeParent.RightChild = nodePresser;
    }

    if (squeesedNode.LeftChild != null)
      squeesedNode.LeftChild.Parent = nodePresser;
    if (squeesedNode.RightChild != null)
      squeesedNode.RightChild.Parent = nodePresser;

    nodePresser.Parent = squeesedNode.Parent;

    if (squeesedNode.LeftChild != null)
      nodePresser.LeftChild = squeesedNode.LeftChild;
    if (squeesedNode.RightChild != null)
      nodePresser.RightChild = squeesedNode.RightChild;

    if (squeesedNode.Parent == null) {
      Root = nodePresser;
    }

    squeesedNode.Parent = null;
    squeesedNode.LeftChild = null;
    squeesedNode.RightChild = null;
  }

  public int Count() {
    return Count(Root);
  }

  int Count(BSTNode<T> node) {
    int allNodesCount = 0;

    if (node == null)
      return allNodesCount;

    allNodesCount += 1;

    return allNodesCount + Count(node.LeftChild) + Count(node.RightChild);
  }
}