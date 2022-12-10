package test_queue;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.*;

// Некорректное извлечение из непустой очереди

public class Queue_Test {

  @Test
  public void test_1() {
    Queue<Integer> queue = new Queue<Integer>();

    assertEquals(queue.dequeue(), null);

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

  @Test
  public void test_2() {
    Queue<Integer> queue = new Queue<Integer>();

    assertEquals(queue.dequeue(), null);
    assertEquals(queue.size(), 0);

    queue.enqueue(0);

    assertEquals(queue.size(), 1);

    assertEquals(queue.dequeue(), (Integer) 0);

    assertEquals(queue.dequeue(), null);
  }

  @Test
  public void test_3() {
    Queue<Integer> queue = new Queue<Integer>();

    assertEquals(queue.dequeue(), null);
    assertEquals(queue.size(), 0);

    for (int i = 0; i < 30; i++) {
      queue.enqueue(i);
    }

    assertEquals(queue.size(), 30);

    for (int i = 0; i < 30; i++) {
      assertEquals(queue.size(), 30 - i);
      assertEquals(queue.dequeue(), (Integer) i);
      assertEquals(queue.size(), 30 - i - 1);
    }
  }

}
