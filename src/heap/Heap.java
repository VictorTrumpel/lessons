package heap;

public class Heap {
  public int[] HeapArray; // хранит неотрицательные числа-ключи

  public Heap() {
    HeapArray = null;
  }

  public void MakeHeap(int[] a, int depth) {
    int tree_size = 0;

    int factor = 1;
    for (int i = 0; i <= depth; i++) {
      tree_size += factor;
      factor *= 2;
    }

    HeapArray = new int[tree_size];

    for (int i = 0; i < a.length; i++) {
      int key = a[i];
      Add(key);
    }
  }

  public int GetMax() {
    if (HeapArray[0] == 0)
      return -1;

    int rootKey = HeapArray[0];
    int lastKey = HeapArray[HeapArray.length - 1];

    HeapArray[HeapArray.length - 1] = 0;
    HeapArray[0] = lastKey;

    sortHeapAfterRemove(0);

    return rootKey;
  }

  public boolean Add(int key) {
    Integer emptyIdx = null;

    for (int i = 0; i < HeapArray.length; i++) {
      if (HeapArray[i] == 0)
        emptyIdx = i;
    }

    if (emptyIdx == null)
      return false;

    HeapArray[emptyIdx] = key;

    sortHeapAfterAdd(emptyIdx);

    return true;
  }

  void sortHeapAfterAdd(int currentIdx) {
    int parentIdx = (int) Math.floor((currentIdx - 1) / 2);

    int parentKey = HeapArray[parentIdx];
    int currentKey = HeapArray[currentIdx];

    if (currentKey > parentKey) {
      HeapArray[parentIdx] = currentKey;
      HeapArray[currentIdx] = parentKey;

      sortHeapAfterAdd(parentIdx);
    }
  }

  void sortHeapAfterRemove(int currentIdx) {
    int leftChildIdx = currentIdx * 2 + 1;
    int rightChildIdx = currentIdx * 2 + 2;

    int leftChildKey = 0;
    int rightChildKey = 0;

    if (leftChildIdx < HeapArray.length)
      leftChildKey = HeapArray[leftChildIdx];

    if (rightChildIdx < HeapArray.length)
      rightChildKey = HeapArray[rightChildIdx];

    int maxChildKeyIdx = leftChildKey > rightChildKey
        ? leftChildIdx
        : rightChildIdx;

    if (maxChildKeyIdx >= HeapArray.length)
      return;

    int currentKey = HeapArray[currentIdx];
    int maxChildKey = HeapArray[maxChildKeyIdx];

    if (currentKey < maxChildKey) {

      HeapArray[maxChildKeyIdx] = currentKey;
      HeapArray[currentIdx] = maxChildKey;

      sortHeapAfterRemove(maxChildKeyIdx);
    }
  }
}