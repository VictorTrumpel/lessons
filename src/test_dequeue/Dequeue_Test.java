package test_dequeue;

import static org.junit.Assert.*;

import org.junit.Test;

import deque.*;

// Некорректное извлечение из непустой очереди

public class Dequeue_Test {

  @Test
  public void test_1() {
    Deque<Integer> deq = new Deque<Integer>();

    assertEquals(deq.size(), 0);
    assertEquals(deq.removeFront(), null);
    assertEquals(deq.removeTail(), null);

    deq.addFront(0);

    assertEquals(deq.size(), 1);

    assertEquals(deq.removeTail(), (Integer) 0);

    assertEquals(deq.removeTail(), null);

    assertEquals(deq.size(), 0);

    deq.addFront(0);

    assertEquals(deq.size(), 1);

    assertEquals(deq.removeFront(), (Integer) 0);

    assertEquals(deq.removeFront(), null);

    deq.addFront(0);
    deq.addFront(1);
    deq.addFront(2);
    deq.addFront(3);
    // 3 2 1 0

    assertEquals(deq.size(), 4);

    assertEquals(deq.removeFront(), (Integer) 3);
    assertEquals(deq.removeFront(), (Integer) 2);
    assertEquals(deq.removeFront(), (Integer) 1);
    assertEquals(deq.removeFront(), (Integer) 0);
    assertEquals(deq.removeTail(), null);
    assertEquals(deq.removeFront(), null);

    assertEquals(deq.size(), 0);

    deq.addFront(0);
    deq.addFront(1);
    deq.addFront(2);
    deq.addFront(3);
    deq.addFront(4);
    // 4 3 2 1 0

    assertEquals(deq.size(), 5);
    assertEquals(deq.removeTail(), (Integer) 0);
    assertEquals(deq.size(), 4);
    assertEquals(deq.removeFront(), (Integer) 4);
    assertEquals(deq.removeFront(), (Integer) 3);
    assertEquals(deq.removeTail(), (Integer) 1);
    assertEquals(deq.removeFront(), (Integer) 2);

    assertEquals(deq.size(), 0);

    deq.addTail(0);
    deq.addTail(1);
    deq.addTail(2);
    deq.addTail(3);
    deq.addTail(4);

    // 0 1 2 3 4

    assertEquals(deq.size(), 5);
    assertEquals(deq.removeTail(), (Integer) 4);
    assertEquals(deq.removeFront(), (Integer) 0);
    assertEquals(deq.removeFront(), (Integer) 1);
    assertEquals(deq.removeTail(), (Integer) 3);
    assertEquals(deq.removeTail(), (Integer) 2);

    assertEquals(deq.size(), 0);
    assertEquals(deq.removeTail(), null);
    assertEquals(deq.removeFront(), null);

    deq.addTail(2);
    deq.addFront(1);
    deq.addTail(3);
    deq.addFront(0);
    deq.addTail(4);
    // 0 1 2 3 4

    assertEquals(deq.size(), 5);
    assertEquals(deq.removeTail(), (Integer) 4);
    assertEquals(deq.removeFront(), (Integer) 0);
    assertEquals(deq.removeFront(), (Integer) 1);
    assertEquals(deq.removeTail(), (Integer) 3);
    assertEquals(deq.removeTail(), (Integer) 2);
    assertEquals(deq.size(), 0);
    assertEquals(deq.removeTail(), null);
    assertEquals(deq.removeFront(), null);
  }
}
