package test_heap;

import static org.junit.Assert.*;

import org.junit.Test;

import heap.*;

public class Heap_Test {

  @Test
  public void test_6() {
    Heap heap = new Heap();

    int[] nums = { 110, 90, 40, 70, 80, 30, 10, 20, 50, 60, 65, 31, 29, 11, 9 };

    heap.MakeHeap(nums, 3);

    int[] expectedNums = { 110, 90, 40, 70, 80, 31, 11, 20, 50, 60, 65, 30, 29, 10, 9 };

    assertArrayEquals(expectedNums, heap.HeapArray);

    assertEquals(110, heap.GetMax());

    int[] expectedNums1 = { 90, 80, 40, 70, 65, 31, 11, 20, 50, 60, 9, 30, 29, 10, -1 };

    assertArrayEquals(expectedNums1, heap.HeapArray);
  }
}
