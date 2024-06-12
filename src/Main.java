import bracket_balance.BracketBalance;

import postfix_sum.*;

import java.util.ArrayList;

import java.util.*;

import recursion.*;

import build_tree.*;

import intTree.*;

import splay_tree.*;

import decart_tree.*;

class ParentNode {
  public Integer a = 1;
  public Integer b = 2;
}

class Node extends ParentNode {
  public void printA() {
    System.out.println(this.a);
  }
}

public class Main {

  public static void main(String[] args) {
    DecartTree tree = new DecartTree();

    DecartTree.Node node8_5 = tree.createNode(8, 5);
    DecartTree.Node node9_4 = tree.createNode(9, 4);
    DecartTree.Node node4_1 = tree.createNode(4, 1);
    DecartTree.Node node3_7 = tree.createNode(3, 7);

    ArrayList<DecartTree.Node> list = new ArrayList<>();
    list.add(node8_5);
    list.add(node9_4);
    list.add(node4_1);
    list.add(node3_7);

    Node node = new Node();
    node.printA();

    tree.createTree(list);

  }
}
