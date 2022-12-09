package test_queue;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.*;

public class Queue_Test {

  @Test
  public void test_1() {
    Queue<Integer> queue = new Queue<Integer>();

    assertEquals(queue.size(), 0);
    assertEquals(queue.dequeue(), null);

    queue.enqueue(0);
    queue.enqueue(1);
    queue.enqueue(2);

    assertEquals(queue.size(), 3);

    assertEquals(queue.dequeue(), (Integer) 0);

    assertEquals(queue.size(), 2);

    assertEquals(queue.dequeue(), (Integer) 1);

    assertEquals(queue.size(), 1);

    assertEquals(queue.dequeue(), (Integer) 2);

    assertEquals(queue.size(), 0);

    assertEquals(queue.dequeue(), null);

    assertEquals(queue.size(), 0);
  }

}
