package test_linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

import linkedlist.*;

public class Clear_Test {

  @Test
  public void test1() {
    LinkedList2 list = new LinkedList2();

    list.addInTail(new Node(11));
    list.addInTail(new Node(12));
    list.addInTail(new Node(14));
    list.addInTail(new Node(15));

    list.clear();

    assertEquals(list.head, null);
    assertEquals(list.tail, null);
  }

}
