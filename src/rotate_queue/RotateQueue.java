package rotate_queue;

import queue.*;

import static org.junit.Assert.*;

import org.junit.Test;

import queue.*;

public class RotateQueue<T> {

  @Test
  public void test_1() {
    Queue<Integer> queue = new Queue<Integer>();

    queue.enqueue(0);
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);

    queue.rotate(1);

    assertEquals(queue.dequeue(), (Integer) 1);
    assertEquals(queue.dequeue(), (Integer) 2);
    assertEquals(queue.dequeue(), (Integer) 3);
    assertEquals(queue.dequeue(), (Integer) 0);
  }

  @Test
  public void test_2() {
    Queue<Integer> queue = new Queue<Integer>();

    queue.enqueue(0);
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);

    // 3 2 1 0

    queue.rotate(3);

    assertEquals(queue.dequeue(), (Integer) 3);
    assertEquals(queue.dequeue(), (Integer) 2);
    assertEquals(queue.dequeue(), (Integer) 1);
    assertEquals(queue.dequeue(), (Integer) 0);
  }

  @Test
  public void test_3() {
    Queue<Integer> queue = new Queue<Integer>();

    queue.enqueue(0);
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);

    // 3 2 1 0

    queue.rotate(4);

    assertEquals(queue.dequeue(), (Integer) 3);
    assertEquals(queue.dequeue(), (Integer) 2);
    assertEquals(queue.dequeue(), (Integer) 1);
    assertEquals(queue.dequeue(), (Integer) 0);
  }

  @Test
  public void test_4() {
    Queue<Integer> queue = new Queue<Integer>();

    queue.enqueue(0);
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);

    // 2 3 1 0

    queue.rotate(2);

    assertEquals(queue.dequeue(), (Integer) 2);
    assertEquals(queue.dequeue(), (Integer) 3);
    assertEquals(queue.dequeue(), (Integer) 1);
    assertEquals(queue.dequeue(), (Integer) 0);
  }

  @Test
  public void test_5() {
    Queue<Integer> queue = new Queue<Integer>();

    queue.enqueue(0);

    queue.rotate(1);

    assertEquals(queue.dequeue(), (Integer) 0);
  }

  @Test
  public void test_6() {
    Queue<Integer> queue = new Queue<Integer>();

    queue.enqueue(0);
    queue.enqueue(2);

    queue.rotate(1);

    assertEquals(queue.dequeue(), (Integer) 2);
    assertEquals(queue.dequeue(), (Integer) 0);
  }
}
