package test_linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.*;

public class Find_Test {

  @Test
  public void test1() {
    LinkedList2 linkedList = new LinkedList2();

    linkedList.addInTail(new Node(12));
    linkedList.addInTail(new Node(55));
    linkedList.addInTail(new Node(66));

    int findValue = 66;

    Node findNode = linkedList.find(findValue);

    assertEquals(findNode.value, findValue);
  }

  @Test
  public void test2() {
    LinkedList2 linkedList = new LinkedList2();

    linkedList.addInTail(new Node(12));

    Node findNode = linkedList.find(66);

    assertEquals(findNode, null);
  }

  @Test
  public void test3() {
    LinkedList2 linkedList = new LinkedList2();

    linkedList.addInTail(new Node(12));

    Node findNode = linkedList.find(12);

    assertEquals(findNode.value, 12);
  }

  @Test
  public void test4() {
    LinkedList2 linkedList = new LinkedList2();

    linkedList.addInTail(new Node(12));
    linkedList.addInTail(new Node(4));

    Node findNode = linkedList.find(4);

    assertEquals(findNode.value, 4);
  }

  @Test
  public void test5() {
    LinkedList2 linkedList = new LinkedList2();

    Node findNode = linkedList.find(4);

    assertEquals(findNode, null);
  }

}
