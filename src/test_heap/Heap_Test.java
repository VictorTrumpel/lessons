package test_heap;

import static org.junit.Assert.*;

import org.junit.Test;

import heap.*;

public class Heap_Test {

  @Test
  public void test_1() {
    Heap heap = new Heap();

    int[] nums = { 4, 1, 3, 7, 8, 9, 11 };

    heap.MakeHeap(nums, 2);

    int[] expectedTree = { 11, 9, 4, 8, 7, 3, 1 };

    assertArrayEquals(expectedTree, heap.HeapArray);

    assertEquals(11, heap.GetMax());

    int[] expectedTree1 = { 9, 8, 4, 1, 7, 3, 0 };

    assertArrayEquals(expectedTree1, heap.HeapArray);

    assertEquals(9, heap.GetMax());

    int[] expectedTree2 = { 8, 7, 4, 1, 0, 3, 0 };

    assertArrayEquals(expectedTree2, heap.HeapArray);
  }

  @Test
  public void test_2() {
    Heap heap = new Heap();

    int[] nums = { 4, 1, 3, 7, 8, 9 };

    heap.MakeHeap(nums, 2);

    int[] expectedTree = { 9, 8, 4, 0, 7, 3, 1 };

    assertArrayEquals(expectedTree, heap.HeapArray);

    assertEquals(true, heap.Add(11));

    int[] expectedTree2 = { 11, 9, 4, 8, 7, 3, 1 };

    assertArrayEquals(expectedTree2, heap.HeapArray);

    assertEquals(false, heap.Add(12));
  }

  @Test
  public void test_3() {
    Heap heap = new Heap();

    int[] nums = { 4, 1, 3 };

    heap.MakeHeap(nums, 2);

    int[] expectedTree = { 4, 0, 3, 0, 0, 0, 1 };

    assertArrayEquals(expectedTree, heap.HeapArray);
  }
}
