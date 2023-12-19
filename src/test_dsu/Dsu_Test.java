package test_dsu;

import static org.junit.Assert.*;

import org.junit.Test;

import dsu.*;

public class Dsu_Test {
  
  @Test
  public void test_1() {
    Dsu dsu = new Dsu();

    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);

    dsu.makeSet(node1);
    dsu.makeSet(node2);
    dsu.makeSet(node3);

    assertEquals(dsu.setTree.size(), 3);

    dsu.unite(node1, node2);

    assertEquals(dsu.setTree.size(), 2);

    Node node4 = new Node(4);
    node4.parent = node3;

    Node node5 = new Node(5);
    node5.parent = node4;

    assertEquals(dsu.find(node5), node3);

    assertEquals(node5.parent, node3);
    assertEquals(node4.parent, node3);
  }
}
