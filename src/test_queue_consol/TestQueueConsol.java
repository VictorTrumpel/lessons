package test_queue_consol;

import queue.*;

public class TestQueueConsol {

  public void test_1() {
    Queue<Integer> queue = new Queue<Integer>();

    System.out.println(queue.size() == 0);
    System.out.println(queue.dequeue() == null);
    System.out.println(queue.size() == 0);

    for (int i = 1; i < 1001; i++) {
      queue.enqueue(i);
    }

    System.out.println(queue.size() == 1000);
    System.out.println(queue.dequeue() == 1);
    System.out.println(queue.size() == 999);
  }
}
