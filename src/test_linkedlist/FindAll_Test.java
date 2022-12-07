package test_linkedlist;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.*;

public class FindAll_Test {

  @Test
  public void test1() {
    LinkedList2 linkedList = new LinkedList2();

    Node n1 = new Node(12);
    Node n2 = new Node(55);
    Node n3 = new Node(55);
    Node n4 = new Node(55);
    Node n5 = new Node(55);

    linkedList.addInTail(n1);
    linkedList.addInTail(n2);
    linkedList.addInTail(n3);
    linkedList.addInTail(n4);
    linkedList.addInTail(n5);

    ArrayList<Node> nodes = linkedList.findAll(55);

    ArrayList<Node> rightNodes = new ArrayList<Node>();
    rightNodes.add(n2);
    rightNodes.add(n3);
    rightNodes.add(n4);
    rightNodes.add(n5);

    assertEquals(nodes.get(0), n2);
    assertEquals(nodes.get(1), n3);
    assertEquals(nodes.get(2), n4);
    assertEquals(nodes.get(3), n5);

    assertEquals(nodes.get(0).value, 55);
    assertEquals(nodes.get(1).value, 55);
    assertEquals(nodes.get(2).value, 55);
    assertEquals(nodes.get(3).value, 55);

    assertEquals(nodes.size(), 4);
  }

  @Test
  public void test2() {
    LinkedList2 linkedList = new LinkedList2();

    Node n1 = new Node(12);
    Node n2 = new Node(55);
    Node n3 = new Node(55);
    Node n4 = new Node(55);
    Node n5 = new Node(55);

    linkedList.addInTail(n1);
    linkedList.addInTail(n2);
    linkedList.addInTail(n3);
    linkedList.addInTail(n4);
    linkedList.addInTail(n5);

    ArrayList<Node> nodes = linkedList.findAll(66);

    assertEquals(nodes.size(), 0);
  }

  @Test
  public void test3() {
    LinkedList2 linkedList = new LinkedList2();

    ArrayList<Node> nodes = linkedList.findAll(66);

    assertEquals(nodes.size(), 0);
  }

  @Test
  public void test4() {
    LinkedList2 linkedList = new LinkedList2();

    Node n1 = new Node(12);
    Node n2 = new Node(55);

    linkedList.addInTail(n1);
    linkedList.addInTail(n2);

    ArrayList<Node> nodes = linkedList.findAll(55);

    assertEquals(nodes.size(), 1);
    assertEquals(nodes.get(0), n2);
    assertEquals(nodes.get(0).value, 55);
  }
}
