import bracket_balance.BracketBalance;

import postfix_sum.*;

import java.util.ArrayList;

import java.util.*;

import recursion.*;

import build_tree.*;

import intTree.*;

import splay_tree.*;

public class Main {

  public static void main(String[] args) {
    IntTree intTree = new IntTree(0, 4);

    IntervalTreeNode root = intTree.root;

    SplayTree splayTree = new SplayTree();

    SplayTree.Node node = splayTree.createNode();


    // System.out.println(intTree.root.start);
    // System.out.println(intTree.root.end);

    // System.out.println(root.left.start);
    // System.out.println(root.left.end);


    // System.out.println(root.left.left.start);
    // System.out.println(root.left.left.right);

    // System.out.println(root.left.right.left);
    // System.out.println(root.left.right.right);

    IntervalTreeNode findNode = intTree.findValue(1);
    System.out.println(findNode.end);
    System.out.println(findNode.start);

  }
}
