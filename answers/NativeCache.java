class NativeCache<T> {
  private int size;
  private int step = 1;
  private int length = 0;

  public String[] slots;
  public T[] values;
  public int[] hits;

  public NativeCache(int sz, Class clazz) {
    size = sz;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, this.size);
    hits = new int[sz];
  }

  public void put(String key, T value) {
    int hashIdx = length == size ? releaseAndGetSlot() : getEmptySlotIdx(key, value);

    if (hashIdx == -1)
      return;

    slots[hashIdx] = key;
    values[hashIdx] = value;
    length += 1;
  }

  public T get(String key) {
    int hashIdx = findKey(key);

    if (hashIdx != -1) {
      hits[hashIdx] += 1;
      return values[hashIdx];
    }

    return null;
  }

  public int size() {
    return length;
  }

  private int releaseAndGetSlot() {
    int minHitsIdx = 0;

    for (int i = 1; i < hits.length; i++) {
      if (hits[i] < hits[minHitsIdx]) {
        minHitsIdx = i;
      }
    }

    length -= 1;

    hits[minHitsIdx] = 0;
    slots[minHitsIdx] = null;
    values[minHitsIdx] = null;

    return minHitsIdx;
  }

  private int hashFun(String value) {
    if (size == 0)
      return 0;

    int hash = value.hashCode();
    int idx = hash % size;
    return idx < 0 ? idx * -1 : idx;
  }

  private int getEmptySlotIdx(String key, T value) {
    int hashIdx = hashFun(key);

    if (slots[hashIdx] == null || values[hashIdx].equals(value))
      return hashIdx;

    int findIdx = hashIdx;
    Boolean isCircle = false;

    while (findIdx < slots.length) {
      findIdx += step;

      if (findIdx >= slots.length) {
        isCircle = true;
        int newIdx = findIdx - slots.length - 1;
        findIdx = newIdx < 0 ? 0 : newIdx;
      }

      if (slots[findIdx] == null)
        return findIdx;

      if (findIdx > hashIdx && isCircle)
        break;
    }

    return -1;
  }

  private int findKey(String key) {
    int hashIdx = hashFun(key);

    if (key.equals(slots[hashIdx]))
      return hashIdx;

    int idx = 0;
    while (idx < slots.length) {
      String currentKey = slots[idx];
      if (currentKey != null && currentKey.equals(key))
        return idx;

      idx += 1;
    }

    return -1;
  }
}