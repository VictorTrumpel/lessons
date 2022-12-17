package hash_table;

public class HashTable {
  public int size;
  public int step;
  public String[] slots;

  public HashTable(int sz, int stp) {
    size = sz;
    step = stp;
    slots = new String[size];
    for (int i = 0; i < size; i++)
      slots[i] = null;
  }

  public int hashFun(String value) {
    if (size == 0)
      return 0;

    int hash = value.hashCode();
    int idx = hash % size;
    return idx < 0 ? idx * -1 : idx;
  }

  public int seekSlot(String value) {
    int hashIdx = hashFun(value);

    if (slots[hashIdx] == null) {
      slots[hashIdx] = value;
      return hashIdx;
    }

    if (value == slots[hashIdx])
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

      if (slots[findIdx] == null) {
        return findIdx;
      }

      if (findIdx > hashIdx && isCircle)
        break;
    }

    return -1;
  }

  public int put(String value) {
    int hashIdx = hashFun(value);

    if (slots[hashIdx] == null) {
      slots[hashIdx] = value;
      return hashIdx;
    }

    if (value == slots[hashIdx])
      return hashIdx;

    return -1;
  }

  public int find(String value) {
    int idx = 0;

    while (idx < slots.length) {
      if (slots[idx] == value) {
        return idx;
      }

      idx += 1;
    }

    return -1;
  }
}